package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {
	
private Long id;
	
	private String banco;
	
	private String conta;
	
	private String tipoConta;
	
	private String agencia;
	
	private BeneficiarioProcessoDTO beneficiario;

}
