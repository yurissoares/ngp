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
import com.nativa.ngp.dto.UserDto;
import com.nativa.ngp.model.Response;
import com.nativa.ngp.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<List<UserDto>>> listarUsers() {
		Response<List<UserDto>> response = new Response<>();
		response.setData(this.userService.listar());
		response.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<UserDto>> consultarUser(@PathVariable Long userId) {
		Response<UserDto> response = new Response<>();
		UserDto user = this.userService.consultar(userId);
		response.setData(user);
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).consultarUser(userId))
				.withSelfRel());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).excluirUser(userId))
				.withRel(HyperLinkConstant.EXCLUIR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).atualizarUser(user))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	public ResponseEntity<Response<Boolean>> cadastrarUser(@Valid @RequestBody UserDto user) {
		Response<Boolean> response = new Response<>();
		response.setData(this.userService.cadastrar(user));
		response.setStatusCode(HttpStatus.CREATED.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).atualizarUser(user))
				.withRel(HyperLinkConstant.ATUALIZAR.getValor()));
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).listarUsers())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Boolean>> excluirUser(@PathVariable Long userId) {
		Response<Boolean> response = new Response<>();
		response.setData(this.userService.excluir(userId));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).listarUsers())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Response<Boolean>> atualizarUser(@Valid @RequestBody UserDto user) {
		Response<Boolean> response = new Response<>();
		response.setData(this.userService.atualizar(user));
		response.setStatusCode(HttpStatus.OK.value());
		response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).listarUsers())
				.withRel(HyperLinkConstant.LISTAR.getValor()));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
