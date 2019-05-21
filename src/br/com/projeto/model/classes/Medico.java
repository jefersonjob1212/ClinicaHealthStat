package br.com.projeto.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

/**
 * @author Humberto
 *
 */
@Audited
@Entity
@Table(name = "medico")
@SequenceGenerator(name = "medico_seq", sequenceName = "medico_seq", initialValue = 1, allocationSize = 1)
public class Medico  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Médico", campoConsulta = "pessoa.pessoaNome", principal = 2)
	@JoinColumn(unique=true , referencedColumnName="idPessoa")
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa = new Pessoa();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idMedico", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "medico_seq")
	private Long idMedico;
	
	@Column(name="numeroCrm")
	private String numeroCrm;
	
	@Temporal(TemporalType.DATE)
	private Date dataInscricaoCrm;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	@ManyToMany(cascade = CascadeType.MERGE,  fetch = FetchType.LAZY)
	@JoinTable(name="medico_especialidade",
	joinColumns={
			@JoinColumn(name="medicoId", referencedColumnName="idMedico")},
	inverseJoinColumns={
			@JoinColumn(name="especialidadeId",	referencedColumnName="idEspecialidade")}) 
	private List<Especialidade>  especialidades = new ArrayList<>();
	

	//GETTERS E SETTERS-------------------------
	public Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Long idMedico) {
		this.idMedico = idMedico;
	}
	
	public Date getDataInscricaoCrm() {
		return dataInscricaoCrm;
	}

	public void setDataInscricaoCrm(Date dataInscricaoCrm) {
		this.dataInscricaoCrm = dataInscricaoCrm;
	}

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
	
	public String getNumeroCrm() {
		return numeroCrm;
	}
	
	public void setNumeroCrm(String numeroCrm) {
		this.numeroCrm = numeroCrm;
	}
	
	public List<Especialidade> getEspecialidades() {
		// Hibernate.initialize(especialidades);
		return especialidades;
		
	}
	
	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
	
	// HASH CODE & EQUALS

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMedico == null) ? 0 : idMedico.hashCode());
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
		Medico other = (Medico) obj;
		if (idMedico == null) {
			if (other.idMedico != null)
				return false;
		} else if (!idMedico.equals(other.idMedico))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		String s = "";
		for(Especialidade e: especialidades){
			s += e.getNomeEspecialidade();
			if(especialidades.indexOf(e) != especialidades.size() - 1)
				s += ", ";
		}
		s += "";
		return s;
		
	}


	
	

	
	
}
