package br.gov.pa.ideflorbio.ordenamentopeut.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name="localizacao")
public class Localizacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String municiopio;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	@Column(name="status_logradouro")
	private String statusLogradouro;
	
	//relacionamentos
	@JsonIgnore
	@OneToMany
	(mappedBy="localizacao")
	private List<Processo> processo;
	
	
	//getters,setters,equals e hash
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getStatusLogradouro() {
		return statusLogradouro;
	}
	public void setStatusLogradouro(String statusLogradouro) {
		this.statusLogradouro = statusLogradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMuniciopio() {
		return municiopio;
	}
	public void setMuniciopio(String municiopio) {
		this.municiopio = municiopio;
	}
	public List<Processo> getProcesso() {
		return processo;
	}
	public void setProcesso(List<Processo> processo) {
		this.processo = processo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Localizacao other = (Localizacao) obj;
		return Objects.equals(id, other.id);
	}

}
