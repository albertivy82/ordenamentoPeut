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
@Table(name="orcamento")
public class Orcamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String ptres;
	
	@NotBlank
	private String fonte;
	
	@NotBlank
	@Column(name="elemento_despesa")
	private String elementoDespesa;
	
	
	//relacionamentos
	@JsonIgnore
	@OneToMany(mappedBy="orcamento")
	private List<Indenizacao> indenizacao;
	
	//getters, setters, equals e hash
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPtres() {
		return ptres;
	}
	public void setPtres(String ptres) {
		this.ptres = ptres;
	}
	public String getFonte() {
		return fonte;
	}
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	public String getElementoDespesa() {
		return elementoDespesa;
	}
	public void setElementoDespesa(String elementoDespesa) {
		this.elementoDespesa = elementoDespesa;
	}
	
	public List<Indenizacao> getIndenizacao() {
		return indenizacao;
	}
	public void setIndenizacao(List<Indenizacao> indenizacao) {
		this.indenizacao = indenizacao;
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
		Orcamento other = (Orcamento) obj;
		return Objects.equals(id, other.id);
	}
	

}
