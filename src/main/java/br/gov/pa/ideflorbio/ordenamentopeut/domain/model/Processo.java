package br.gov.pa.ideflorbio.ordenamentopeut.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="processo")
public class Processo implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="processo_pae")
	@NotBlank
	private String processoPae;
	
	@NotBlank
	@Column(name="processo_original")
	private String processoOriginal;
	
	@NotBlank
	@Column(name="numero_lote")
	private String numeroLote;
	
	@NotBlank
	@Column(name="status_lote")
	private String statusLote;
	
	@NotBlank
	@Column(name="status_processo")
	private String statusProcesso;
	
	//relacionamentos
	
	@Valid
	@NotNull
	@ManyToOne
	@JoinColumn(name="localizacao")
	private Localizacao localizacao;
	
	@Valid
	@NotNull
	@OneToOne
	@JoinColumn(name="beneficiarios")
	private Beneficiario beneficiario;
	
	@JsonIgnore
	@OneToMany(mappedBy="processo")
	private List<Indenizacao> indenizacoes;
	
	@OneToMany(mappedBy="processo")
	private List<Imagens> imagens;
	
	
	//getters, setters, equal e hash
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProcessoPae() {
		return processoPae;
	}
	public void setProcessoPae(String processoPae) {
		this.processoPae = processoPae;
	}
	public String getProcessoOriginal() {
		return processoOriginal;
	}
	public void setProcessoOriginal(String processoOriginal) {
		this.processoOriginal = processoOriginal;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getStatusLote() {
		return statusLote;
	}
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}
	public String getStatusProcesso() {
		return statusProcesso;
	}
	public void setSatusProcesso(String satusProcesso) {
		this.statusProcesso = satusProcesso;
	}
	public void setStatusProcesso(String statusProcesso) {
		this.statusProcesso = statusProcesso;
	}
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	public Beneficiario getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Beneficiario beneficiario) {
		this.beneficiario = beneficiario;
	}
	public List<Indenizacao> getIndenizacoes() {
		return indenizacoes;
	}
	public void setIndenizacoes(List<Indenizacao> indenizacoes) {
		this.indenizacoes = indenizacoes;
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
		Processo other = (Processo) obj;
		return Objects.equals(id, other.id);
	}
}
