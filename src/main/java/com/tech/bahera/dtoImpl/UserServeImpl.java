package com.tech.bahera.dtoImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.tech.bahera.dto.Address;
import com.tech.bahera.dto.User;

@Service(value = "userService")
public class UserServeImpl implements UserDetailsService{
	
	@Autowired
	private MongoOperations mongoOperations;
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.out.println("Client called loadUserByUsername :"+userId);
		Query query = new Query(Criteria.where("username").is(userId));
		List<User> la = mongoOperations.find(query, User.class);
		
		if(la.size() <= 0){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(la.get(0).getUsername(), la.get(0).getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}



	public User loadUserByEmail(String email) throws UsernameNotFoundException {
		Query query = new Query(Criteria.where("username").is(email));
		List<User> la = mongoOperations.find(query, User.class);
		
		if(la.size() <= 0){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return la.get(0);
	}
	
	public User loadUserByMobile(String mobile) throws UsernameNotFoundException {
		Query query = new Query(Criteria.where("contactNumber").is(mobile));
		List<User> la = mongoOperations.find(query, User.class);
		
		if(la.size() <= 0){
			throw new UsernameNotFoundException("Invalid mobile number.");
		}
		return la.get(0);
	}
	public List<User> loadAllUsers(){
		return mongoOperations.findAll(User.class);
	}
	
    public User save(User user) {
        return mongoOperations.save(user, "user");
    }

    public boolean saveAddress(Address add) {
    	System.out.println("Client called updateUserAddress :"+add);
    	if(getAddress(add.getEmailId()) == null) {
    		mongoOperations.save(add, "useraddr");
    		return true;
    	}
    	else {
    		Query query = new Query(Criteria.where("emailId").is(add.getEmailId()));
    		Update update = new Update();
    		update.set("addline1", add.getAddline1());
    		update.set("addline2", add.getAddline2());
    		update.set("city", add.getCity());
    		update.set("pcode", add.getPcode());
    		update.set("state", add.getState());
    		update.set("country", add.getCountry());
    		UpdateResult us =  mongoOperations.updateFirst(query, update, Address.class);
    		if (us.isModifiedCountAvailable()) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
        
    }
  
    public Address getAddress(String email) {
    	Query query = new Query(Criteria.where("emailId").is(email));
        List<Address> addList = (mongoOperations.find(query, Address.class));
        
        if(addList.size() == 0) {
        	return null;
        }
        else {
        	return addList.get(0);
        }
    }
}
