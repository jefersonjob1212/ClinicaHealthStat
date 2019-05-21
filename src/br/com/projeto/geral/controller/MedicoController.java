package br.com.projeto.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.Medico;
import br.com.repository.interfaces.RepositoryMedico;
import br.com.srv.interfaces.SrvMedico;

@Controller
public class MedicoController extends ImplementacaoCrud<Medico> implements 
	InterfaceCrud<Medico>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvMedico srvMedico ;
	
	@Resource
	private RepositoryMedico repositoryMedico;
	
	
	
}
