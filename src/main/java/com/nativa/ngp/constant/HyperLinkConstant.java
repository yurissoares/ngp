package com.nativa.ngp.constant;

import lombok.Getter;

@Getter
public enum HyperLinkConstant {
	
	ATUALIZAR("UPDATE"),
	EXCLUIR("EXCLUIR"),
	LISTAR("LISTAR"),
	CONSULTAR("GET_ALL");
	
	private final String valor;
	
	private HyperLinkConstant(String valor) {
		this.valor = valor;
	}
}
