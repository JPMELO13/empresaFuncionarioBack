package empresaFuncionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import empresaFuncionario.models.Cargo;
import empresaFuncionario.models.Funcionario;
import empresaFuncionario.repositorys.FuncionarioRepository;

//anotação informando que essa classe é um serviço
@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoService cargoService;
	
	//listar todos funcionarios
	public List<Funcionario> showAllFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	public List<List> funcionariosComCargo(){
		return funcionarioRepository.funcionariosComCargo();
	}
	
	public Funcionario showOneFuncionario(Integer func_id) {
		Optional<Funcionario> funcionarios = funcionarioRepository.findById(func_id);
		return funcionarios.orElseThrow();
	}
	
	
	public Funcionario inserirFuncionario(Integer id_cargo, Funcionario funcionario) {
		funcionario.setFunc_id(null);
		Cargo cargo = cargoService.buscarUmCargo(id_cargo);
		funcionario.setCargo(cargo);
		return funcionarioRepository.save(funcionario);
	}
	
	public void deleteFuncionario(Integer func_id) {
		funcionarioRepository.deleteById(func_id);
	}

	public Funcionario editarFuncionario(Funcionario funcionarios) {
		showOneFuncionario(funcionarios.getFunc_id());
		return funcionarioRepository.save(funcionarios);
	}
	
	public List<Funcionario> buscarPorCargo(Integer car_id){
		List<Funcionario> funcionario = funcionarioRepository.fetchByCargo(car_id);
		return funcionario;
	}
	
	public Object buscarUmFuncionarioComCargo(Integer func_id) {
		Optional<Object> funcionario = funcionarioRepository.funcionarioComCargo(func_id);
		return funcionario.orElseThrow();
	}
}
