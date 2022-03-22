package empresaFuncionario.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import empresaFuncionario.models.Dependente;

public interface DependenteRepository extends JpaRepository<Dependente, Integer>{
	
	@Query(value = "SELECT * FROM db_empresa.dependente where func_id= :func_id", nativeQuery = true)
	List<Dependente> buscarDependentesFuncionario(Integer func_id);
	
	@Query(value = "SELECT dep_id,dep_idade,dep_nome,dep_relacao,func_id FROM dependente where dep_id =:depId", nativeQuery = true)
	Optional<Object> DependenteComFunc(Integer depId);

}
