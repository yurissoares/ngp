package com.nativa.ngp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nativa.ngp.dto.MarcaDto;
import com.nativa.ngp.entity.MarcaEntity;
import com.nativa.ngp.exception.MarcaException;
import com.nativa.ngp.repository.IMarcaRepository;

@Service
public class MarcaService implements IMarcaService {

	@Autowired
	private IMarcaRepository marcaRepository;

	@Override
	public Boolean atualizar(MarcaDto marca) {
		try {
			this.consultar(marca.getMarcaId());
			ModelMapper mapper = new ModelMapper();
			MarcaEntity marcaEntityAtualizada = mapper.map(marca, MarcaEntity.class);
			
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
	public MarcaDto consultar(Long marcaId) {
		try {
			ModelMapper mapper = new ModelMapper();
			Optional<MarcaEntity> marcaOptional = this.marcaRepository.findById(marcaId);
			if (marcaOptional.isPresent()) {
				return mapper.map(marcaOptional.get(), MarcaDto.class);
			}
			throw new MarcaException("Marca não encontrada", HttpStatus.NOT_FOUND);
		} catch (MarcaException m) {
			throw m;
		} catch (Exception e) {
			throw new MarcaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MarcaDto> listar() {
		try {
			ModelMapper mapper = new ModelMapper();
			return mapper.map(this.marcaRepository.findAll(), new TypeToken<List<MarcaDto>>() {}.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Boolean cadastrar(MarcaDto marca) {
		try {
			ModelMapper mapper = new ModelMapper();
			MarcaEntity marcaEntity = mapper.map(marca, MarcaEntity.class);
			this.marcaRepository.save(marcaEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
