package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.ConvenioController;
import br.com.projeto.model.classes.Convenio;


@Controller
@Scope(value = "session")
@ManagedBean(name = "convenioBeanView")
public class ConvenioBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Convenio objetoSelecionado = new Convenio();
	
	private String url = "/cadastro/cadConvenio.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findConvenio.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Convenio> list
	= new CarregamentoLazyListForObjeto<Convenio>();
	 
	@Autowired
	private ConvenioController convenioController;
	
	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_convenio");
		super.setNomeRelatorioSaida("report_convenio");
		super.setListDataBeanCollectionReport(convenioController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	public Convenio getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Convenio objetoSelecionado) {

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

	public CarregamentoLazyListForObjeto<Convenio> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	
	@Override
	public String save() throws Exception {
	    objetoSelecionado = convenioController.merge(objetoSelecionado);
	   
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = convenioController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Convenio();
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
		objetoSelecionado = new Convenio();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Convenio) convenioController.
				getSession().get(getClassImp(),  objetoSelecionado.getIdConvenio());
		convenioController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Convenio();
		sucesso();
	}

	@Override
	protected Class<Convenio> getClassImp() {
		return Convenio.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Convenio> getController() {
		return convenioController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Convenio();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
}
