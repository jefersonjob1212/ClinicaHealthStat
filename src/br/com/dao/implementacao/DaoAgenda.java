package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.projeto.model.classes.Agenda;
import br.com.repository.interfaces.RepositoryAgenda;
@Repository
public class DaoAgenda extends ImplementacaoCrud<Agenda> implements RepositoryAgenda{

	
	private static final long serialVersionUID = 1L;

}
