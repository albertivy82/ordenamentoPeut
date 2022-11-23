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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Laudo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.LaudoRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroLaudoService;

@RestController
@RequestMapping("/laudos")
public class LaudoController {
	
	@Autowired
	CadastroLaudoService cadastroLaudos;
	
	@Autowired
	LaudoRepository laudos;
	
	@GetMapping
	public List<Laudo> listar(){
		return laudos.findAll();
	}
	
	@GetMapping("/{id}")
	public Laudo buscar(@PathVariable Long id) {
		return cadastroLaudos.LocalizarEntidade(id);
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Laudo adcionar (@RequestBody @Valid Laudo laudo) {
		
		try {
			return cadastroLaudos.salvar(laudo);
		}catch(EntidadeNaoEncontradaException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Laudo atualizar(@PathVariable Long id, @RequestBody @Valid Laudo laudoRecebido) {
		try {
		Laudo laudoProcurado = cadastroLaudos.LocalizarEntidade(id);
		BeanUtils.copyProperties(laudoRecebido, laudoProcurado, "id");
		return cadastroLaudos.salvar(laudoProcurado);
		}catch(EntidadeNaoEncontradaException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id){
		cadastroLaudos.remover(id);
	}
	
	
	

}
