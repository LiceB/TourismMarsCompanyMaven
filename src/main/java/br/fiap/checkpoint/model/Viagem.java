package br.fiap.checkpoint.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

import br.fiap.checkpoint.repository.ViagemRepository;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Entity
public class Viagem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Pattern(regexp = "[0-9]{2}[/][0-9]{2}[/][0-9]{4}")
	private String dateDecolagem;
	private String estadia;

	private String retorno;

	@Min(value = 3, message = "A quantidade de assentos precisa ser multiplo de 3")
	private String assento;

	@Column(name = "modelo_nave")
	@Pattern(regexp = "[a-zA-Z]{4}[-][0-9]{4}")
	private String modeloNave;

	private String primeiroComandante;

	@Pattern(regexp = "[a-zA-Z]{3}[-][0-9]{4}[-][a-zA-Z][0-9][a-zA-Z]")
	private String matriculaComandante1;

	private String paisPrimeiro;

	@Pattern(regexp = "[0-9]{4}")
	private String nascimentoPrimeiro;

	private String segundoComandante;

	@Pattern(regexp = "[a-zA-Z]{3}[-][0-9]{4}[-][a-zA-Z][0-9][a-zA-Z]")
	private String matriculaComandante2;

	private String paisSegundo;

	@Pattern(regexp = "[0-9]{4}")
	private String nascimentoSegundo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateDecolagem() {
		return dateDecolagem;
	}

	public void setDateDecolagem(String dateDecolagem) {
		this.dateDecolagem = dateDecolagem;
	}

	public String getEstadia() {
		return estadia;
	}

	public void setEstadia(String estadia) {
		this.estadia = estadia;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getAssento() {
		return assento;
	}

	public void setAssento(String assento) {
		this.assento = assento;
	}

	public String getModeloNave() {
		return modeloNave;
	}

	public void setModeloNave(String modeloNave) {
		this.modeloNave = modeloNave;
	}

	public String getPrimeiroComandante() {
		return primeiroComandante;
	}

	public void setPrimeiroComandante(String primeiroComandante) {
		this.primeiroComandante = primeiroComandante;
	}

	public String getMatriculaComandante1() {
		return matriculaComandante1;
	}

	public void setMatriculaComandante1(String matriculaComandante1) {
		this.matriculaComandante1 = matriculaComandante1;
	}

	public String getPaisPrimeiro() {
		return paisPrimeiro;
	}

	public void setPaisPrimeiro(String paisPrimeiro) {
		this.paisPrimeiro = paisPrimeiro;
	}

	public String getNascimentoPrimeiro() {
		return nascimentoPrimeiro;
	}

	public void setNascimentoPrimeiro(String nascimentoPrimeiro) {
		this.nascimentoPrimeiro = nascimentoPrimeiro;
	}

	public String getSegundoComandante() {
		return segundoComandante;
	}

	public void setSegundoComandante(String segundoComandante) {
		this.segundoComandante = segundoComandante;
	}

	public String getMatriculaComandante2() {
		return matriculaComandante2;
	}

	public void setMatriculaComandante2(String matriculaComandante2) {
		this.matriculaComandante2 = matriculaComandante2;
	}

	public String getPaisSegundo() {
		return paisSegundo;
	}

	public void setPaisSegundo(String paisSegundo) {
		this.paisSegundo = paisSegundo;
	}

	public String getNascimentoSegundo() {
		return nascimentoSegundo;
	}

	public void setNascimentoSegundo(String nascimentoSegundo) {
		this.nascimentoSegundo = nascimentoSegundo;
	}

	public Viagem atualizar(Long idViagem, ViagemRepository viagemRepository) {
		Viagem viagem = viagemRepository.getOne(idViagem);
		viagem.setDateDecolagem(this.dateDecolagem);
		viagem.setAssento(this.assento);
		viagem.setEstadia(this.estadia);
		viagem.setPrimeiroComandante(this.primeiroComandante);
		viagem.setSegundoComandante(this.segundoComandante);
		return viagem;
	}

}
