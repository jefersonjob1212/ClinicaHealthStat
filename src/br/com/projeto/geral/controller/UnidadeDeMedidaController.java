package br.com.projeto.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.UnidadeDeMedida;
import br.com.repository.interfaces.RepositoryUnidadeDeMedida;
import br.com.srv.interfaces.SrvUnidadeDeMedida;

@Controller
public class UnidadeDeMedidaController extends ImplementacaoCrud<UnidadeDeMedida> implements 
	InterfaceCrud<UnidadeDeMedida>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvUnidadeDeMedida srvUnidadeDeMedida;
	
	@Resource
	private RepositoryUnidadeDeMedida repositoryUnidadeDeMedida;
	
}
