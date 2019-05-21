package br.com.projeto.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.Estado;

@Controller
public class EstadoController extends ImplementacaoCrud<Estado> implements 
	InterfaceCrud<Estado>{

	private static final long serialVersionUID = 1L;

	
	public List<SelectItem> getListEstado() throws Exception {
		List<SelectItem> list = new ArrayList<SelectItem>();

		List<Estado> estados = super.findListByQueryDinamica(" from Estado");

		for (Estado estado : estados) {
			list.add(new SelectItem(estado.getEstado_id(), estado.getEstado_nome()));
		}
		return list;
	}
}
