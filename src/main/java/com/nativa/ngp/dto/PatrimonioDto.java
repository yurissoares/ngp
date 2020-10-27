package com.nativa.ngp.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PatrimonioDto extends RepresentationModel<PatrimonioDto> {

	private Long patrimonioId;
	
	@NotBlank(message = "Informe o nome do patrimonio.")
	private String nome;
	
	private Long marcaId;
	
	private String descricao;
	
	private String numTombo;
}
