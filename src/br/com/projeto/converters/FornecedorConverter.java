package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.model.classes.Fornecedor;
@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()){
			return (Fornecedor) HibernateUtil.getCurrentSession().get(Fornecedor.class, new Long(codigo));
		}
		return codigo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null){
			Fornecedor c = (Fornecedor) objeto;
			return c.getIdFornecedor() != null && c.getIdFornecedor() > 0 ? c.getIdFornecedor().toString() : null;
		}
		return null;
	}

}

