package com.nativa.ngp.service;

import java.util.List;

import com.nativa.ngp.entity.MarcaEntity;

public interface IMarcaService {

	public Boolean atualizar(final MarcaEntity marca);

	public Boolean excluir(final Long marcaId);

	public List<MarcaEntity> listar();

	public MarcaEntity consultar(final Long marcaId);

	public Boolean cadastrar(final MarcaEntity marca);
}
