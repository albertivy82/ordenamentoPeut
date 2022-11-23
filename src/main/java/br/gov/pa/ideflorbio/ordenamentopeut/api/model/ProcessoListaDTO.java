package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoListaDTO {
	
    private Long id;
    
	private String processoPae;
	
	private String statusProcesso;
	 	
	private LocalizacaoListaProcessoDTO localizacao;
	
	private BeneficiarioNomeCpfDTO beneficiario;
	
	private List<IndenizacaoResumoDTO> indenizacoes;

}
