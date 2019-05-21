package br.com.projeto.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.FornecedorController;
import br.com.projeto.model.classes.Fornecedor;


@Controller
@Scope(value = "session")
@ManagedBean(name = "fornecedorBeanView")
public class FornecedorBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Fornecedor objetoSelecionado = new Fornecedor();
	
	private String url = "/cadastro/cadFornecedor.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findFornecedor.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Fornecedor> list = new CarregamentoLazyListForObjeto<Fornecedor>();
	
	@Autowired
	private FornecedorController fornecedorController;
	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_Fornecedor");
		super.setNomeRelatorioSaida("report_Fornecedor");
		super.setListDataBeanCollectionReport(fornecedorController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	public List<SelectItem> getFornecedores() throws Exception{
		//System.out.println("Cidades Lista Chamado");
		return fornecedorController.getListFornecedores();
	}
	
	public Fornecedor getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Fornecedor objetoSelecionado) {

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

	public CarregamentoLazyListForObjeto<Fornecedor> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	
	@Override
	public String save() throws Exception {
	    
		objetoSelecionado = fornecedorController.merge(objetoSelecionado);
		objetoSelecionado = new Fornecedor();
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = fornecedorController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Fornecedor();
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
		objetoSelecionado = new Fornecedor();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Fornecedor) fornecedorController.getSession().get(getClassImp(),  objetoSelecionado.getIdFornecedor());
		fornecedorController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Fornecedor();
		sucesso();
	}

	@Override
	protected Class<Fornecedor> getClassImp() {
		return Fornecedor.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Fornecedor> getController() {
		return fornecedorController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Fornecedor();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
}
