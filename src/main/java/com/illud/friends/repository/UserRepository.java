package com.illud.friends.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.illud.friends.domain.User;

@RepositoryRestResource(collectionResourceRel = "friends", path = "friends")
public interface UserRepository extends Neo4jRepository<User,String>{
	
	User save(User entity);
	List<User> findByName(@Param("name") String name);

	@Query("MATCH (u:User{userId:"+" {userId} "+"}),(u)-[r:FRIEND_OF]-(friends) RETURN friends;")
	List<User> findAllFriends(@Param("userId") String userId);
	
	//method to make friend or accept friend request 
	
	@Query("MATCH (u:User{userId:"+" {userId} "+"}),(f:User{userId:"+" {friendId} "+"}) create (u)-[:FRIEND_OF]->(f) RETURN u;")
	User acceptFriendRequest(@Param("userId") String userId,@Param("friendId") String friendId);
	
	//method to unfriend an existing friend
	
	@Query("match (u:User{userId:"+"{userId}"+"}),(f:User{userId:"+"{friendId}"+"}),(u)-[r:FRIEND_OF]-(f) delete r;")
	User unfriend(@Param("userId") String userId,@Param("friendId") String friendId);
	
	//method to make an friend request relation between friends
	@Query("match (u:User{userId:"+"{userId}"+"}),(f:User{userId:"+"{friendId}"+"}) create (u)-[:FRIEND_REQUEST]->(f) return f")
	 User createAnFriendRequest(@Param("userId") String userId,@Param("friendId")  String friendId);
	
	//get all friend requests get for this user
	@Query("match (u:User{userId:"+"{ userId }"+"}),(u)<-[:FRIEND_REQUEST]-(requesters) return requesters;")
	List<User> findAllFriendRequests(@Param("userId") String userId);
	
	//method to cancel friend requests
	@Query("match (u:User{userId:"+"{userId}"+"}),(f:User{userId:"+"{friendId}"+"}),(u)-[r:FRIEND_REQUEST]-(f) delete r;")
	User cancelFriendRequest(@Param("userId") String userId,@Param("friendId")  String friendId);
	
	
	//get all users  with the given name from the friend list
	@Query("match (u:User{userId:"+"{ userId }"+"}),(friends:User{name:\"+\"{ name }\"+\"}),(u)-[:FRIEND_OF]-(friends) return friends limit 25;")
	List<User> findAllFriendsWithName(@Param("userId") String userId,@Param("name") String name);
	
	//get all users  with the given name from the friendrequest list
	@Query("match (u:User{userId:"+"{ userId }"+"}),(friendRequests:User{name:\"+\"{ name }\"+\"}),(u)-[:FRIEND_REQUEST]-(friendRequests:User) return friendRequests:User limit 25;")
	List<User> findAllFriendRequestsWithName(@Param("userId") String userId,@Param("name") String name);
	
	//get all users except people in friend list or friend request list
	@Query(" MATCH (b:User),(a:User{userId:'"+"{userId}"+"'}) WHERE NOT (a)-[:FRIEND_OF]-(b) and  not (a)-[:FRIEND_REQUEST]-(b) return b limit 30;")
	List<User> findAllUsersExceptFriendRequesOrFriends(@Param("userId") String userId);
	

}
