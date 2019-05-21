
package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projeto.geral.controller.EspecialidadeController;
import br.com.projeto.model.classes.Especialidade;

@FacesConverter( value="especialidadeConverter",forClass = Especialidade.class)
public class EspecialidadeConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	//	Especialidade toReturn = null;
	//	if (value != null) {
			//Long codigo = new Long(value); 
			try {
				return new EspecialidadeController().findByPorId(Especialidade.class, Integer.valueOf(value).longValue());
			} catch (Exception e) {
				return new Especialidade();
			}
		}
//	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		 try {
	            return String.valueOf(((Especialidade) value).getIdEspecialidade());
	        } catch (Exception e) {
	            return "";
	        }
	    }
		//return String.valueOf(especialidade.getIdEspecialidade().longValue());

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
