package com.nativa.ngp.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MarcaDto extends RepresentationModel<MarcaDto>{

	private Long marcaId;
	
	@NotBlank(message = "Informe o nome da marca.")
	private String nome;
	
}
