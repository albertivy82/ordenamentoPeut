package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class BeneficiarioNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public BeneficiarioNaoEncontradoException(String message) {
		super(message);
	}
	
	public BeneficiarioNaoEncontradoException(Long id) {
		this(String.format("Beneficiario de código %d não existe", id));
	}

}
