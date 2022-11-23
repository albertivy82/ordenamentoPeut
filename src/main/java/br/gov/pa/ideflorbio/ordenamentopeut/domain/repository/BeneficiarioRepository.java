package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{
	
	Optional<Beneficiario> findByNome(String nome);;
}
