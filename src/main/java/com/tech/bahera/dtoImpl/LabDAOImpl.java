package com.tech.bahera.dtoImpl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.result.DeleteResult;
import com.tech.bahera.dto.StoreRecordFiles;
import com.tech.bahera.dto.UserRecord;


//@Repository("labDAO")
@Service(value = "labDAO")
public class LabDAOImpl {

	@Autowired
	private MongoOperations mongoOperations;

	public StoreRecordFiles saveFile(MultipartFile file, String email) {
		StoreRecordFiles doc = new StoreRecordFiles();
		try {
			doc.setEmailId(email);
			doc.setContentType(file.getContentType());
			doc.setName(file.getOriginalFilename());
			doc.setDateTime(new SimpleDateFormat().format(new Date()));
			doc.setFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

			mongoOperations.insert(doc);
			System.out.println(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return doc;
	}


	public StoreRecordFiles downloadFile(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		StoreRecordFiles docFromDB = mongoOperations.findOne(query, StoreRecordFiles.class);
		return docFromDB;
	}


	

	public List<StoreRecordFiles> retrieveFileDetails(String email) {
		Query query = new Query(Criteria.where("emailId").is(email));
		return mongoOperations.find(query, StoreRecordFiles.class);

	}

	public boolean deleteFileById(String id, String email) {

		System.out.println("Delete Record :" + id + ", " + email);
		Query query = new Query(Criteria.where("_id").is(id).and("emailId").is(email));
		DeleteResult rs = mongoOperations.remove(query, StoreRecordFiles.class);
		if (rs.getDeletedCount() > 0) {
			System.out.println("Deleted Record :" + rs.getDeletedCount());
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteFileByName(String name, String email) {

		Query query = new Query(Criteria.where("name").is(name).and("emailId").is(email));
		DeleteResult rs = mongoOperations.remove(query, StoreRecordFiles.class);
		if (rs.getDeletedCount() > 0) {
			System.out.println("Deleted Record :" + rs.getDeletedCount());
			return true;
		} else {
			return false;
		}
	}
}
