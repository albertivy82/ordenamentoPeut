package br.gov.pa.ideflorbio.ordenamentopeut.api.controller;




import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.ProcessoDTO;
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.ProcessoIndenizacaoDetalheDTO;
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.ProcessoListaDTO;
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.input.ProcessoInput;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.EntidadeNaoEncontradaException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.NegocioException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.exception.ProcessoNaoEncontradoException;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Processo;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroProcessoService;



@RestController
@RequestMapping("/processos")
public class ProcessoController {
	
		
	@Autowired
	private CadastroProcessoService cadastroProcesso;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	public Page<ProcessoListaDTO> listarResumo(@PageableDefault(size=10) Pageable paginacao){
		return cadastroProcesso.listarTodos(paginacao).map(p->modelMapper.map(p, ProcessoListaDTO.class));
	}
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProcessoIndenizacaoDetalheDTO Buscar(@PathVariable Long id){
		
			Processo proceso = cadastroProcesso.localizarEntidade(id);
		
		return modelMapper.map(proceso, ProcessoIndenizacaoDetalheDTO.class);
	}
	
	@GetMapping("/titular/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public ProcessoIndenizacaoDetalheDTO BuscarTitular(@PathVariable String nome){
		
		Processo processoAchado =  cadastroProcesso.localizarEntidadePorTitular(nome);
		
	  return modelMapper.map(processoAchado, ProcessoIndenizacaoDetalheDTO.class);	
	}
	
	@GetMapping("/processo/{processo}")
	@ResponseStatus(HttpStatus.OK)
	public ProcessoIndenizacaoDetalheDTO BuscarProcesso(@PathVariable String processo){
			
			Processo procesoEncontrado = cadastroProcesso.localizarEntidadePorNumero(processo);
		
		return modelMapper.map(procesoEncontrado, ProcessoIndenizacaoDetalheDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProcessoDTO adicionar(@RequestBody @Valid ProcessoInput processoInput) {
		
		try{
			Processo processo = modelMapper.map(processoInput, Processo.class);
			Processo processoSalvo = cadastroProcesso.salvar(processo);
			return modelMapper.map(processoSalvo, ProcessoDTO.class);
		
		}catch (ProcessoNaoEncontradoException e){
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProcessoDTO atualizar(@PathVariable Long id, @RequestBody @Valid ProcessoInput processoInput){
		 	try {
		 		Processo processo = modelMapper.map(processoInput, Processo.class);
				Processo processoProcurado = cadastroProcesso.localizarEntidade(id);
				BeanUtils.copyProperties(processo, processoProcurado, "id");
				Processo processoSalvo = cadastroProcesso.salvar(processoProcurado);
			return modelMapper.map(processoSalvo, ProcessoDTO.class);
		 	}catch(EntidadeNaoEncontradaException e) {
		 		throw new NegocioException(e.getMessage(), e);	
		 	}
		 
	 }
	
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id){
		cadastroProcesso.remover(id);
	}

   
}
