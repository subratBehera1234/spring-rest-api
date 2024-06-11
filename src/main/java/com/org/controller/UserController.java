package com.org.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.User;
import com.org.repo.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository repo;
	
	@PostMapping("/save")
	public String saveUSer(@RequestBody User user) {
		 repo.save(user);
		return "user saved";
	}
	
	@GetMapping("/fetchAll")
	public List<User> fetchAll(){
		
		return repo.findAll();
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<User> fetchById(@PathVariable int id){
		Optional<User> user = repo.findById(id);
		if(user.isPresent())
		return new ResponseEntity<User>(user.get(),HttpStatus.FOUND);
		else
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	@PutMapping("/update/{id}")
	public String updateUserById(@PathVariable int id,@RequestBody User user) {
		Optional<User> u = repo.findById(id);
		
		if(u.isPresent()) {
			User dbUser=u.get();
			dbUser.setName(user.getName());
			dbUser.setAge(user.getAge());
			dbUser.setMobile(user.getMobile());
			dbUser.setEmail(user.getEmail());
			dbUser.setPassword(user.getPassword());
			
			repo.save(dbUser);
			return "user updated";
		}
		return "user not existed";
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		repo.deleteById(id);
		return "user deleted successfully";
	}
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		repo.deleteAll();
		
		return"All user deleted successfully";
	}
	@GetMapping("/fetchByEmailAndPwd")
	public User fetchByEmailAndPwd(@RequestParam String email,@RequestParam String password) {
		Optional<User> user = repo.findByEmailAndPassword(email,password);
		return user.get();
	}
}
