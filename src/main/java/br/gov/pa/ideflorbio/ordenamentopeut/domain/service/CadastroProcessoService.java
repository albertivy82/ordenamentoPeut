package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.ProcessoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ProcessoRepository;

@Service
public class CadastroProcessoService {
	
	@Autowired
	private ProcessoRepository processos;
	
	@Autowired
	private CadastroBeneficiarioService beneficiarios;
	
	@Autowired
	private CadastroLocalizacaoService localizacoes;
	
	
	
	private static final String ENTIDADE_EM_USO = "Conta Bancaia de código %d não pode ser removida, pois está em uso";
	
	//-----MÉTODOS------//
	
	//1________________________________________
	public Page<Processo> listarTodos(Pageable paginacao){
		return processos.findAll(paginacao);
	}
	
	//___________________________________________________
	public Processo localizarEntidade(Long id) {
		return processos.findById(id).
				orElseThrow(()-> new ProcessoNaoEncontradoException(id));
	}
	
	//______________________________________________________
	
	
	public Processo localizarEntidadePorNumero(String numProcesso) {
		return processos.findByProcessoPae(numProcesso)
				.orElseThrow(()-> new ProcessoNaoEncontradoException(numProcesso));
	}
		 
	
	//________________________________________________________________________
	public Processo localizarEntidadePorTitular(String Nome) {
		
		return processos.findByBeneficiario(Nome).
				orElseThrow(()-> new ProcessoNaoEncontradoException(Nome));
	
		
	}
	
	//____________________________________________________
		@Transactional
		public Processo salvar(Processo processo) {
			
			
			Beneficiario beneficiarioProcurado = beneficiarios.
					localizarEntidade(processo.getBeneficiario().getId());
			
			Localizacao localizacaoProcurada = localizacoes.
					localizarEntidade(processo.getLocalizacao().getId());
			
			
			processo.setBeneficiario(beneficiarioProcurado);
			processo.setLocalizacao(localizacaoProcurada);
			return processos.save(processo);
		}
	
	//___________________________________________________
	@Transactional
	public void remover(Long id) {
		
		try {
		processos.deleteById(id);
		processos.flush();
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format(ENTIDADE_EM_USO, id));
		}catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Processo de código %d não existe", id));
		}
	}
	

}
