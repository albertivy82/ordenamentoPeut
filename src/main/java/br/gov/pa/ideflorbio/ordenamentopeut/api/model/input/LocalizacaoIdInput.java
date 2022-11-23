package br.gov.pa.ideflorbio.ordenamentopeut.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalizacaoIdInput {
	@NotNull
	private Long id;

}
