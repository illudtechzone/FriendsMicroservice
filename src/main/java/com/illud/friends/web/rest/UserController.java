package com.illud.friends.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.illud.friends.domain.User;
import com.illud.friends.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@CrossOrigin("*")
@RestController
@RequestMapping("/apis")
public class UserController {
	
    private final Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		log.debug(">>>>>>>>>>>>>>>>user "+user.getName());
		userService.create(user);
		return user;
		
	}
	@GetMapping("/user/{name}")
	public List<User> findUserByName(@PathVariable String name){
		
		log.debug(">>>>>>>>>>>>>>>>usernmae "+name);
		
		return userService.findByName(name);

	}
	
	@GetMapping("/user/{userId}/friends")
	public List<User> findFriends(@PathVariable String userId){
		log.debug(">>>>>>>>>>>>>>>>userId "+userId);
		return userService.findFriends(userId);
	}
	
	@PostMapping("/user/{userId}/accept/{friendId}")
	public User acceptFriendRequest(@PathVariable String userId,@PathVariable String friendId) {
		log.debug("request to accept friend request from userId:"+userId+" friendId:"+friendId);
		return userService.acceptFriendRequest(userId, friendId);
	}
	
	@DeleteMapping("/user/{userId}/unfriendUser/{friendId}")
	public User unFriend(@PathVariable String userId,@PathVariable String friendId){
		log.debug("request to unfriend userId:"+userId+" friendId:"+friendId);
		return userService.unfriend(userId, friendId);
	}
	
	@DeleteMapping("/user")
	public Boolean delete(@RequestBody User user){
		log.debug("request to delete  user :"+user);
		return userService.delete(user);
	}
	
	@PostMapping("/createFriendRequest/user/{userId}/friend/{friendId}")
	public void friendRequest(@PathVariable String userId,@PathVariable String friendId)
	{
		log.debug("request to sent friend request  userId:"+userId+" friendId:"+friendId);
		userService.createFriendRequest(userId,friendId);

	}
	
	@GetMapping("/friendRequest/user/{userId}")
	public List<User> findAllFriendRequests(@PathVariable String userId){

		log.debug("request to get all friendrequests of userId:"+userId);
		
		return userService.findAllFriendRequests(userId);
		
		
		
	}
	@DeleteMapping("/user/{userId}/cancelFriendRequest/{friendId}")
	public User cancelFriendRequest(@PathVariable String userId,@PathVariable String friendId){
		log.debug("request to unfriend userId:"+userId+" friendId:"+friendId);
		return userService.cancelFriendRequest(userId, friendId);
	}
	
	@GetMapping("/usersByName/{name}")
	public List<User>findByUserName(@PathVariable String name){
		log.debug("requets to find all user with name "+name);
		return userService.findByName(name);
		
	}
	
	@GetMapping("/{userId}/friendRequestByName/{name}")
	public List<User>findFriendRequestByname(@PathVariable String userId,@PathVariable String name){
		log.debug("requets to find all friendRequest with name "+name);
		return userService.findFriendRequestByname(userId,name);
		
		
	}
	
	@GetMapping("/{userId}/friendsByName/{name}")
	public List<User>findFriendsByname(@PathVariable String userId,@PathVariable String name){
		log.debug(">>>>>>>>>>>>requets to find all friendRequest with name "+name);
		return userService.findFriendsByname(userId,name);
		
		
	}
	
	//rest method to find people that are not in friendlist or friend request list
	@GetMapping("/{userId}/othersByName/{name}")
	public List<User>findOthersByname(@PathVariable String userId,@PathVariable String name){
		log.debug("requets to find all friendRequest with name "+name);
		return userService.findOthersByname(userId,name);
		
		
	}
	

}
