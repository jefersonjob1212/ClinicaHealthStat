package br.com.projeto.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * @author Humberto
 *
 */

@Audited
@Entity
@Table(name = "agenda")
@SequenceGenerator(name = "agenda_seq", sequenceName = "agenda_seq", initialValue = 1, allocationSize = 1)
public class Agenda  implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "agenda_seq")
	private Long idAgenda;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	private Boolean diaTodo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn()
	private Paciente paciente;

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(Boolean diaTodo) {
		this.diaTodo = diaTodo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAgenda == null) ? 0 : idAgenda.hashCode());
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
		Agenda other = (Agenda) obj;
		if (idAgenda == null) {
			if (other.idAgenda != null)
				return false;
		} else if (!idAgenda.equals(other.idAgenda))
			return false;
		return true;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}


	
	
	
}
