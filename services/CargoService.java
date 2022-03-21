package empresaFuncionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import empresaFuncionario.models.Cargo;
import empresaFuncionario.repositorys.CargoRepository;

@Service
public class CargoService {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public List<Cargo> mostrarTodosCargos(){
		return cargoRepository.findAll();
	}
	
	
	public Cargo buscarUmCargo(Integer car_id) {
		Optional<Cargo> cargo = cargoRepository.findById(car_id);
		return cargo.orElseThrow();
		
	}
	
	public Cargo cadastrarCargo(Cargo cargo) {
		// é uma forma de segurança para não setarmos o id
		cargo.setCar_id(null);
		return cargoRepository.save(cargo);
	}

	public Cargo editarCargo(Cargo cargo) {
		buscarUmCargo(cargo.getCar_id());
		return cargoRepository.save(cargo);
	}
	
	public void deletarUmCargo(Integer car_id) {
		cargoRepository.deleteById(car_id);
	}

}
