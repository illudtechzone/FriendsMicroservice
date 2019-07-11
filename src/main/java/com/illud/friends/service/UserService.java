package com.illud.friends.service;

import java.util.List;


import com.illud.friends.domain.User;

public interface UserService {
	
	public User create(User user);
	public List<User>findByName(String name);
	
	public List<User> findFriends( String userId);
	
	public User acceptFriendRequest(String userId,String friendId);
	
	public User unfriend(String userId,String friendId);
	
	public Boolean delete(User user);
	
	public User createFriendRequest(String userId, String friendId);
	
	public List<User> findAllFriendRequests(String userId);
	
	
	public User cancelFriendRequest(String userId, String friendId);

}
