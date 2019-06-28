package com.illud.friends.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class User {
	@Id
	private String userId;
	private String name;
	@Relationship(type="FRIEND_OF", direction = Relationship.OUTGOING) 
	private List<User>friends=new ArrayList<>();
	 

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
