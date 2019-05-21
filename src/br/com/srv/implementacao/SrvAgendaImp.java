package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryAgenda;
import br.com.srv.interfaces.SrvAgenda;

@Service
public class SrvAgendaImp implements SrvAgenda{

	private static final long serialVersionUID = 1L;
	//tdo Imp tem injeção de dependencia do repositorio
	
	
	@Resource 
	private RepositoryAgenda repositoryAgenda;
}
