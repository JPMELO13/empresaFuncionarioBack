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

import empresaFuncionario.models.Cargo;
import empresaFuncionario.models.Funcionario;
import empresaFuncionario.repositorys.FuncionarioRepository;
import empresaFuncionario.services.CargoService;
import empresaFuncionario.services.FuncionarioService;

//anotação referente ao erro de CORS
@CrossOrigin
//anotação para dizer que essa classe é um controller
@RestController
//anotação para dizer que nossa rota será por esse endPoint
@RequestMapping("empresaFuncionario")


public class FuncionarioController {
	
	// precisamos fazer a injeção de dependencias do service e do repository
		@Autowired
		private FuncionarioRepository funcionarioRepository;
		@Autowired
		private FuncionarioService funcionarioService;
		@Autowired
		private CargoService cargoService;

		// anotação do método GetMapping - significa usar o método get do HTTP e mapear
		// para a rota /aluno
		@GetMapping("/funcionario")
		public List<Funcionario> showAllFuncionarios() {
			List<Funcionario> funcionarios = funcionarioService.showAllFuncionarios();
			return funcionarios;
		}
		
		@GetMapping("/funcionario-cargo")
		public List<List> funcionariosComCargo(){
			List<List> funcionaariosCargo = funcionarioService.funcionariosComCargo();
			return funcionaariosCargo;
		}
		
		

		// anotação com a rota em que conseguiremos acessar as infos desse aluno
		@GetMapping("/funcionario/{func_id}")
		// como é apenas um aluno específico o tipo de retorno é ResponseEntity
		// ResponseEntity retorna os dados reais de um registro do BD, retorna uma
		// resposta inteira - incluindo cabeçalho, corpo e status
		// o caractér curinga genérico <?> ele sempre traz uma resposta, seja ela de
		// sucesso ou de erro
		//////////// o pathVariable é para dizer que vai ser passado através do
		// paramêtro da url
		public ResponseEntity<?> showOneFuncionario(@PathVariable Integer func_id) {
			Funcionario funcionario = funcionarioService.showOneFuncionario(func_id);
			return ResponseEntity.ok().body(funcionario);
		}
		
		@GetMapping("/funcionarioComCargo/{func_id}")
		public ResponseEntity<?> funcionarioComCargo(@PathVariable Integer func_id) {
			Object funcionario = funcionarioService.buscarUmFuncionarioComCargo(func_id);
			return ResponseEntity.ok().body(funcionario);
		}
		
		@GetMapping("/funcionario/busca-cargo/{car_id}")
		public List<Funcionario> buscarPorCargo(@PathVariable Integer car_id){
			List<Funcionario> funcionario = funcionarioService.buscarPorCargo(car_id);
			return funcionario;
		}


		
		@PostMapping("/funcionario")
		public ResponseEntity<Funcionario>inserirFuncionario(@RequestParam(value="cargo") Integer id_cargo,@RequestBody Funcionario funcionarios){
			
			funcionarios = funcionarioService.inserirFuncionario(id_cargo,funcionarios);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/funcionario/{id}").buildAndExpand(funcionarios.getFunc_id())
					.toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		//anotação com o método de delete
		@DeleteMapping("/funcionario/{func_id}")
		public ResponseEntity<Void> deleteFuncionario(@PathVariable Integer func_id){
			funcionarioService.deleteFuncionario(func_id);
			return ResponseEntity.noContent().build();
		}
		
//		@PutMapping("/funcionario/{func_id}")
//		public ResponseEntity<Void> editarFuncionario(@RequestBody Funcionario funcionarios, @RequestParam(value="cargo") Integer id_cargo){
//			funcionarios.setFunc_id(func_id);
//			funcionarios = funcionarioService.editarFuncionario(funcionarios);
//			System.out.println(funcionarios.getCargo());
//			return ResponseEntity.noContent().build();
//		}
		
		@PutMapping("/funcionario/{func_id}")
		public ResponseEntity<Void> editarFuncionario(@PathVariable Integer func_id, @RequestBody Funcionario funcionarios, @RequestParam(value="cargo") Integer id_cargo){
			Cargo cargo = cargoService.buscarUmCargo(id_cargo);
			funcionarios.setFunc_id(func_id);
			funcionarios.setCargo(cargo);
			funcionarios = funcionarioService.editarFuncionario(funcionarios);
			return ResponseEntity.noContent().build();
		}

}
