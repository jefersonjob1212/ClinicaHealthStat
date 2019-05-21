package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.model.classes.Cidade;
@FacesConverter(forClass = Cidade.class)
public class CidadeConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()){
			return (Cidade) HibernateUtil.getCurrentSession().get(Cidade.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null){
			Cidade c = (Cidade) objeto;
			return c.getId_cidade() != null && c.getId_cidade() > 0 ? c.getId_cidade().toString() : null;
		}
		return null;
	}

}

