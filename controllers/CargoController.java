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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import empresaFuncionario.models.Cargo;
import empresaFuncionario.services.CargoService;


@CrossOrigin
@RestController
@RequestMapping("empresaFuncionario")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	 
	@GetMapping("/cargo")
	public List<Cargo> mostrarTodosCargos(){
		List<Cargo> cargo = cargoService.mostrarTodosCargos();
		return cargo;
	}
	
	@GetMapping("/cargo/{car_id}")
	public ResponseEntity<Cargo> buscarUmCargo(@PathVariable Integer car_id){
		Cargo cargo = cargoService.buscarUmCargo(car_id);
		return ResponseEntity.ok().body(cargo);
	}
	
	@PostMapping("/cargo")
	public ResponseEntity<Cargo> cadastrarCargo(@RequestBody Cargo cargo){
		cargo = cargoService.cadastrarCargo(cargo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getCar_id()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/cargo/{car_id}")
	public ResponseEntity<Cargo> editarTurma(@PathVariable Integer car_id, @RequestBody Cargo cargo){
		cargo.setCar_id(car_id);
		cargo = cargoService.editarCargo(cargo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/cargo/{car_id}")
	public ResponseEntity<Void> deletarUmaTurma(@PathVariable Integer car_id){
		cargoService.deletarUmCargo(car_id);
		return ResponseEntity.noContent().build();
	}

}
