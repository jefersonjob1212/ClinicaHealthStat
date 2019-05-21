package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.projeto.model.classes.UnidadeDeMedida;
import br.com.repository.interfaces.RepositoryUnidadeDeMedida;
@Repository
public class DaoUnidadeDeMedida extends ImplementacaoCrud<UnidadeDeMedida> 
implements RepositoryUnidadeDeMedida {

	
	private static final long serialVersionUID = 1L;

}
