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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Localizacao;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LocalizacaoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroLocalizacaoService;

@RestController
@RequestMapping("/localizacoes")
public class LocalizacaoController {
	
	@Autowired
	private LocalizacaoRepository localizacoes;
	
	@Autowired
	private CadastroLocalizacaoService cadastroLocalizacao;
	
	@GetMapping
	private List<Localizacao> listar(){
		return localizacoes.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	private Localizacao buscar(@PathVariable Long id){
		return cadastroLocalizacao.localizarEntidade(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Localizacao adicionar(@RequestBody @Valid Localizacao localizacao){
		return cadastroLocalizacao.salvar(localizacao);
		
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Localizacao atualizar(@PathVariable Long id, @RequestBody @Valid Localizacao localizacao){
		 Localizacao localizacaoProcurada = cadastroLocalizacao.localizarEntidade(id);
		 BeanUtils.copyProperties(localizacao, localizacaoProcurada, "id");
		 return cadastroLocalizacao.salvar(localizacaoProcurada);
	}
	
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	private void apagar(@PathVariable Long id){
		cadastroLocalizacao.remover(id);
	}
	

}
