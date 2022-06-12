package com.gobbledn.profileservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
public class ProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileServiceApplication.class, args);
	}

	@Autowired
	ProfileService profileService;

	@Bean
	public Consumer<String> postCreated() {
		return message -> profileService.increaseProfilePosts(message);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
