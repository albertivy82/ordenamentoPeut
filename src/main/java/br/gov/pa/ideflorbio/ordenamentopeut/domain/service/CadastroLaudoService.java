package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.LaudoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Laudo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LaudoRepository;


@Service
public class CadastroLaudoService {
	
	@Autowired
	LaudoRepository laudos;
	
	@Autowired
	CadastroProcessoService processos;
	
	private static final String ENTIDADE_EM_USO = "Conta Bancaia de código %d não pode ser removida, pois está em uso";
	
	
	public Laudo salvar(Laudo laudo) {
		
		Processo processo = processos.localizarEntidade(laudo.getProcesso().getId());	
		laudo.setProcesso(processo); 
	return laudos.save(laudo);
	}
	
	public Laudo LocalizarEntidade (Long id) {
		Laudo laudo = laudos.findById(id).orElseThrow(()-> new LaudoNaoEncontradoException(id));
		return laudo;
	}
	
	public void remover(Long id) {
		try {
			laudos.deleteById(id);
			laudos.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new LaudoNaoEncontradoException(String.format("Processo de código %d não existe", id));
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(ENTIDADE_EM_USO, id));
		}
	}

}
