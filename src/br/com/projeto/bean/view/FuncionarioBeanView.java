package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.EntidadeController;
import br.com.projeto.model.classes.Entidade;

@Controller
@Scope("session")
@ManagedBean(name="funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract{
	
	private static final long serialVersionUID = 1L;

	private Entidade objetoSelecionado = new Entidade();
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private CarregamentoLazyListForObjeto<Entidade> list = new CarregamentoLazyListForObjeto<Entidade>();

	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	
	@Override
	protected Class<?> getClassImp() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";	
		//and entity.tipoEntidade = 'FUNCIONARIO' 
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		return getUrlFind();
	}
	
	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	
	  public String getUrlFind() {
		return urlFind;
	}

	public void setUrlFind(String urlFind) {
		this.urlFind = urlFind;
	}

	public void setList(CarregamentoLazyListForObjeto<Entidade> list) { 
		this.list = list; 
	} 
	
	public CarregamentoLazyListForObjeto<Entidade> getList() { return
	  list; 
	}
	 
	  
	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
		
	}
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_codigo() != null
				&& objetoSelecionado.getEnt_codigo() > 0 ){
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}


}
