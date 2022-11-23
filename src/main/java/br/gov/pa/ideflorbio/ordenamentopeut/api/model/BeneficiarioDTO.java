package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiarioDTO {
	
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String rg;
	
	private String perfil;
	
	private List<ContaBancariaDeBeneficiarioDTO> conta;
	
	

}
