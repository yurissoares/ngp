package com.nativa.ngp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nativa.ngp.constant.HyperLinkConstant;
import com.nativa.ngp.dto.PatrimonioDto;
import com.nativa.ngp.model.Response;
import com.nativa.ngp.service.IPatrimonioService;

@RestController
@RequestMapping("/patrimonio")
public class PatrimonioController {

	@Autowired
	private IPatrimonioService patrimonioService;

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<List<PatrimonioDto>>> listarPatrimonios() {
		Response<List<PatrimonioDto>> response = new Response<>();
		response.setData(this.patrimonioService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{patrimonioId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<PatrimonioDto>> consultarPatrimonio(@PathVariable Long patrimonioId) {
		Response<PatrimonioDto> response = new Response<>();
		PatrimonioDto patrimonio = this.patrimonioService.consultar(patrimonioId);
		response.setData(patrimonio);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).consultarPatrimonio(patrimonioId))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).excluirPatrimonio(patrimonioId))
				.withRel(HyperLinkConstant.EXCLUIR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).atualizarPatrimonio(patrimonio))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Boolean>> cadastrarPatrimonio(@Valid @RequestBody PatrimonioDto patrimonio) {
		Response<Boolean> response = new Response<>();
		response.setData(this.patrimonioService.cadastrar(patrimonio));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).listarPatrimonios())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{patrimonioId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Boolean>> excluirPatrimonio(@PathVariable Long patrimonioId) {
		Response<Boolean> response = new Response<>();
		response.setData(this.patrimonioService.excluir(patrimonioId));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).listarPatrimonios())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Boolean>> atualizarPatrimonio(@Valid @RequestBody PatrimonioDto patrimonio) {
		Response<Boolean> response = new Response<>();
		response.setData(this.patrimonioService.atualizar(patrimonio));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class).listarPatrimonios())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
