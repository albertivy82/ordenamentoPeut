package br.gov.pa.ideflorbio.ordenamentopeut.api.model;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndenizacaoCompletaDTO {
	
    private Long id;
	private String nl;
	private Date dataNl;
	private String ne;
	private Date dataNe;
	private String ob;
	private Date dataOb;
	private Date dataAcordo;
	private BigDecimal valor;
	private String satusPagamento;
	private ContaDTO contaDeposito;
	private OrcamentoCompletoDTO orcamento;
	

}
