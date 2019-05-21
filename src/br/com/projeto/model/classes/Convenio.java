package br.com.projeto.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

/**
 * @author Humberto
 *
 */
@Entity
@Audited
@Table(name="convenio")
@SequenceGenerator(name="convenio_seq", sequenceName="convenio_seq", initialValue = 1, allocationSize = 1)
public class Convenio implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idConvenio", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "convenio_seq")
	private Long idConvenio;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Convênio", campoConsulta = "nomeConvenio", principal = 2)
	private String nomeConvenio;
	
	private Double valorDecrescido;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//GETTERS E SETTERS----------------------------------------------
	
	public Long getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(Long idConvenio) {
		this.idConvenio = idConvenio;
	}
	public String getNomeConvenio() {
		return nomeConvenio;
	}
	public void setNomeConvenio(String nomeConvenio) {
		this.nomeConvenio = nomeConvenio;
	}
	public int getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
	public Double getValorDecrescido() {
		return valorDecrescido;
	}
	public void setValorDecrescido(Double valorDecrescido) {
		this.valorDecrescido = valorDecrescido;
	}
	
	//EQUALS E HASCODE ----------------------------------------
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConvenio == null) ? 0 : idConvenio.hashCode());
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
		Convenio other = (Convenio) obj;
		if (idConvenio == null) {
			if (other.idConvenio != null)
				return false;
		} else if (!idConvenio.equals(other.idConvenio))
			return false;
		return true;
	}
	
	
	
	
}	