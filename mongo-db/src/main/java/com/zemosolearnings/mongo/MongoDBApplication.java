package com.zemosolearnings.mongo;

import com.zemosolearnings.mongo.mapper.StudentMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.zemosolearnings.mongo.repository")
@ComponentScan("com.zemosolearnings.mongo.*")
public class MongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoDBApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Bean
	public StudentMapper studentMapper(){
		return new StudentMapper(modelMapper());
	}

}
