package org.closure.MMirror;

import java.time.Instant;

import org.closure.MMirror.models.UserDto;
import org.closure.MMirror.services.IdGeneration;
import org.closure.MMirror.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MMirrorApplication {


	public static void main(String[] args) {
		SpringApplication.run(MMirrorApplication.class, args);
		
	}

	

}
