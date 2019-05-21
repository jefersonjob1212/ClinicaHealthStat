package br.com.projeto.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.Agenda;
import br.com.repository.interfaces.RepositoryAgenda;
import br.com.srv.interfaces.SrvAgenda;

@Controller
public class AgendarController extends ImplementacaoCrud<Agenda> implements 
	InterfaceCrud<Agenda>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvAgenda srvAgenda ;
	
	@Resource
	private RepositoryAgenda repositoryAgenda ;
	
	
	
}
