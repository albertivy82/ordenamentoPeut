package br.gov.pa.ideflorbio.ordenamentopeut.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Laudo;

@Repository
public interface LaudoRepository extends JpaRepository<Laudo, Long>{

}
