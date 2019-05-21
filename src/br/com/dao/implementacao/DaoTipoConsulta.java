package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.projeto.model.classes.TipoConsulta;
import br.com.repository.interfaces.RepositoryTipoConsulta;
@Repository
public class DaoTipoConsulta extends ImplementacaoCrud<TipoConsulta> implements RepositoryTipoConsulta{

	
	private static final long serialVersionUID = 1L;

}
