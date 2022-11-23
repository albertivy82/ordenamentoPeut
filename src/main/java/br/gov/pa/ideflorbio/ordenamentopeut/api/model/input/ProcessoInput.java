package br.gov.pa.ideflorbio.ordenamentopeut.api.model.input;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProcessoInput {
	
	
	@NotBlank
	private String processoPae;
	
	@NotBlank
	private String processoOriginal;
	
	@NotBlank
	private String numeroLote;
	
	@NotBlank
	private String statusLote;
	
	@NotBlank
	private String statusProcesso;
	
	@Valid
	@NotNull
	private LocalizacaoIdInput localizacao;
	
	@Valid
	@NotNull
	private BeficiarioIdInput beneficiario;
	
}
