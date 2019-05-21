package br.com.projeto.model.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

/**
 * @author Humberto
 *
 */

@Audited
@Entity
@Table(name = "atendente")
@SequenceGenerator(name = "atendente_seq", sequenceName = "atendente_seq", initialValue = 1, allocationSize = 1)
public class Atendente  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Atendente", campoConsulta = "pessoa.pessoaNome", principal = 2)
	@JoinColumn(unique=true , referencedColumnName="idPessoa")
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa = new Pessoa();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idAtendente", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "atendente_seq")
	private Long idAtendente;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//GETTERS E SETTERS-------------------------
	
	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Pessoa getPessoa() { 
		return pessoa; 
	}
	 
	public void setPessoa(Pessoa pessoa) { 
		 this.pessoa = pessoa; 
	}
	
	public Long getIdAtendente() {
		return idAtendente;
	}
	
	public void setIdAtendente(Long idAtendente) {
		this.idAtendente = idAtendente;
	}
	
	// HASH CODE & EQUALS

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAtendente == null) ? 0 : idAtendente.hashCode());
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
		Atendente other = (Atendente) obj;
		if (idAtendente == null) {
			if (other.idAtendente != null)
				return false;
		} else if (!idAtendente.equals(other.idAtendente))
			return false;
		return true;
	}
	 
	 

	
	
}
