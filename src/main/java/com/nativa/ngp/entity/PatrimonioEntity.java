package com.nativa.ngp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_patrimonio")
@Data
@NoArgsConstructor
public class PatrimonioEntity implements Serializable {

	private static final long serialVersionUID = 6078331721770190969L;
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patrimonio_id")
	private Long patrimonioId;
	
	@JsonInclude(Include.NON_EMPTY)
	@Column(name="nome")
	private String nome;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="marca_id")
	private MarcaEntity marca;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="num_tombo")
	private String numTombo;
	

}
