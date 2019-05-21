package br.com.projeto.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.model.classes.Especialidade;
import br.com.repository.interfaces.RepositoryEspecialidade;
import br.com.srv.interfaces.SrvEspecialidade;

@Controller
public class EspecialidadeController extends ImplementacaoCrud<Especialidade> implements 
	InterfaceCrud<Especialidade>{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvEspecialidade srvEspecialidade;
	
	@Resource
	private RepositoryEspecialidade repositoryEspecialidade;
	
	//Buscando no Banco de dados a lista de especialidades
	
	public List<Especialidade> listaEspecialidades() throws Exception{
		// Hibernate.initialize(listaEspecialidades());
		return super.findListByQueryDinamica(" from Especialidade");
	}
	

	
}
