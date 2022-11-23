package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaTitularDTO {
	
	private Long id;
	
	private BeneficiarioNomeCpfDTO beneficiario;

}
