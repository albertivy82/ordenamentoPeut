package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessoDTO {
	//para retorno do cadastro
	
	private Long id;
    
	private String processoPae;
	
	private String processoOriginal;
	
	private String statusProcesso;
	 	
	private LocalizacaoCadastroProcessoDTO localizacao;
	
	private BeneficiarioIdDTO beneficiario;
	
	private List<IndenizacaoIdDTO> indenizacoes;

}
