package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.LocalizacaoNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;


@Service 
public class CadastroLocalizacaoService {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
//------------------------------------------------------------------------------------//
	//1
	@Transactional
	public Localizacao salvar(Localizacao localizacao) {
		return localizacoes.save(localizacao);
	}
	//2
	public Localizacao localizarEntidade(Long id) {
		return localizacoes.findById(id).
				orElseThrow(()-> new LocalizacaoNaoEncontradaException(id));
	}
	
	//3
	@Transactional
	public void remover(Long id) {
		try {
			localizacoes.deleteById(id);
			localizacoes.flush();
		}catch(DataIntegrityViolationException e) {
			throw new LocalizacaoNaoEncontradaException(id);
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.
					format("A localização de código %d não existe", id));
		}
	}

}
