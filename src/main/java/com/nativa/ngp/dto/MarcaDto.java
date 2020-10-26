package com.nativa.ngp.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MarcaDto {

	private Long marcaId;
	
	@NotBlank(message = "Informe o nome da marca.")
	private String nome;
	
}
