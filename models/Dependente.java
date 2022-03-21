package empresaFuncionario.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Dependente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer depId;
	
	@Column(nullable=false, length=100)
	private String depNome;
	
	@Column(nullable=false, length=100)
	private String depRelacao;
	
	@Column(nullable=false)
	private Integer depIdade;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "func_id")
	private Funcionario funcionario;

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	public String getDepNome() {
		return depNome;
	}

	public void setDepNome(String depNome) {
		this.depNome = depNome;
	}

	public String getDepRelacao() {
		return depRelacao;
	}

	public void setDepRelacao(String depRelacao) {
		this.depRelacao = depRelacao;
	}

	public Integer getDepIdade() {
		return depIdade;
	}

	public void setDepIdade(Integer depIdade) {
		this.depIdade = depIdade;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
