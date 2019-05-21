package br.com.projeto.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "especialidade")
@SequenceGenerator(name = "especialidade_seq", sequenceName = "especialidade_seq", initialValue = 1, allocationSize = 1)
public class Especialidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "idEspecialidade", principal = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "especialidade_seq")
	private Long idEspecialidade;

	@IdentificaCampoPesquisa(descricaoCampo = "Especialidade", campoConsulta = "nomeEspecialidade", principal = 2)
	private String nomeEspecialidade;

	private String observacao;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	@ManyToMany(mappedBy = "especialidades")
	private List<Medico> medicos = new ArrayList<>();

	public Long getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Long idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEspecialidade == null) ? 0 : idEspecialidade.hashCode());
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
		Especialidade other = (Especialidade) obj;
		if (idEspecialidade == null) {
			if (other.idEspecialidade != null)
				return false;
		} else if (!idEspecialidade.equals(other.idEspecialidade))
			return false;
		return true;
	}

	

	 

}