package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.model.classes.Paciente;
@FacesConverter(forClass = Paciente.class, value="pacienteConverter")
public class PacienteConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()){
			return (Paciente) HibernateUtil.getCurrentSession().get(Paciente.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null){
			Paciente c = (Paciente) objeto;
			return c.getIdPaciente() != null && c.getIdPaciente() > 0 ? c.getIdPaciente().toString() : null;
		}
		return null;
	}
		

	}



