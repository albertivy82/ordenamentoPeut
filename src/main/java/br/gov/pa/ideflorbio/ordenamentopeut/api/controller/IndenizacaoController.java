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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.IndenizacaoNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Indenizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.IndenizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroIndenizacaoService;

@RestController
@RequestMapping("/indenizacoes")
public class IndenizacaoController {
	
	@Autowired
	private IndenizacaoRepository indenizacoes;
	
	@Autowired
	private CadastroIndenizacaoService cadastroIndenizacao;
	
	@GetMapping
	public List<Indenizacao> listar(){
		return indenizacoes.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Indenizacao buscar(@PathVariable Long id){
		return cadastroIndenizacao.localizarEntidade(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Indenizacao adicionar(@RequestBody @Valid Indenizacao indenizacao){
		return cadastroIndenizacao.salvar(indenizacao);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Indenizacao atualizar(@PathVariable Long id, @RequestBody @Valid Indenizacao indenizacao){
		
		Indenizacao indenizacaoProcurada = cadastroIndenizacao.localizarEntidade(id);
		try {
		BeanUtils.copyProperties(indenizacao, indenizacaoProcurada, "id");
		return cadastroIndenizacao.salvar(indenizacaoProcurada);
		}catch(IndenizacaoNaoEncontradaException e){	
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id ){
		cadastroIndenizacao.remover(id);
	}
	
		
}
