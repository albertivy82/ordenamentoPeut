package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class ProcessoNaoEncontradoException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public ProcessoNaoEncontradoException(String message) {
		super(message);
	}
	
	public ProcessoNaoEncontradoException(Long id) {
		this(String.format("processo de código %d não existe", id));
	}
	
	
}
