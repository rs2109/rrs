package com.tech.bahera.controller;

import java.net.URI;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tech.bahera.config.JwtTokenUtil;
import com.tech.bahera.dto.Address;
import com.tech.bahera.dto.AuthToken;
import com.tech.bahera.dto.EmailUser;
import com.tech.bahera.dto.LoginUser;
import com.tech.bahera.dto.User;
import com.tech.bahera.dtoImpl.UserServeImpl;

//@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4201","http://192.168.1.20:4200", "http://192.168.43.99:4200","http://217.136.61.67:4200", "http://109.142.21.105:4200","http://109.142.21.105:80","http://109.142.21.105:8080"} )
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin
@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserServeImpl userService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
	private BCryptPasswordEncoder bcryptEncoder;
 
    
    @PutMapping("/uaddress")
    public boolean updateUserAddress(@RequestBody Address addr) {
    	
    	addr.setEmailId(SecurityContextHolder.getContext().getAuthentication().getName());
    	return userService.saveAddress(addr);

    }
    
    @GetMapping("/address")
	public Address getUserAddress() {
		System.out.println("Client called getUserAddress :");
		return userService.getAddress(SecurityContextHolder.getContext().getAuthentication().getName());
			
	}
    
    @GetMapping("/address/{email}")
	public Address getUserAddress(@PathVariable("email") String email) {
		System.out.println("Client called getUserAddress :");
		return userService.getAddress(email);
			
	}
    
    
	@GetMapping("/customer")
	public User getCustomerById() {
		System.out.println("Get User by emailID :" );
		return userService.loadUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());	
	}
	
	@GetMapping("/customers")
	public List<User> getAllCustomer() {
		System.out.println("Get all customers..:" );
		List<User> usList =  userService.loadAllUsers();
		System.out.println(usList);
		return usList;
	}
	
	@GetMapping("/customer/{email}")
	public User getCustomerById(@PathVariable("email") String email) {
		System.out.println("Get User by emailID :" );
		return userService.loadUserByEmail(email);
	}
	
	@GetMapping("/customerm/{mobile}")
	public User getCustomerByMobile(@PathVariable("mobile") String mobile) {
		System.out.println("Get User by mobile number." + mobile);
		return userService.loadUserByMobile(mobile);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/register")
	public ResponseEntity<?> createCustomer(@RequestBody User user) {
		System.out.println("Going to create User:");
		System.out.println(user);
		
//		if(userService.loadUserByEmail(user.getUsername()) != null) {
//            return new ResponseEntity(new CustomErrorType("Email address " + 
//		            user.getUsername() + " already exist."),HttpStatus.IM_USED);
//        }
		
		String pwd = user.getPassword();
		if (pwd != null && pwd != "")
			user.setPassword(bcryptEncoder.encode(pwd));
		else {
			user.setPassword(bcryptEncoder.encode("abc123"));
		}
	
		
		//String dob= user.getDob();
		
		//user.setDob(dob.substring(0, dob.indexOf('T')));
        // Create a new user
		System.out.println(user);
		userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/mrest/register")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(user);
		
		
	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailUser user) {
		System.out.println(user);
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465);

        mailSender.setUsername("onlineappmailservice@gmail.com");
        mailSender.setPassword("saharanpur_011");

        //from email id and password
        //System.out.println("Username is : " + String.valueOf(resourceList.get(0)).split("@")[0]);
        //System.out.println("Password is : " + String.valueOf(resourceList.get(1)));

        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.starttls.enable", "true");
        mailProp.put("mail.smtp.starttls.required", "true");
        mailProp.put("mail.debug", "true");
        mailProp.put("mail.smtp.ssl.enable", "true");
        
		
		String strMsgSender = "<table style=\"width:100%\">\r\n" + 
				"<tr ><td>Hi "+user.getUsername()+ ","+ "</td></tr>\r\n" + 
				"<tr height = 20px></tr>\r\n" + 
				"<tr ><td>Welcome to <a href=\"http://localhost:4200\">Ran Soft Service</a> Thank you for your query. We will reply soon on your query.</td></tr>\r\n" +  
				"<tr height = 80px></tr>\r\n" + 
				"<tr ><td>Best Regards</td></tr>\r\n" + 
				"<tr ><td>RRS Team</td></tr>         \r\n" + 
				"<tr ><td><a href=\"http://localhost:4200\">Ran Soft Service</a></td></tr>				\r\n" +
				"</table>\r\n" + 
				"";

		String strMsg = "<table style=\"width:100%\">\r\n" + 
				"<tr ><td>"+user.getMessage()+ ","+ "</td></tr>\r\n" + 
				"<tr height = 20px></tr>\r\n" +   
				"<tr height = 80px></tr>\r\n" + 
				"<tr ><td>Best Regards</td></tr>\r\n" + 
				"<tr ><td>RSS Team</td></tr>         \r\n" + 
				"<tr ><td><a href=\"http://localhost:4200\">Ran Soft Service</a></td></tr>				\r\n" +
				"</table>\r\n" + 
				"";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("randhirsingh21@gmail.com");
            helper.setSubject(user.getSubject());
            helper.setText(strMsgSender, true);

            //Checking Internet Connection and then sending the mail
            //if(OneMethod.isNetConnAvailable())
                mailSender.send(mimeMessage);
                
            MimeMessageHelper help = new MimeMessageHelper(mimeMessage, true);
            help.setTo(user.getEmailId());
            help.setSubject(user.getSubject());
            help.setText(strMsg, true);

            //Checking Internet Connection and then sending the mail
            //if(OneMethod.isNetConnAvailable())
            	//mailSender.send(mimeMessage);
            //else
                //JOptionPane.showMessageDialog(null, "No Internet Connection Found...");
            URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath().path("/rrs")
                        .buildAndExpand(user.getEmailId()).toUri();
            	return ResponseEntity.ok("success");
                //return ResponseEntity.created(location).body("success");
        } catch (MessagingException e) {
            e.printStackTrace();
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/rrs")
                    .buildAndExpand(user.getEmailId()).toUri();

            //return ResponseEntity.created(location).body("failure");
            return ResponseEntity.ok("failure");
        }
   }
	
	
	@PostMapping(value = "/token")
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
    	
    	String tokenNok = "dummmy";
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.loadUserByEmail(loginUser.getUsername());
        if (user != null) {
        	final String token = jwtTokenUtil.generateToken(user);
        	user.setToken(token);
        	//return ResponseEntity.ok(new AuthToken(token));
        		 return ResponseEntity.ok(user);
        }
        
        return ResponseEntity.ok(new AuthToken(tokenNok));
    }
 
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticateCustomer(@RequestBody LoginUser loginUser) {
//		System.out.println("User Name: " + loginUser.getUsername() + "...");
//		System.out.println("User Password: " + loginUser.getPassword() + "...");
//		
//		
//		
//        String tokenNok = "dummmy";
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginUser.getUsername(),
//                        loginUser.getPassword()
//                )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        final Optional<User> user = userService.findById(loginUser.getUsername());
//        if (user.isPresent()) {
//        	final String token = jwtTokenUtil.generateToken(user.get());
//        	User fuser = user.get();
//        		 fuser.setToken(token);
//        	//return ResponseEntity.ok(new AuthToken(token));
//        		 System.out.println("User is logged in :" + loginUser.getUsername());
//        		 return ResponseEntity.ok(fuser);
//        }
//        System.out.println("User is not authorize to log in :" + loginUser.getUsername());
//        return ResponseEntity.ok(new AuthToken(tokenNok));
//        
//        
//        
//        
//        
//	}
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateCustomer(@RequestBody LoginUser loginUser) {
		System.out.println("User Name: " + loginUser.getUsername() + "...");
		System.out.println("User Password: " + loginUser.getPassword() + "...");
		
		
		
        String tokenNok = "dummmy";
        try {
        	final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );
       
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final User user = userService.loadUserByEmail(loginUser.getUsername());
        if (user != null) {
        	final String token = jwtTokenUtil.generateToken(user);
        	
        		 user.setToken(token);
        	//return ResponseEntity.ok(new AuthToken(token));
        		 System.out.println("User is logged in :" + loginUser.getUsername());
        		 return ResponseEntity.ok(user);
        }
        System.out.println("User is not authorize to log in :" + loginUser.getUsername());
        return ResponseEntity.ok(new AuthToken(tokenNok));
        }catch(Exception ex) {
        	System.out.println("Authentication Error:"+ex.getMessage());
        	//return (ResponseEntity<?>) ResponseEntity.badRequest();
        	return new ResponseEntity<String>(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
        } 
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateCustomer( @RequestBody User user) {
		
		
		User updateduser = userService.save(user);

		return new ResponseEntity<>(updateduser, HttpStatus.OK);
	}
 
//	@DeleteMapping("/customers/{id}")
//	public ResponseEntity<String> deleteUser(@PathVariable("id") String id) {
//		System.out.println("Delete User with ID = " + id + "...");
// 
//		userService.deleteById(id);
//		
//		return new ResponseEntity<>("User has been deleted!", HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/user/delete")
//	public ResponseEntity<String> deleteAllUsers() {
//		System.out.println("Delete All Customers...");
// 
//		userService.deleteAll();
//		
//		return new ResponseEntity<>("All users have been deleted!", HttpStatus.OK);
//	}


	
	
	

}
