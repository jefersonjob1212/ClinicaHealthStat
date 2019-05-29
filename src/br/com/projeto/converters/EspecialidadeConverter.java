
package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.model.classes.Especialidade;

@FacesConverter( value="especialidadeConverter",forClass = Especialidade.class)
public class EspecialidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()){
			return (Especialidade) HibernateUtil.getCurrentSession().get(Especialidade.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null){
			Especialidade c = (Especialidade) objeto;
			return c.getIdEspecialidade() != null && c.getIdEspecialidade() > 0 ? c.getIdEspecialidade().toString() : null;
		}
		return null;
	}

	}





/*
 * package br.com.projeto.converters;
 * 
 * import java.io.Serializable;
 * 
 * import javax.faces.component.UIComponent; import
 * javax.faces.context.FacesContext; import javax.faces.convert.Converter;
 * import javax.faces.convert.FacesConverter;
 * 
 * import br.com.projeto.geral.controller.EspecialidadeController; import
 * br.com.projeto.model.classes.Especialidade;
 * 
 * @FacesConverter( value="especialidadeConverter",forClass =
 * Especialidade.class) public class EspecialidadeConverter implements
 * Converter, Serializable {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * @Override public Object getAsObject(FacesContext arg0, UIComponent arg1,
 * String value) { Especialidade toReturn = null; if (value != null) { Long
 * codigo = new Long(value); try { return new
 * EspecialidadeController().findByPorId(Especialidade.class, codigo); } catch
 * (Exception e) { } } return toReturn; }
 * 
 * @Override public String getAsString(FacesContext arg0, UIComponent arg1,
 * Object value) { Especialidade especialidade = (Especialidade) value; if
 * (especialidade == null || especialidade.getIdEspecialidade() == null) {
 * return null; } return
 * String.valueOf(especialidade.getIdEspecialidade().longValue());
 * 
 * }
 * 
 * }
 */
