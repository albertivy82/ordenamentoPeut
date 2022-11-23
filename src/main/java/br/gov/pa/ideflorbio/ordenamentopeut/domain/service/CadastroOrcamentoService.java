package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.OrcamentoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;

@Service
public class CadastroOrcamentoService {
	
	@Autowired
	private OrcamentoRepository orcamentos;
	
	
	//-------MÉTODOS--------//
	
	//1____________________________________________________
	@Transactional
	public Orcamento salvar(Orcamento orcamento) {
		return orcamentos.save(orcamento);
	}
	
	//2____________________________________________________
	public Orcamento localizarEntidade(Long id) {
		return orcamentos.findById(id).orElseThrow(()-> new OrcamentoNaoEncontradoException(id));
	}
	
	//3_____________________________________________________
	@Transactional
	public void remover(Long id) {
		try {
			orcamentos.deleteById(id);
			orcamentos.flush();
		}catch(DataIntegrityViolationException e) {
			throw new OrcamentoNaoEncontradoException(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("Orçamento de código %d não existe", id));
		}
	}

}
