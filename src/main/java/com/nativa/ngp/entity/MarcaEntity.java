package com.nativa.ngp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_marca")
@Data
@NoArgsConstructor
public class MarcaEntity implements Serializable {

	private static final long serialVersionUID = 702051841961317525L;
	
	//Testar o @JsonInclude(Include.NON_NULL)
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="marca_id")
	private Long marcaId;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="nome")
	private String nome;

}
