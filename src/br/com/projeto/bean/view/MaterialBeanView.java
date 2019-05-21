package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.MaterialController;
import br.com.projeto.model.classes.Material;


@Controller
@Scope(value = "session")
@ManagedBean(name = "materialBeanView")
public class MaterialBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Material objetoSelecionado = new Material();
	
	private String url = "/cadastro/cadMaterial.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findMaterial.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Material> list = new CarregamentoLazyListForObjeto<Material>();
	 
	@Autowired
	private MaterialController materialController;
	
	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_material");
		super.setNomeRelatorioSaida("report_material");
		super.setListDataBeanCollectionReport(materialController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	public Material getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Material objetoSelecionado) {

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

	public CarregamentoLazyListForObjeto<Material> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	
	@Override
	public String save() throws Exception {
	    objetoSelecionado = materialController.merge(objetoSelecionado);
	   
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = materialController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Material();
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
		objetoSelecionado = new Material();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Material) materialController.getSession().get(getClassImp(),  objetoSelecionado.getIdMaterial());
		materialController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Material();
		sucesso();
	}

	@Override
	protected Class<Material> getClassImp() {
		return Material.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Material> getController() {
		return materialController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Material();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
}
