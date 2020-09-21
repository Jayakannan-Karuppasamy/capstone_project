package learn.reactive.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import learn.reactive.demo.constant.FeedbackConstant;
import learn.reactive.demo.model.EmployeeRole;
import learn.reactive.demo.repoistory.AdminEmpRoleRespoistroy;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(FeedbackConstant.ADMIN_ROLE_ENDPOINT)
public class AdminRoleMapper {

	@Autowired
	private AdminEmpRoleRespoistroy adminEmpRoleRepo;
	
	@GetMapping("/all")
	public Flux<EmployeeRole> getAllEmployeeRole() {
		return adminEmpRoleRepo.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ResponseEntity<EmployeeRole>> getEmployeeId(@PathVariable Integer id) {
		return adminEmpRoleRepo.findByemployeeId(id).map(fetchId -> ResponseEntity.ok(fetchId))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping("/createRole")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<EmployeeRole> addRole(@RequestBody EmployeeRole role) {
		return adminEmpRoleRepo.save(role);
	}

	@PutMapping("/updateRole/{id}")
	public Mono<ResponseEntity<EmployeeRole>> updateRoleByEmpId(@PathVariable Integer id,
			@RequestBody EmployeeRole role) {
		return adminEmpRoleRepo.findByemployeeId(id).flatMap(existingEmpRole -> {
			existingEmpRole.setEmpRole(role.getEmpRole());
			return adminEmpRoleRepo.save(existingEmpRole);
		}).map(updateRole -> new ResponseEntity<>(updateRole, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/deleteEmpId/{id}")
	public Mono<ResponseEntity<Void>> deleteEmpId(@PathVariable Integer id){
		return adminEmpRoleRepo.findByemployeeId(id)
				.flatMap(existingEmp->adminEmpRoleRepo.delete(existingEmp).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
						).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
}
