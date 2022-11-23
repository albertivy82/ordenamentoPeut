package br.gov.pa.ideflorbio.ordenamentopeut.domain.service;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.BeneficiarioNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeEmUsoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.BeneficiarioRepository;

@Service
public class CadastroBeneficiarioService {

	private static final String ENTIDADE_EM_USO = "Beneficiario de código %d não pode ser removido, pois está em uso";

	@Autowired
	private BeneficiarioRepository beneficiarios;
	
	
	
	//------MÉTODOS------//
	//______________________________________________________
	@Transactional
	public Beneficiario salvar(Beneficiario beneficiario) {
		     
		return beneficiarios.save(beneficiario);
	}
	
	
	//_______________________________________________________
	public Beneficiario localizarEntidade(Long id) {
		Beneficiario  beneficiario = beneficiarios.findById(id).
				orElseThrow(()-> new BeneficiarioNaoEncontradoException(id));
			return beneficiario;
	}
	
	//_______________________________________________________
		public Beneficiario localizarBeneficiarioPorNome(String nome) {
			Beneficiario  beneficiario = beneficiarios.findByNome(nome).
					orElseThrow(()-> new BeneficiarioNaoEncontradoException(nome));
					
				return beneficiario;
		}
		
		public Page<Beneficiario> listarTodos(Pageable paginacao){
			
			return beneficiarios.findAll(paginacao);
			
		}
	
	
	
	//3________________________________________________________
	//@Transactional
	public void remover(Long id) {
		try {	
			beneficiarios.deleteById(id);
			beneficiarios.flush();
		}catch(EmptyResultDataAccessException e){
			throw new BeneficiarioNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.
					format(ENTIDADE_EM_USO, id));
		}
	}
}
