package com.nativa.ngp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nativa.ngp.dto.MarcaDto;
import com.nativa.ngp.service.IMarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	private IMarcaService marcaService;

	@GetMapping
	public ResponseEntity<List<MarcaDto>> listarMarcas() {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaService.listar());
	}
	
	@GetMapping("/{marcaId}")
	public ResponseEntity<MarcaDto> consultarMarca(@PathVariable Long marcaId) {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaService.consultar(marcaId));
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarMarca(@Valid @RequestBody MarcaDto marca) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.marcaService.cadastrar(marca));
	}
	
	@DeleteMapping("/{marcaId}")
	public ResponseEntity<Boolean> excluirMarca(@PathVariable Long marcaId) {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaService.excluir(marcaId));	
	}

	@PutMapping
	public ResponseEntity<Boolean> atualizarMarca(@Valid @RequestBody MarcaDto marca) {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaService.atualizar(marca));
	}
}
