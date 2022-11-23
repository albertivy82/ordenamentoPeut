package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class LaudoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public LaudoNaoEncontradoException(String message) {
		super(message);
	}
	
	public LaudoNaoEncontradoException(Long id) {
		this(String.format("Laudo de código %d não existe", id));
	}

}
