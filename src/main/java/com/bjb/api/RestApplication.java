package com.bjb.api;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bjb.api.model.User;
//import com.bjb.api.model2.Book;
import com.bjb.api.repository.UsersRepository;
//import com.bjb.api.repository2.BookRepository;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}
//	@Bean
//    public Docket productApi() {
//       return new Docket(DocumentationType.SWAGGER_2).select()
//          .apis(RequestHandlerSelectors.basePackage("com.example.demo")).build();
//    }
//	@Bean
//    CommandLineRunner initDatabase(BookRepository repository) {
//        return args -> {
//            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santideva", new BigDecimal("15.41")));
//            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
//            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
//        };
//    }
//	  @Bean
//	  CommandLineRunner initDatabase(UsersRepository repository) {
//	      return args -> {
//	          repository.save(new User("admin", "$2a$12$CzCxM6CakgZWcY9GKKPK1uVy0bABspBhOLBuFvOCacboEhis43a7y", "ROLE_ADMIN", true));
//	      };
//	  }
}
