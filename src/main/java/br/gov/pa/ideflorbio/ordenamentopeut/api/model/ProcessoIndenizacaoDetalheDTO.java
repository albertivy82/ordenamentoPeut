package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoIndenizacaoDetalheDTO {
	
    private Long id;
	
	private String processoPae;
	
	private String processoOriginal;
	
	private String numeroLote;
	
	private String statusLote;
	
	private String statusProcesso;
	
	private BeneficiarioProcessoDTO beneficiario;
	
	private List<IndenizacaoCompletaDTO> indenizacoes;


}
