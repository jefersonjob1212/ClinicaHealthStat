package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.projeto.geral.controller.EspecialidadeController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.Especialidade;
import br.com.projeto.model.classes.Paciente;
@FacesConverter(forClass = Paciente.class, value="pacienteConverter")
public class PacienteConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
	
	//	if (value != null) {
			//Long codigo = new Long(value); 
			try {
				return new PacienteController().findByPorId(Paciente.class, Integer.valueOf(value).longValue());
			} catch (Exception e) {
				return new Paciente();
			}
		}
//	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		 try {
	            return String.valueOf(((Paciente) value).getIdPaciente());
	        } catch (Exception e) {
	            return "";
	        }
	    }
		//return String.valueOf(especialidade.getIdEspecialidade().longValue());

	}



