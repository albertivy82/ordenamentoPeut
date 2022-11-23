package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>{
	
	
	
	@Query(value = "SELECT * FROM processo INNER JOIN beneficiario ON processo.beneficiarios = "
			+ "beneficiario.id WHERE beneficiario.nome LIKE :nome", nativeQuery = true)
	Optional<Processo> findByBeneficiario(String nome);
	
	
	Optional<Processo> findByProcessoPae(String processoPae);
	
	
	
}
