package com.nativa.ngp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nativa.ngp.entity.MarcaEntity;
import com.nativa.ngp.repository.IMarcaRepository;

@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	@Autowired
	private IMarcaRepository marcaRepository;

	@GetMapping
	public ResponseEntity<List<MarcaEntity>> listarMarcas() {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaRepository.findAll());
	}
	
	@GetMapping("/{marcaId}")
	public ResponseEntity<MarcaEntity> consultarMarca(@PathVariable Long marcaId) {
		return ResponseEntity.status(HttpStatus.OK).body(this.marcaRepository.findById(marcaId).get());
	}
	
	@PostMapping
	public ResponseEntity<Boolean> cadastrarMarca(@RequestBody MarcaEntity marca) {
		try {
			this.marcaRepository.save(marca);
			return ResponseEntity.status(HttpStatus.OK).body(true);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(false);	
		}
	}
}
