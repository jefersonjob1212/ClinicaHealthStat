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
@Table(name="tipoConsulta")
@SequenceGenerator(name="tipoConsulta_seq", sequenceName="tipoConsulta_seq", initialValue = 1, allocationSize = 1)
public class TipoConsulta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idTipoConsulta", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "tipoConsulta_seq")
	private Long idTipoConsulta;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "nomeTipoConsulta", principal = 2)
	private String nomeTipoConsulta;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//GETTERS E SETTERS
	
	
	public void setIdTipoConsulta(Long idTipoConsulta) {
		this.idTipoConsulta = idTipoConsulta;
	}
	
	public Long getIdTipoConsulta() {
		return idTipoConsulta;
	}
	
	public void setNomeTipoConsulta(String nomeTipoConsulta) {
		this.nomeTipoConsulta = nomeTipoConsulta;
	}
	
	public String getNomeTipoConsulta() {
		return nomeTipoConsulta;
	}

	public int getVersionNum() {
		return versionNum;
	}
	
	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	//EQUALS E HASCODE

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoConsulta == null) ? 0 : idTipoConsulta.hashCode());
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
		TipoConsulta other = (TipoConsulta) obj;
		if (idTipoConsulta == null) {
			if (other.idTipoConsulta != null)
				return false;
		} else if (!idTipoConsulta.equals(other.idTipoConsulta))
			return false;
		return true;
	}

	
	
	
}
