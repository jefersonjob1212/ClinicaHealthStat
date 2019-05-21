package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.UnidadeDeMedidaController;
import br.com.projeto.model.classes.UnidadeDeMedida;


@Controller
@Scope(value = "session")
@ManagedBean(name = "unidadeDeMedidaBeanView")
public class TipoConsultaBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private UnidadeDeMedida objetoSelecionado = new UnidadeDeMedida();
	
	private String url = "/cadastro/cad_unidadeDeMedida.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_unidadeDeMedida.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<UnidadeDeMedida> list
	= new CarregamentoLazyListForObjeto<UnidadeDeMedida>();
	 
	@Autowired
	private UnidadeDeMedidaController unidadeDeMedidaController;
	
	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_unidadeDeMedida");
		super.setNomeRelatorioSaida("report_unidadeDeMedida");
		super.setListDataBeanCollectionReport(unidadeDeMedidaController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	public UnidadeDeMedida getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(UnidadeDeMedida objetoSelecionado) {

		this.objetoSelecionado = objetoSelecionado;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUrlFind() {
		return urlFind;
	}

	public void setUrlFind(String urlFind) {
		this.urlFind = urlFind;
	}

	public CarregamentoLazyListForObjeto<UnidadeDeMedida> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	
	@Override
	public String save() throws Exception {
	    objetoSelecionado = unidadeDeMedidaController.merge(objetoSelecionado);
	   
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = unidadeDeMedidaController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new UnidadeDeMedida();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return getUrl();
	}
	
	@Override
	public void setarVariaveisNulas() throws Exception {
		list.clean();
		objetoSelecionado = new UnidadeDeMedida();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (UnidadeDeMedida) unidadeDeMedidaController.getSession().get(getClassImp(),  objetoSelecionado.getIdUnidadeDeMedida());
		unidadeDeMedidaController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new UnidadeDeMedida();
		sucesso();
	}

	@Override
	protected Class<UnidadeDeMedida> getClassImp() {
		return UnidadeDeMedida.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<UnidadeDeMedida> getController() {
		return unidadeDeMedidaController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new UnidadeDeMedida();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
}
