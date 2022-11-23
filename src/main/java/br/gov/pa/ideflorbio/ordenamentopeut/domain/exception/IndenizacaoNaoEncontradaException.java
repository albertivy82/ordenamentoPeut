package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class IndenizacaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public IndenizacaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public IndenizacaoNaoEncontradaException(Long id) {
		this(String.format("indenizacao de código %d não existe", id));
	}

}
