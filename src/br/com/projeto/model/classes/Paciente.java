package br.com.projeto.model.classes;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "paciente")
@SequenceGenerator(name = "paciente_seq", sequenceName = "paciente_seq", initialValue = 1, allocationSize = 1)
public class Paciente  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Paciente", campoConsulta = "pessoa.pessoaNome", principal = 2)
	@JoinColumn(unique=true , referencedColumnName="idPessoa")
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa = new Pessoa();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		return true;
	}

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idPaciente", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "paciente_seq")
	private Long idPaciente;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Acompanhante", campoConsulta = "nomeAcompanhante", principal = 3)
	private String nomeAcompanhante;
	
	private String cpfAcompanhante;
	
	private String rgAcompanhante;
	
	private Date dataNascimentoAcompanhante;
	
	private String telefoneAcompanhante ;
	
	private String observacaoAcompanhante;
	
	
	//GETTERS E SETTERS-------------------------
	
	public String getCpfAcompanhante() {
		return cpfAcompanhante;
	}
	
	public void setCpfAcompanhante(String cpfAcompanhante) {
		this.cpfAcompanhante = cpfAcompanhante;
	}
	
	public String getRgAcompanhante() {
		return rgAcompanhante;
	}
	
	public void setRgAcompanhante(String rgAcompanhante) {
		this.rgAcompanhante = rgAcompanhante;
	}
	
	public Date getDataNascimentoAcompanhante() {
		return dataNascimentoAcompanhante;
	}
	
	public String getTelefoneAcompanhante() {
		return telefoneAcompanhante;
	}
	public void setTelefoneAcompanhante(String telefoneAcompanhante) {
		this.telefoneAcompanhante = telefoneAcompanhante;
	}
	
	public String getObservacaoAcompanhante() {
		return observacaoAcompanhante;
	}
	
	public void setObservacaoAcompanhante(String observacaoAcompanhante) {
		this.observacaoAcompanhante = observacaoAcompanhante;
	}
	
	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public String getNomeAcompanhante() {
		return nomeAcompanhante;
	}

	public void setNomeAcompanhante(String nomeAcompanhante) {
		this.nomeAcompanhante = nomeAcompanhante;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}
	
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Pessoa getPessoa() { 
		return pessoa; 
	}
	 
	 public void setPessoa(Pessoa pessoa) { 
		 this.pessoa = pessoa; 
	}

	
	
}
