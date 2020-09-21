package learn.reactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@ComponentScan(basePackages = "learn.reactive.demo")
//@ConditionalOnClass(value=org.springframework.security.authentication.ReactiveAuthenticationManager.class)
public class FeedbackApplicationR2dbcMongoDemoApplication {
	
	/*
	 * @Bean CommandLineRunner addEmpRole(AdminEmpRoleRespoistroy repo) {
	 * 
	 * return args ->{ repo.deleteAll() .subscribe(null,null,()->{ Stream.of(new
	 * EmployeeRole(1,1,"jayakannan","karuppasamy","856370@cognizant.com","admin"),
	 * new EmployeeRole(2,2,"jk","k","jkannan@cognizant.com","pmo"), new
	 * EmployeeRole(3,3,"jktest","test","jktest@cognizant.com","employee"))
	 * .forEach(empRole ->{ repo.save(empRole) .subscribe(System.out::println); });
	 * }); }; }
	 */

	public static void main(String[] args) {
		SpringApplication.run(FeedbackApplicationR2dbcMongoDemoApplication.class, args);
	}

}
