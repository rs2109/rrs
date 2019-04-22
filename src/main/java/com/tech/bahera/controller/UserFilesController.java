package com.tech.bahera.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tech.bahera.dto.StoreRecordFiles;
import com.tech.bahera.dto.UserRecord;
import com.tech.bahera.dtoImpl.LabDAOImpl;
import com.tech.bahera.fileUpload.StorageService;
 
 
//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201","http://192.168.1.20:4200", "http://192.168.1.20:4201","http://192.168.1.60:4200", "http://192.168.1.60:4201"} )
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping
public class UserFilesController {
 
	@Autowired
	StorageService storageService;
	@Autowired
	private LabDAOImpl labDAO;
	
	@PostMapping("/upload")
	public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile multipart) {
		System.out.println("Going to upload File contentType:"+ multipart.getContentType());
		System.out.println("SecurityContextHolder.getContext().getAuthentication().getName():"+ SecurityContextHolder.getContext().getAuthentication().getName());
		StoreRecordFiles res = labDAO.saveFile(multipart, SecurityContextHolder.getContext().getAuthentication().getName());
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/mrest/createrecord")
                .buildAndExpand(res.getName()).toUri();

        return ResponseEntity.created(location).body(res.getEmailId());
		
	}
	
	
	@GetMapping("/file/{email}")
	@ResponseBody
	public String getFilesbyEmail(@PathVariable String email){
		System.out.println("Going to getFile details:"+ email);
		return labDAO.retrieveFileDetails(email).get(0).getName();
	    
	} 
	
	@PostMapping("/file")
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(@RequestBody StoreRecordFiles req, HttpServletRequest request) throws IOException {
		System.out.println("downloadFile is called:" + req); 
		// Load file as Resource
		StoreRecordFiles rf = labDAO.downloadFile(req.getEmailId());
		Binary document = rf.getFile();
		 
		//File fl = new File(bfile.getData());
		ByteArrayResource resource = new ByteArrayResource(document.getData());

         String contentType = rf.getContentType();
            System.out.println("User file contentType:"+contentType);
       
        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        //contentType="application/pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
       
	}
	
	
	
}