
  package com.example.demo.service;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Service;

 import com.example.demo.model.User; 
 import com.example.demo.repository.UserRepository;
 
@Service
  public class UserServiceImpl{


 @Autowired UserRepository userRepository;
 
 public void createUser(User user) { 
 User u=new User(); u.setId(1L);
 u.setName("Bharti"); u.setAddress("Noida"); // TODO Auto-generated method


  userRepository.save(u);
 }
 
  
 /*
 * public User getUser(long id) { // TODO Auto-generated method stub User u=new
 * User(); u=userRepository.findById(id); User u1=new User();
 * u1.setId(u.getId()); u1.setName(u.getName()); u1.setAddress(u.getAddress());
 * return u1; }
 * 
 */public User updateUser(User user) { 
	 User u=new User();
	 
			 user.setId(103L);
			 long id=user.getId();
 u=userRepository.findById(id).get(); 
 u.setName(user.getName());
  u.setAddress(user.getAddress()); 
  return userRepository.save(u);
 }


  // TODO Auto-generated method stub return null; * 
  public void deleteUser(User user) { 
	  User u=new User();
	  long id=user.getId();
  user=userRepository.findById(id).get(); 
  userRepository.delete(u); // TODO

  
  }
}