package com.nativa.ngp.service;

import java.util.List;

import com.nativa.ngp.dto.MarcaDto;

public interface IMarcaService {

	public Boolean atualizar(final MarcaDto marca);

	public Boolean excluir(final Long marcaId);

	public List<MarcaDto> listar();

	public MarcaDto consultar(final Long marcaId);

	public Boolean cadastrar(final MarcaDto marca);
}
