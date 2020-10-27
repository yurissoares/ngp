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
import com.nativa.ngp.dto.MarcaDto;
import com.nativa.ngp.model.Response;
import com.nativa.ngp.service.IMarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

	@Autowired
	private IMarcaService marcaService;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response<List<MarcaDto>>> listarMarcas() {
		Response<List<MarcaDto>> response = new Response<>();
		response.setData(this.marcaService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{marcaId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response<MarcaDto>> consultarMarca(@PathVariable Long marcaId) {
		Response<MarcaDto> response = new Response<>();
		MarcaDto marca = this.marcaService.consultar(marcaId);
		response.setData(marca);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).consultarMarca(marcaId))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).excluirMarca(marcaId))
				.withRel(HyperLinkConstant.EXCLUIR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).atualizarMarca(marca))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response<Boolean>> cadastrarMarca(@Valid @RequestBody MarcaDto marca) {
		Response<Boolean> response = new Response<>();
		response.setData(this.marcaService.cadastrar(marca));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).atualizarMarca(marca))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).listarMarcas())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{marcaId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response<Boolean>> excluirMarca(@PathVariable Long marcaId) {
		Response<Boolean> response = new Response<>();
		response.setData(this.marcaService.excluir(marcaId));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).listarMarcas())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Response<Boolean>> atualizarMarca(@Valid @RequestBody MarcaDto marca) {
		Response<Boolean> response = new Response<>();
		response.setData(this.marcaService.atualizar(marca));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MarcaController.class).listarMarcas())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
