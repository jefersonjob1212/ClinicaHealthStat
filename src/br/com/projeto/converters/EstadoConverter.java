package br.com.projeto.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.projeto.geral.controller.EstadoController;
import br.com.projeto.model.classes.Estado;
@FacesConverter(forClass = Estado.class, value = "estadoConverter")
public class EstadoConverter  implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigoEstado) {
			
		try {
			return new EstadoController().findById(Estado.class,Long.parseLong(codigoEstado));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigoEstado;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object estado) {
		if (estado == null){
			return null;
		}
		
		if (estado instanceof Estado){
			return ((Estado) estado).getEstado_id().toString();

		}else {
			return estado.toString();
		}
		}
//return String.valueOf(especialidade.getIdEspecialidade().longValue());

}

