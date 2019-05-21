package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projeto.geral.controller.MedicoController;
import br.com.projeto.model.classes.Medico;
@FacesConverter(forClass = Medico.class, value="edicoConverter")
public class MedicoConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	
			try {
				return new MedicoController().findByPorId(Medico.class, Integer.valueOf(value).longValue());
			} catch (Exception e) {
				return new Medico();
			}
		}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		 try {
	            return String.valueOf(((Medico) value).getIdMedico());
	        } catch (Exception e) {
	            return "";
	        }
	    }

	}



