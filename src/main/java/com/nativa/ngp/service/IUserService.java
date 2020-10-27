package com.nativa.ngp.service;

import java.util.List;

import com.nativa.ngp.dto.UserDto;

public interface IUserService {

	public Boolean atualizar(final UserDto user);

	public Boolean excluir(final Long userId);

	public List<UserDto> listar();

	public UserDto consultar(final Long userId);

	public Boolean cadastrar(final UserDto user);
}
