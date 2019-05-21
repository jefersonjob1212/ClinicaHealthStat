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
import br.com.projeto.geral.controller.AtendenteController;
import br.com.projeto.geral.controller.CidadeController;
import br.com.projeto.model.classes.Atendente;
import br.com.projeto.model.classes.Pessoa;


@Controller
@Scope(value = "session")
@ManagedBean(name = "atendenteBeanView")
public class AtendenteBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Atendente objetoSelecionado = new Atendente();
	private String url = "/cadastro/cadAtendente.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findAtendente.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Atendente> list = new CarregamentoLazyListForObjeto<Atendente>();
	
	@Autowired
	private AtendenteController atendenteController; // Injeta o Atendente Controller
	
	@Autowired
	private CidadeController cidadeController; //Injeta a Cidade para uso da lista de cidades
	
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_atendente");
		super.setNomeRelatorioSaida("report_atendente");
		super.setListDataBeanCollectionReport(atendenteController.findList(getClassImp()));
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
		//pessoaController.pesquisaCep(null);
		//System.out.println("Metodo pesquisa" + objetoSelecionado.getPessoa().getPessoaCep() );
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	@Override
	public String save() throws Exception {
	    
		objetoSelecionado = atendenteController.merge(objetoSelecionado);
		objetoSelecionado = new Atendente();
		
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = atendenteController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Atendente();
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
		objetoSelecionado = new Atendente();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Atendente) atendenteController.getSession().get(getClassImp(),  objetoSelecionado.getIdAtendente());
		atendenteController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Atendente();
		sucesso();
	}

	@Override
	protected Class<Atendente> getClassImp() {
		return Atendente.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		sucesso();
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Atendente> getController() {
		return atendenteController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Atendente();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}

	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	
	public Atendente getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Atendente objetoSelecionado) {

		this.objetoSelecionado = objetoSelecionado;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUrlFind() {
		System.out.println("Chamou URLFIND");
		return urlFind;
	}

	public void setUrlFind(String urlFind) {
		this.urlFind = urlFind;
	}

	public CarregamentoLazyListForObjeto<Atendente> getList() throws Exception {
		return list;
	}
}
