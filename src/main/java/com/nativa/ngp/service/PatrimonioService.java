package com.nativa.ngp.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nativa.ngp.controller.PatrimonioController;
import com.nativa.ngp.dto.PatrimonioDto;
import com.nativa.ngp.entity.MarcaEntity;
import com.nativa.ngp.entity.PatrimonioEntity;
import com.nativa.ngp.exception.PatrimonioException;
import com.nativa.ngp.repository.IMarcaRepository;
import com.nativa.ngp.repository.IPatrimonioRepository;

@Service
public class PatrimonioService implements IPatrimonioService {

	private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte";

	private IMarcaRepository marcaRepository;
	private IPatrimonioRepository patrimonioRepository;
	private ModelMapper mapper;

	@Autowired
	public PatrimonioService(IPatrimonioRepository patrimonioRepository, IMarcaRepository marcaRepository) {
		this.mapper = new ModelMapper();
		this.patrimonioRepository = patrimonioRepository;
		this.marcaRepository = marcaRepository;
	}
	
	public void verificarNumTomboExistente(String numTombo) {
		if(numTombo != null) {
			throw new PatrimonioException("O número de tombo não pode ser modificado.", HttpStatus.BAD_REQUEST);
		}	
	}
	
	public void verificarMarcaExistente(Long marcaId) {
		Optional<MarcaEntity> marcaOptional = this.marcaRepository.findById(marcaId);
		if (!marcaOptional.isPresent()) {
			throw new PatrimonioException("Insira o ID de uma marca existente", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Boolean atualizar(PatrimonioDto patrimonio) {
		try {
			this.consultar(patrimonio.getPatrimonioId());
			
			verificarNumTomboExistente(patrimonio.getNumTombo());
			verificarMarcaExistente(patrimonio.getMarcaId());
			
			PatrimonioEntity patrimonioEntityAtualizada = this.mapper.map(patrimonio, PatrimonioEntity.class);
			
			patrimonioEntityAtualizada.setNumTombo(this.consultar(patrimonio.getPatrimonioId()).getNumTombo());
			this.patrimonioRepository.save(patrimonioEntityAtualizada);
			return Boolean.TRUE;
		} catch (PatrimonioException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean excluir(Long patrimonioId) {
		try {
			this.consultar(patrimonioId);
			this.patrimonioRepository.deleteById(patrimonioId);
			return Boolean.TRUE;
		} catch (PatrimonioException m) {
			throw m;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public PatrimonioDto consultar(Long patrimonioId) {
		try {
			Optional<PatrimonioEntity> patrimonioOptional = this.patrimonioRepository.findById(patrimonioId);
			if (patrimonioOptional.isPresent()) {
				return this.mapper.map(patrimonioOptional.get(), PatrimonioDto.class);
			}
			throw new PatrimonioException("Patrimonio não encontrada", HttpStatus.NOT_FOUND);
		} catch (PatrimonioException m) {
			throw m;
		} catch (Exception e) {
			throw new PatrimonioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<PatrimonioDto> listar() {
		try {
			List<PatrimonioDto> patrimonioDto = this.mapper.map(this.patrimonioRepository.findAll(),
					new TypeToken<List<PatrimonioDto>>() {
					}.getType());
			patrimonioDto.forEach(patrimonio -> patrimonio
					.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PatrimonioController.class)
							.consultarPatrimonio(patrimonio.getPatrimonioId())).withSelfRel()));
			return patrimonioDto;
		} catch (Exception e) {
			throw new PatrimonioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Boolean cadastrar(PatrimonioDto patrimonio) {
		try {
			Optional<MarcaEntity> marcaOptional = this.marcaRepository.findById(patrimonio.getMarcaId());
			if (!marcaOptional.isPresent()) {
				throw new PatrimonioException("Insira o ID de uma marca existente", HttpStatus.NOT_FOUND);
			}
			
			verificarNumTomboExistente(patrimonio.getNumTombo());

			PatrimonioEntity patrimonioEntity = new PatrimonioEntity();
			patrimonioEntity.setNome(patrimonio.getNome());
			patrimonioEntity.setMarca(marcaOptional.get());
			patrimonioEntity.setDescricao(patrimonio.getDescricao());
			patrimonioEntity.setNumTombo(String.valueOf(Math.abs(new Random().nextLong())));
			
			this.patrimonioRepository.save(patrimonioEntity);
			return Boolean.TRUE;
		} catch (PatrimonioException m) {
			throw m;
		} catch (Exception e) {
			throw new PatrimonioException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
