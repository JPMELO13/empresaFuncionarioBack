package empresaFuncionario.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import empresaFuncionario.models.Funcionario;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
	
	@Query(value = "SELECT * FROM funcionario WHERE car_id = :car_id", nativeQuery = true)
	List<Funcionario> fetchByCargo(Integer car_id);

	@Query(value = "SELECT func_id, func_nome,func_cidade,car_nome,car_atribuicao FROM cargo right JOIN funcionario ON funcionario.car_id = cargo.car_id;", nativeQuery = true)
	List<List>funcionariosComCargo();
	
	@Query(value = "SELECT func_id, func_nome,func_cidade,funcionario.car_id, car_nome,car_atribuicao FROM cargo right JOIN funcionario ON funcionario.car_id = cargo.car_id where funcionario.func_id = :func_id", nativeQuery = true)
	Optional<Object> funcionarioComCargo(Integer func_id);
}
