package empresaFuncionario.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import empresaFuncionario.models.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer>{
	
	@Query(value = "SELECT * FROM db_empresa.dependente where func_id= :func_id", nativeQuery = true)
	List<Dependente> buscarDependentesFuncionario(Integer func_id);

}
