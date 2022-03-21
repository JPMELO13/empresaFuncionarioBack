package empresaFuncionario.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cargo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer car_id;
	
	@Column(nullable=false, length=30)
	private String car_nome;
	
	@Column(nullable=false, length=100)
	private String car_atribuicao;
	
	@OneToMany(mappedBy="cargo")
	private List<Funcionario> funcionario = new ArrayList<>();

	public Integer getCar_id() {
		return car_id;
	}

	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}

	public String getCar_nome() {
		return car_nome;
	}

	public void setCar_nome(String car_nome) {
		this.car_nome = car_nome;
	}

	public String getCar_atribuicao() {
		return car_atribuicao;
	}

	public void setCar_atribuicao(String car_atribuicao) {
		this.car_atribuicao = car_atribuicao;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

}
