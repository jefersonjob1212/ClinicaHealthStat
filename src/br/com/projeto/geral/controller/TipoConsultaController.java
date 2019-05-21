package br.com.projeto.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.TipoConsulta;
import br.com.repository.interfaces.RepositoryTipoConsulta;
import br.com.srv.interfaces.SrvTipoConsulta;

@Controller
public class TipoConsultaController extends ImplementacaoCrud<TipoConsulta> implements 
	InterfaceCrud<TipoConsulta>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvTipoConsulta SrvTipoConsulta;
	
	@Resource
	private RepositoryTipoConsulta repositoryTipoConsulta;
	
}
