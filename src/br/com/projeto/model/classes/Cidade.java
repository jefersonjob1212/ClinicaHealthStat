package br.com.projeto.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

/**
 * @author Humberto
 *
 */
@Audited
@Entity
@Table(name="cidade")
@SequenceGenerator(name="cidade_seq", sequenceName = "cidade_seq" , 
initialValue = 1, allocationSize = 1)
public class Cidade  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="id_cidade", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "cidade_seq")
	private Long id_cidade;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "cidade_descricao", principal = 2)
	@Column(nullable = false, length = 100) //Se é obrigatória false é ..
	private String cidade_descricao;
	
	
	@IdentificaCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "estado.estado_nome" )
	@Basic
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "estado", nullable = false)
	@ForeignKey(name = "estado_fk")
	private Estado estado = new Estado();
	
	public Long getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(Long id_cidade) {
		this.id_cidade = id_cidade;
	}

	public String getCidade_descricao() {
		return cidade_descricao;
	}

	public void setCidade_descricao(String cidade_descricao) {
		this.cidade_descricao = cidade_descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cidade == null) ? 0 : id_cidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id_cidade == null) {
			if (other.id_cidade != null)
				return false;
		} else if (!id_cidade.equals(other.id_cidade))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [id_cidade=" + id_cidade + ", "
				+ "cidade_descricao=" + cidade_descricao + "]";
	}
	
	
}



