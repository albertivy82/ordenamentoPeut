package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Orcamento;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.OrcamentoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroOrcamentoService;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {
	
	@Autowired
	private OrcamentoRepository orcamento;
	
	@Autowired
	private CadastroOrcamentoService cadastroOrcamento;
	
	
	
	//1_______________________________________________________
	@GetMapping
	public List<Orcamento> listar(){
		return orcamento.findAll();
	}
	//2________________________________________________________
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Orcamento buscar(@PathVariable Long id){
		return cadastroOrcamento.localizarEntidade(id);
	}
	
	//3______________________________________________________
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Orcamento adicionar(@RequestBody @Valid Orcamento orcamento){
		return cadastroOrcamento.salvar(orcamento);
	}
	
	//4______________________________________________________
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Orcamento atualizar(@PathVariable Long id, @RequestBody @Valid Orcamento orcamentoEnviado){
		Orcamento orcamentoProcurado = cadastroOrcamento.localizarEntidade(id);
		BeanUtils.copyProperties(orcamentoEnviado, orcamentoProcurado, "id");
		return cadastroOrcamento.salvar(orcamentoProcurado);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id){
		cadastroOrcamento.remover(id);
	}

}
