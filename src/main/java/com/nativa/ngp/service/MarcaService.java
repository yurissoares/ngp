package com.nativa.ngp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nativa.ngp.entity.MarcaEntity;
import com.nativa.ngp.exception.MarcaException;
import com.nativa.ngp.repository.IMarcaRepository;

@Service
public class MarcaService implements IMarcaService {

	@Autowired
	private IMarcaRepository marcaRepository;

	@Override
	public Boolean atualizar(MarcaEntity marca) {
		try {
			MarcaEntity marcaEntityAtualizada = this.consultar(marca.getMarcaId());

			marcaEntityAtualizada.setNome(marca.getNome());
			this.marcaRepository.save(marcaEntityAtualizada);

			return true;
		} catch (MarcaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean excluir(Long marcaId) {
		try {
			this.consultar(marcaId);
			this.marcaRepository.deleteById(marcaId);
			return true;
		} catch (MarcaException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MarcaEntity consultar(Long marcaId) {
		try {
			Optional<MarcaEntity> marcaOptional = this.marcaRepository.findById(marcaId);
			if (marcaOptional.isPresent()) {
				return marcaOptional.get();
			}
			throw new MarcaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
		} catch (MarcaException m) {
			throw m;
		} catch (Exception e) {
			throw new MarcaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MarcaEntity> listar() {
		try {
			return this.marcaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Boolean cadastrar(MarcaEntity marca) {
		try {
			this.marcaRepository.save(marca);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
