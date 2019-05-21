package br.com.projeto.bean.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.CidadeController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.Paciente;
import br.com.projeto.model.classes.Pessoa;


@Controller
@Scope(value = "session")
@ManagedBean(name = "pacienteBeanView")
public class PacienteBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Paciente objetoSelecionado = new Paciente();
	
	private String url = "/cadastro/cadPaciente.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findPaciente.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Paciente> list = new CarregamentoLazyListForObjeto<Paciente>();
	
	@Autowired
	private PacienteController pacienteController;
	
	@Autowired
	private CidadeController cidadeController;
	
	//RELATORIO
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_paciente");
		super.setNomeRelatorioSaida("report_paciente");
		super.setListDataBeanCollectionReport(pacienteController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	// *************** LISTA DE CIDADES ************************************
	
		public List<SelectItem> getCidades() throws Exception{
			return cidadeController.getListCidades();
		}
		
	// *************** LISTA DE CIDADES ************************************
	
	//PESQUISA CEP
	public void pesquisarCep(AjaxBehaviorEvent event) throws Exception {
		try {
			URL url = new URL("https://viacep.com.br/ws/"+ objetoSelecionado.getPessoa().getCep() +"/json/");
			URLConnection connection = url.openConnection();
			InputStream inputStream = connection.getInputStream(); //is
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8")); //br
		
			String cep = "";
			StringBuilder jsonCEP = new StringBuilder();
			
			while((cep = bufferedReader.readLine()) != null)    {
				jsonCEP.append(cep);
				
			}
			
			Pessoa gson = new Gson().fromJson(jsonCEP.toString(), Pessoa.class);
			
			//String logradouro ="";
			objetoSelecionado.getPessoa().setCep(gson.getCep());
			objetoSelecionado.getPessoa().setLogradouro(gson.getLogradouro());
			objetoSelecionado.getPessoa().setBairro(gson.getBairro());
			objetoSelecionado.getPessoa().setIbge(gson.getIbge());
			objetoSelecionado.getPessoa().setComplemento(gson.getComplemento());
			System.out.println("CEP Saindo " +jsonCEP);
			
		} catch (Exception e) {
			e.printStackTrace();
			error();
			System.out.println("Erro ao buscar cep 'Internet' ");
		}
	}
	
	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	@Override
	public String save() throws Exception {
	    
		objetoSelecionado = pacienteController.merge(objetoSelecionado);
		objetoSelecionado = new Paciente();
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = pacienteController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Paciente();
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
		objetoSelecionado = new Paciente();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Paciente) pacienteController.getSession().get(getClassImp(),  objetoSelecionado.getIdPaciente());
		pacienteController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Paciente();
		sucesso();
	}

	@Override
	protected Class<Paciente> getClassImp() {
		return Paciente.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Paciente> getController() {
		return pacienteController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Paciente();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

	//Getters e setters 
	public Paciente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Paciente objetoSelecionado) {

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

	public CarregamentoLazyListForObjeto<Paciente> getList() throws Exception {
		return list;
	}

	
}
