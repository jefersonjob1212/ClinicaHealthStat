package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.model.classes.Medico;

@FacesConverter(forClass = Medico.class, value = "medicoConverter")
public class MedicoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Medico) HibernateUtil.getCurrentSession().get(Medico.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null) {
			Medico c = (Medico) objeto;
			return c.getIdMedico() != null && c.getIdMedico() > 0 ? c.getIdMedico().toString() : null;
		}
		return null;
	}

}
/*
 * @Override public Object getAsObject(FacesContext arg0, UIComponent arg1,
 * String value) {
 * 
 * try { return new MedicoController().findByPorId(Medico.class,
 * Integer.valueOf(value).longValue()); } catch (Exception e) { return new
 * Medico(); } }
 * 
 * @Override public String getAsString(FacesContext arg0, UIComponent arg1,
 * Object value) { try { return String.valueOf(((Medico) value).getIdMedico());
 * } catch (Exception e) { return ""; } }
 */
