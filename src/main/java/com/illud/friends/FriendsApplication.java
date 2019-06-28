package com.illud.friends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@EnableNeo4jRepositories("com.illud.friends.repository")
@SpringBootApplication
@EnableAutoConfiguration
public class FriendsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsApplication.class, args);
	}

}
