package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndenizacaoResumoDTO {
	
	
	private BigDecimal valor;
	private ContaTitularDTO contaDeposito;
	private OrcamentoFonteDTO orcamento;
	

}
