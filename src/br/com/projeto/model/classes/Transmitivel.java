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
@Table(name="doenca")
@SequenceGenerator(name="doenca_seq", sequenceName="doenca_seq", initialValue = 1, allocationSize = 1)
public class Transmitivel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idDoenca", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "doenca_seq")
	private Long idDoenca;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "nomeDoenca", principal = 2)
	private String nomeDoenca;
	
	private String transmissao;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//GETTERS E SETTERS
	
	public Long getIdDoenca() {
		return idDoenca;
	}

	public void setIdDoenca(Long idDoenca) {
		this.idDoenca = idDoenca;
	}

	public String getNomeDoenca() {
		return nomeDoenca;
	}

	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}

	public String getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
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
		result = prime * result + ((idDoenca == null) ? 0 : idDoenca.hashCode());
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
		Transmitivel other = (Transmitivel) obj;
		if (idDoenca == null) {
			if (other.idDoenca != null)
				return false;
		} else if (!idDoenca.equals(other.idDoenca))
			return false;
		return true;
	}
	
}
