package br.gov.pa.ideflorbio.ordenamentopeut.domain.model.enums;

public enum Perfil {
	
	TITULAR("TITULAR ORIGINAL DO PROCESSO"),
	SUCESSOR("SUCEDEU O(A) TITULAR DO PROCESSO"),
	TERCEIROS("EMPRESTOU CONTA BANCÁRIA PARA A PERCEPÇÃO DA INDENIZAÇÃO. O TITULAR NÃO POSSUÍA CONTA PRÓPRIA.");
	
	private String descritor;
	
	private Perfil(String descritor) {
		this.descritor = descritor;
	}
	
	public String getDecritor() {
		return descritor;
	}
}
