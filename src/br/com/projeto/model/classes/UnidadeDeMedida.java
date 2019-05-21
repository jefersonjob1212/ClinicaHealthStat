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
@Table(name="unidadeDeMedida")
@SequenceGenerator(name="unidadeDeMedida_seq", sequenceName="unidadeDeMedida_seq", initialValue = 1, allocationSize = 1)
public class UnidadeDeMedida implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idUnidadeDeMedida", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "unidadeDeMedida_seq")
	
	private Long idUnidadeDeMedida;
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "nomeUnidadeDeMedida", principal = 2)
	private String nomeUnidadeDeMedida;
	
	private String observacao;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//EQUALS E HASCODE
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUnidadeDeMedida == null) ? 0 : idUnidadeDeMedida.hashCode());
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
		UnidadeDeMedida other = (UnidadeDeMedida) obj;
		if (idUnidadeDeMedida == null) {
			if (other.idUnidadeDeMedida != null)
				return false;
		} else if (!idUnidadeDeMedida.equals(other.idUnidadeDeMedida))
			return false;
		return true;
	}
	
	//GETTERS E SETTERS
	public int getVersionNum() {
		return versionNum;
	}


	public Long getIdUnidadeDeMedida() {
		return idUnidadeDeMedida;
	}

	public void setIdUnidadeDeMedida(Long idUnidadeDeMedida) {
		this.idUnidadeDeMedida = idUnidadeDeMedida;
	}

	public String getNomeUnidadeDeMedida() {
		return nomeUnidadeDeMedida;
	}

	public void setNomeUnidadeDeMedida(String nomeUnidadeDeMedida) {
		this.nomeUnidadeDeMedida = nomeUnidadeDeMedida;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
}
