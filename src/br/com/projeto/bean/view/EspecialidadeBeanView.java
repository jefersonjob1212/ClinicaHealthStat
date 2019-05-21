package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.EspecialidadeController;
import br.com.projeto.model.classes.Especialidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "especialidadeBeanView")
public class EspecialidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Especialidade objetoSelecionado = new Especialidade();

	private String url = "/cadastro/cadEspecialidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findEspecialidade.jsf?faces-redirect=true";

	private CarregamentoLazyListForObjeto<Especialidade> list = new CarregamentoLazyListForObjeto<Especialidade>();

	@Autowired
	private EspecialidadeController especialidadeController;

	/**
	 * Metodos Getter E Setters dos objetos
	 * 
	 * @return
	 */

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_especialidade");
		super.setNomeRelatorioSaida("report_especialidade");
		super.setListDataBeanCollectionReport(especialidadeController.findList(getClassImp()));
		return super.getArquivoReport();
	}

	/*
	 * public List<SelectItem> getEspecialidades() throws Exception { return
	 * especialidadeController.getListEspecialidades(); }
	 */

	public Especialidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Especialidade objetoSelecionado) {

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

	public CarregamentoLazyListForObjeto<Especialidade> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo
	 */

	@Override
	public String save() throws Exception {
		objetoSelecionado = especialidadeController.merge(objetoSelecionado);

		return "";
	}

	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = especialidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Especialidade();
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
		objetoSelecionado = new Especialidade();
	}

	@Override
	public String editar() throws Exception {
		list.clean();
		return getUrl();
	}

	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Especialidade) especialidadeController.getSession().get(getClassImp(),
				objetoSelecionado.getIdEspecialidade());
		especialidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		objetoSelecionado = new Especialidade();
		sucesso();
	}

	@Override
	protected Class<Especialidade> getClassImp() {
		return Especialidade.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Especialidade> getController() {
		return especialidadeController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Especialidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

}
