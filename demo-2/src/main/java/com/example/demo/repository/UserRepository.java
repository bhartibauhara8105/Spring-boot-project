package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	  @Query(value="select u from User u where u.name= :name") 
	  public User getUserByName(String name);

	  @Query(value="select u from User u where u.id= :id")
	public User findOne(Long id);
	  
	 
}
