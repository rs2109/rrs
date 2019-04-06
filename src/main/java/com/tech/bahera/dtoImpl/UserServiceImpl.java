//package com.tech.bahera.dtoImpl;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.tech.bahera.dao.UserDAO;
//import com.tech.bahera.dto.Address;
//import com.tech.bahera.dto.User;
//
//
//@Service(value = "userService")
//public class UserServiceImpl implements UserDetailsService, UserDAO {
//	
//	@Autowired
//	private UserDAO userDao;
//	
//	
//
//	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
//		System.out.println("Client called loadUserByUsername :"+userId);
//		Optional<User> user = userDao.findById(userId);
//		if(!user.isPresent()){
//			throw new UsernameNotFoundException("Invalid username or password.");
//		}
//		return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), getAuthority());
//	}
//
//	private List<SimpleGrantedAuthority> getAuthority() {
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	}
//
//	public List<User> findAll() {
//		List<User> list = new ArrayList<>();
//		userDao.findAll().iterator().forEachRemaining(list::add);
//		return list;
//	}
//
//	public User loadUserByEmail(String email) throws UsernameNotFoundException {
//		Optional<User> user = userDao.findById(email);
//		if(!user.isPresent()){
//			return null;
//		}
//		return user.get();
//	}
//	
//	
//	@Override
//    public User save(User user) {
//        return userDao.save(user);
//    }
//
//	
//
//	
//	@Override
//	public <S extends User> List<S> saveAll(Iterable<S> entites) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<User> findAll(Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> S insert(S entity) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> List<S> insert(Iterable<S> entities) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> List<S> findAll(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Page<User> findAll(Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<User> findById(String id) {
//		// TODO Auto-generated method stub
//		return userDao.findById(id);
//	}
//
//	@Override
//	public boolean existsById(String id) {
//		// TODO Auto-generated method stub
//		return userDao.existsById(id);
//	}
//
//	@Override
//	public Iterable<User> findAllById(Iterable<String> ids) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void deleteById(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(User entity) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends User> entities) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteAll() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public <S extends User> Optional<S> findOne(Example<S> example) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public <S extends User> long count(Example<S> example) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public <S extends User> boolean exists(Example<S> example) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
