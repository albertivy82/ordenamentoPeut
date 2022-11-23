package br.gov.pa.ideflorbio.ordenamentopeut.domain.exception;

public class LocalizacaoNaoEncontradaException extends EntidadeNaoEncontradaException {
	
	private static final long serialVersionUID = 1L;

	public LocalizacaoNaoEncontradaException(String message) {
		super(message);
	}
	
	public LocalizacaoNaoEncontradaException(Long id) {
		this(String.format("Localizacao de código %d não existe", id));
	}

}
