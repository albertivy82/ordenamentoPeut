package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class ContaBancariaNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public ContaBancariaNaoEncontradaException(String message) {
		super(message);
	}
	
	public ContaBancariaNaoEncontradaException(Long id) {
		this(String.format("Conta Bancaria de código %d não existe", id));
	}

}
