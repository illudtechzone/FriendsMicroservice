package com.illud.friends.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.illud.friends.domain.User;
import com.illud.friends.repository.UserRepository;
import com.illud.friends.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private  UserRepository userRepo;
	//create an new user 
	@Override
	public User create(User user) {
		
		return userRepo.save(user);
	}
	@Override
	public List<User> findByName(String name) {
		return userRepo.findByName(name);
	}
	@Override
	public List<User> findFriends(String userId) {
		
		return userRepo.findAllFriends(userId);
	}
	@Override
	public User acceptFriendRequest(String userId, String friendId) {
		
		User user=userRepo.acceptFriendRequest(userId,friendId);
		cancelFriendRequest(userId,friendId);
		return user;
	}
	@Override
	public User unfriend(String userId, String friendId) {
		
		
		return userRepo.unfriend(userId,friendId);
	}
	@Override
	public Boolean delete(User user) {
		
		Boolean result=true;
		try {
		 userRepo.delete(user);
		}
		catch(Exception e)
		{
			log.error("error while removing user "+e);
			result=false;
		}
		return result;
		 
	}
	
	@Override
	public User createFriendRequest(String userId, String friendId) {
		
		return userRepo.createAnFriendRequest(userId,friendId);
	}
	@Override
	public List<User> findAllFriendRequests(String userId) {
		
		return userRepo.findAllFriendRequests(userId);
	}
	
	@Override
	public User cancelFriendRequest(String userId, String friendId) {
		
		return userRepo.cancelFriendRequest(userId,friendId);
	}
	@Override
	public List<User> findFriendRequestByname(String userId, String name) {
		
		return userRepo.findAllFriendRequestsWithName(userId, name);
	}
	@Override
	public List<User> findFriendsByname(String userId, String name) {
		
	
		return userRepo.findAllFriendsWithName(userId, name);
	}
	@Override
	public List<User> findOthersByname(String userId, String name) {
	
		return userRepo.findAllUsersExceptFriendRequesOrFriends(userId,name);
	}

}
