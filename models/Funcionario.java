package empresaFuncionario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario {
////////////atributos da classe aluno - ao invés de criar lá no mysql, cria aqui pelo java
/// os modificadores de acesso serão private e para alterações usaremos os sets

//////anotação @Id diz que o atributo ra_aluno é a chave primária
///// anotação @GeneratedValue(strategy = GenerationType.IDENTITY) diz que o valor será autoincrementado
/// e a estratágia é que a geração será pelo springBoot
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer func_id;

//////anotação column diz que o atributo al_nome será uma coluna da tabela
////os parâmetros-> nullabe como false diz que ele NÃO PODE ser nulo 
/// e o length é o tamanho máxcimo da string - o default é 255 e isso é o que ele pega na memória
	@Column(nullable = false, length = 60)
	private String func_nome;

//////anotação column diz que o atributo al_cidade será uma coluna da tabela
////os parâmetros-> nullabe como false diz que ele NÃO PODE ser nulo 
/// e o length é o tamanho máxcimo da string - o default é 255 e isso é o que ele pega na memória	
	@Column(nullable = false, length = 30)
	private String func_cidade;

//////anotação column diz que o atributo al_turma será uma coluna da tabela
////os parâmetros-> nullabe como false diz que ele NÃO PODE ser nulo 
/// e o length é o tamanho máxcimo da string - o default é 255 e isso é o que ele pega na memória
//@Column(nullable = false, length = 20)
//private String al_turma;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Cargo cargo;

	public Integer getFunc_id() {
		return func_id;
	}

	public void setFunc_id(Integer func_id) {
		this.func_id = func_id;
	}

	public String getFunc_nome() {
		return func_nome;
	}

	public void setFunc_nome(String func_nome) {
		this.func_nome = func_nome;
	}

	public String getFunc_cidade() {
		return func_cidade;
	}

	public void setFunc_cidade(String func_cidade) {
		this.func_cidade = func_cidade;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	
	
}
