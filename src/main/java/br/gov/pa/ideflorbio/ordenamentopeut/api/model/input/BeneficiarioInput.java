package br.gov.pa.ideflorbio.ordenamentopeut.api.model.input;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiarioInput {

	@NotBlank
	private String nome;
	@NotBlank
	private String cpf;
	@NotBlank
	private String rg;
	@NotNull
	private Perfil perfil;
	

}
