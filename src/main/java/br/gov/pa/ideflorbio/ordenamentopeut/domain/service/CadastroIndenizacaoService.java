package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.IndenizacaoNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;

@Service
public class CadastroIndenizacaoService {
	
	@Autowired
	public IndenizacaoRepository indenizacoes;
	
	@Autowired
	private CadastroContaBancariaService pesquisarConta;
	
	@Autowired
	private CadastroProcessoService pesquisaProcesso;
	
	@Autowired
	private CadastroOrcamentoService pesquisaOrcamento;
	
	
	//--------MÉTODOS---------//
	//1__________________________________
	@Transactional
	public Indenizacao salvar(Indenizacao indenizacao) {
		
		ContaBancaria contaPesquisada = pesquisarConta.
				localizarEntidade(indenizacao.getContaDeposito().getId());
		
		Processo processopesquisado = pesquisaProcesso.
				localizarEntidade(indenizacao.getProcesso().getId());
		
		Orcamento orcamentoPesquisado = pesquisaOrcamento.
				localizarEntidade(indenizacao.getOrcamento().getId());
		
		indenizacao.setContaDeposito(contaPesquisada);
		indenizacao.setProcesso(processopesquisado);
		indenizacao.setOrcamento(orcamentoPesquisado);
		
		return indenizacoes.save(indenizacao);
	}
	
	//2____________________________________
	public Indenizacao localizarEntidade(Long id) {
		return indenizacoes.findById(id).
				orElseThrow(()-> new IndenizacaoNaoEncontradaException(id));
	}
	
	//3________________________________
	@Transactional
	public void remover(Long id) {
		
		try {
		indenizacoes.deleteById(id);
		indenizacoes.flush();
		}catch(DataIntegrityViolationException e) {
			throw new IndenizacaoNaoEncontradaException(id);
		}catch(EmptyResultDataAccessException e) {
			throw new IndenizacaoNaoEncontradaException(id);
		}
	}

}
