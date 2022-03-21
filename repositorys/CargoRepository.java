package empresaFuncionario.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import empresaFuncionario.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo,Integer>{

}
