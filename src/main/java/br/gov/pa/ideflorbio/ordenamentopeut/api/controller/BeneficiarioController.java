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
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.BeneficiarioDTO;
import br.gov.pa.ideflorbio.ordenamentopeut.api.model.input.BeneficiarioInput;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.Beneficiario;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroBeneficiarioService;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {
	
		
	@Autowired
	private CadastroBeneficiarioService cadastroBeneficiario;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	//----MÉTODOS------//
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<BeneficiarioDTO> listar(@PageableDefault(size=10) Pageable paginacao){
		
		return cadastroBeneficiario.listarTodos(paginacao).map(p-> modelMapper.map(p,BeneficiarioDTO.class));
		
	}
	
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BeneficiarioDTO buscar(@PathVariable Long id){
		
			Beneficiario beneficiario = cadastroBeneficiario.localizarEntidade(id);
		
		return modelMapper.map(beneficiario, BeneficiarioDTO.class);
	}
	
	@GetMapping("beneficiario/{nome}")
	@ResponseStatus(HttpStatus.OK)
	public BeneficiarioDTO buscarPorNome(@PathVariable String nome){
		Beneficiario beneficiario = cadastroBeneficiario.localizarBeneficiarioPorNome(nome);
		 
		 return modelMapper.map(beneficiario, BeneficiarioDTO.class);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BeneficiarioDTO adicionar(@RequestBody @Valid BeneficiarioInput beneficiarioInput) {
		
		Beneficiario beneficiario = modelMapper.map(beneficiarioInput, Beneficiario.class);
		Beneficiario beneficiarioSalvo = cadastroBeneficiario.salvar(beneficiario);
		
		return modelMapper.map(beneficiarioSalvo, BeneficiarioDTO.class);
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public BeneficiarioDTO atualizar(@PathVariable Long id, @RequestBody @Valid BeneficiarioInput beneficiarioInput) {
			
			Beneficiario beneficiario = modelMapper.map(beneficiarioInput, Beneficiario.class);
			Beneficiario beneficiarioAtual = cadastroBeneficiario.localizarEntidade(id);
			BeanUtils.copyProperties(beneficiario, beneficiarioAtual, "id");
			cadastroBeneficiario.salvar(beneficiarioAtual);
		
	 return modelMapper.map(beneficiarioAtual, BeneficiarioDTO.class);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id){
		cadastroBeneficiario.remover(id);
	}

}
