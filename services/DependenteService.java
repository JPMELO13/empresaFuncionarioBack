package empresaFuncionario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import empresaFuncionario.models.Dependente;
import empresaFuncionario.models.Funcionario;
import empresaFuncionario.repositorys.DependenteRepository;

@Service
public class DependenteService {
	@Autowired
	private DependenteRepository dependenteRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public List<Dependente> buscarTodosDependentes(){
		return dependenteRepository.findAll();
	}
	
	public Dependente buscarUmDependente(Integer depId){
		Optional<Dependente> dep = dependenteRepository.findById(depId);
		return dep.orElseThrow();
	}
	public Object buscarUmDependenteFunc(Integer depId){
		Optional<Object> dep = dependenteRepository.DependenteComFunc(depId);
		System.out.println(dep);
		return dep.orElseThrow();
	}
	
	public List<Dependente> buscarDependentesPorFuncionario(Integer func_id){
		List<Dependente> deps = dependenteRepository.buscarDependentesFuncionario(func_id);
		return deps;
	}
	
	public Dependente inserirDependente(Dependente dep, Integer func_id) {
		dep.setDepId(null);
		Funcionario func = funcionarioService.showOneFuncionario(func_id);
		dep.setFuncionario(func);
		return dependenteRepository.save(dep);
	}
	
	public void deletarUmDependente(Integer depId) {
		dependenteRepository.deleteById(depId);
	}
	
	public Dependente editarDependente(Dependente dep, Integer func_id) {
		buscarUmDependente(dep.getDepId());
		Funcionario func = funcionarioService.showOneFuncionario(func_id);
		dep.setFuncionario(func);
		return dependenteRepository.save(dep);
	}
	
}
