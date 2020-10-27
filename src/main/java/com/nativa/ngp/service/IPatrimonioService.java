package com.nativa.ngp.service;

import java.util.List;

import com.nativa.ngp.dto.PatrimonioDto;

public interface IPatrimonioService {

	public Boolean atualizar(final PatrimonioDto patrimonio);

	public Boolean excluir(final Long patrimonioId);

	public List<PatrimonioDto> listar();

	public PatrimonioDto consultar(final Long patrimonioId);

	public Boolean cadastrar(final PatrimonioDto patrimonio);
	
}
