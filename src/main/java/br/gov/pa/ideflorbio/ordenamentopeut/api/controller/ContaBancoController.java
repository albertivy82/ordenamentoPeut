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
import br.gov.pa.ideflorbio.ordenamentopeut.domain.model.ContaBancaria;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.repository.ContaBancariaRepository;
import br.gov.pa.ideflorbio.ordenamentopeut.domain.service.CadastroContaBancariaService;

@RestController
@RequestMapping("/contasbancarias")
public class ContaBancoController {
	
	@Autowired
	private ContaBancariaRepository contasBancarias;
	
	@Autowired
	private CadastroContaBancariaService bancos;
	
	
	@GetMapping
	public List<ContaBancaria> listar(){
		return contasBancarias.findAll();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ContaBancaria buscar(@PathVariable Long id) {
			ContaBancaria contaEncontrada = bancos.localizarEntidade(id);
		return contaEncontrada;	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContaBancaria adicionar(@RequestBody @Valid ContaBancaria contaBancaria){
		try {
			return bancos.salvar(contaBancaria);
		}catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ContaBancaria atualizar(@PathVariable Long id, @RequestBody @Valid ContaBancaria contaRecebida){
			try {
				ContaBancaria contaProcurada = bancos.localizarEntidade(id);
				BeanUtils.copyProperties(contaRecebida, contaProcurada, "id");
				return bancos.salvar(contaProcurada);
			}catch(EntidadeNaoEncontradaException e) {
				//captura a exception do beneficiario na classe de serviço, pega a mensagem 
				//mas lança o BAD REQUEST do NegocioEsception
				throw new NegocioException(e.getMessage(), e);
			}
	}
		
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void apagar(@PathVariable Long id){
		bancos.excluir(id);
	}

}
