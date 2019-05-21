package br.com.projeto.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.Cidade;
import br.com.repository.interfaces.RepositoryCidade;
import br.com.srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements 
	InterfaceCrud<Cidade>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvCidade srvCidade ;
	
	@Resource
	private RepositoryCidade repositoryCidade;
	
	public List<SelectItem> getListCidades() throws Exception {
		List<SelectItem> list = new ArrayList<SelectItem>();

		List<Cidade> cidades = super.findListByQueryDinamica(" from Cidade");

		for (Cidade cidade : cidades) {
			list.add(new SelectItem(cidade, cidade.getCidade_descricao()));
		}
		return list;
	}
	
}
