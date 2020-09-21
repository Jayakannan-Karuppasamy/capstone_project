package learn.reactive.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import learn.reactive.demo.constant.FeedbackConstant;
import learn.reactive.demo.model.EmployeeRole;
import learn.reactive.demo.repoistory.AdminEmpRoleRespoistroy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class AdminRoleMapperTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	
	@Autowired
	AdminEmpRoleRespoistroy adminEmpRoleRepo;
	
	public List<EmployeeRole> data(){
		return null;
	}

	//@Before
	public void initSetUp() {
		adminEmpRoleRepo.deleteAll()
			.thenMany(Flux.fromIterable(data()))
			.flatMap(adminEmpRoleRepo::save)
			.doOnNext((role) -> {
				System.out.println("Inserted the employee role for Test"+role);
			}).blockLast();
	}

	@Test
	public void getAllRole() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(EmployeeRole.class)
			.hasSize(4);
	}
	
	@Test
	public void getAllRole_approach1() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(EmployeeRole.class)
			.hasSize(4).consumeWith((response) -> {
				List<EmployeeRole> roles= response.getResponseBody();
				roles.forEach((r) -> {
					assertTrue(r.getEmployeeId() !=null);
				});
			});
	}
	
	@Test
	public void getAllRole_approach2() {
		Flux<EmployeeRole> empRoleFlux=webTestClient.get().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.returnResult(EmployeeRole.class)
			.getResponseBody();
		
		StepVerifier.create(empRoleFlux.log("Value from Employee Role :::"))
					.expectNextCount(4)
					.verifyComplete();
	}
	
	@Test
	public void getRoleByEmpID() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/{id}"),1)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.empEmailId", "856370@cognizant.com");
	}

	@Test
	public void getRoleByEmpID_NOTFOUND() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/{id}"),3)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	public void createEmpRole() {
		EmployeeRole role=new EmployeeRole(5,5,"FifthEmployee","LastEmployee","FifthEmployee@gmail.com","POC");
		webTestClient.post().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/createRole"))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(role), EmployeeRole.class)
			.exchange()
			.expectStatus().isCreated()
			.expectBody()
			.jsonPath("$.employeeId").isNotEmpty()
			.jsonPath("$.empEmailId").isEqualTo("FifthEmployee@gmail.com");
				
	}
	
	@Test
	public void deleteRole() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/deleteEmpId/{id}"),3)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Void.class);
	}
	
	@Test
	public void deleteRole_NOTFOUND() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/deleteEmpId/{id}"),6)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	//Update the role by employeeId
	@Test
	public void updateEmployeeRole() {
		EmployeeRole role=new EmployeeRole(1,1,"jayakannan","karuppasamy","856370@cognizant.com","ADMIN");
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/updateRole/{id}"),1)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(role),EmployeeRole.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.empRole", "ADMIN");
	}
	
	@Test
	public void updateEmployeeRole_NOTFOUND() {
		EmployeeRole role=new EmployeeRole(3,3,"jayakannan","karuppasamy","856370@cognizant.com","ADMIN");
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_ROLE_ENDPOINT.concat("/updateRole/{id}"),3)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(role),EmployeeRole.class)
			.exchange()
			.expectStatus().isNotFound();
		
	}
}
