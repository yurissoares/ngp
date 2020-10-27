package com.nativa.ngp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDto extends RepresentationModel<UserDto> {

	private Long userId;

	@NotBlank(message = "Informe um nome.")
	private String nome;
	
	@Email
	@NotBlank(message = "Informe um email.")
	private String email;
	
	@NotBlank(message = "Informe uma senha.")
	private String senha;
}
