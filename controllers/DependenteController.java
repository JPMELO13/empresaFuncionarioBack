package empresaFuncionario.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import empresaFuncionario.models.Dependente;
import empresaFuncionario.services.DependenteService;

@CrossOrigin
@RestController
@RequestMapping("empresaFuncionario")
public class DependenteController {
	
	@Autowired DependenteService dependenteService;
	
	@GetMapping("/dependente")
	public List<Dependente> BuscarTodosDependentes(){
		List<Dependente> deps = dependenteService.buscarTodosDependentes();
		return deps;
	}
	
	@GetMapping("/dependente/{depId}")
	public ResponseEntity<Dependente> buscarUmDependente(@PathVariable Integer depId){
		Dependente dep = dependenteService.buscarUmDependente(depId);
		return ResponseEntity.ok().body(dep);
	}
	
	@GetMapping("/dependente/funcionario/{func_id}")
	public List<Dependente> buscarDependentePorFuncionario(@PathVariable Integer func_id){
		List<Dependente> deps = dependenteService.buscarDependentesPorFuncionario(func_id);
		return deps;
	}
	
	@PostMapping("/dependente/funcionario/{func_id}")
	public ResponseEntity<Dependente> inserirDependente(@RequestBody Dependente dep, @PathVariable Integer func_id){
		dep = dependenteService.inserirDependente(dep, func_id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dep.getDepId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/dependente/{depId}")
	public ResponseEntity<Void> deletarUmDependente(@PathVariable Integer depId){
		dependenteService.deletarUmDependente(depId);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/dependente/{depId}")
	public ResponseEntity<Dependente> editarDependente(@PathVariable Integer depId, @RequestBody Dependente dep, @RequestParam(value="funcionario") Integer func_id){
		dep.setDepId(depId);
		dep = dependenteService.editarDependente(dep, func_id);
		return ResponseEntity.noContent().build();
		
	}
}
