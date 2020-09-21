package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.EmployeeRole;
import reactor.core.publisher.Mono;

public interface AdminEmpRoleRespoistroy extends ReactiveMongoRepository<EmployeeRole, Integer> {

	public Mono<EmployeeRole> findByemployeeId(Integer employeeId);
}
