package br.com.projeto.acesso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;



public enum Permissao {
		
	ADMIN("ADMIN", "Administrador"),
	USER("USER","usuario Padrão"),
	CADASTRO_ACESSAR("CADASTRO_ACESSAR","Cadastro - Acessar"),
	FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR","Financeiro - Acessar"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR","Mensagem recebida - Acessar"),
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR","Bairro - Acessar"),
	BAIRRO_NOVO("MBAIRRO_NOVO","Bairro - Novo"),
	BAIRRO_EDITAR("BAIRRO_EDITAR","Bairro - Editar"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR","Bairro - Excluir");
	
	private String valor ="";
	private String descricao="";
	
	private Permissao() {
		//CONSTRUTOR
	}
	
	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		
		return getValor();
	}
	
	public static List<Permissao> getListPermissao(){
		List<Permissao> permissoes = new ArrayList<Permissao>();
		
		for(Permissao permissao: Permissao.values()) {
			permissoes.add(permissao);
		}
		
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}

			
		} );
		
		return permissoes;
	}
}
