package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.ContaBancariaNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.IndenizacaoNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;

@Service
public class CadastroContaBancariaService {
	
	private static final String ENTIDADE_EM_USO = "Conta Bancaia de código %d não pode ser removida, pois está em uso";

//-------------------------------------------------------------------------------------------------//
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@Autowired
	private CadastroBeneficiarioService beneficiario;
	
//-------------------------------------------------------------------------------------------------//	
	//1____________________________________________________________________
	@Transactional
	public ContaBancaria salvar(ContaBancaria contaBancaria) {
		
		Beneficiario beneficiarioPesquisado = beneficiario.
				localizarEntidade(contaBancaria.getBeneficiario().getId());
				     
		contaBancaria.setBeneficiario(beneficiarioPesquisado);
				
		return contasBancarias.save(contaBancaria);
	}
//**********************************************************************************************//	
	//2___________________________________________________________________
	@Transactional
	public void excluir(Long id) {
		try {
			contasBancarias.deleteById(id);
			contasBancarias.flush();
		}catch(EmptyResultDataAccessException e) {
			throw new IndenizacaoNaoEncontradaException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format(ENTIDADE_EM_USO, id));
		}
	}
//************************************************************************************************//
	
	public ContaBancaria localizarEntidade(Long id) {
		ContaBancaria contaLocalizada = contasBancarias.findById(id).
				orElseThrow(()-> new ContaBancariaNaoEncontradaException(id));
		return contaLocalizada;
	}
}
