package br.com.projeto.bean.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.EstoquistaController;
import br.com.projeto.model.classes.Estoquista;
import br.com.projeto.model.classes.Pessoa;


@Controller
@Scope(value = "session")
@ManagedBean(name = "estoquistaBeanView")
public class EstoquistaBeanView extends BeanManagedViewAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private Estoquista objetoSelecionado = new Estoquista();
	
	private String url = "/cadastro/cadEstoquista.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findEstoquista.jsf?faces-redirect=true";
	
	private CarregamentoLazyListForObjeto<Estoquista> list = new CarregamentoLazyListForObjeto<Estoquista>();
	
	@Autowired
	private EstoquistaController estoquistaController;
	
	/**
	 * Metodos Getter E Setters dos objetos
	 * @return
	 */
	
	//Get Objeto Estoquista
	public Estoquista getObjetoSelecionado() {
		return objetoSelecionado;
	}
	//Set Objeto Estoquista
	public void setObjetoSelecionado(Estoquista objetoSelecionado) {

		this.objetoSelecionado = objetoSelecionado;
	}
	//Get Url do Cadastro
	public String getUrl() {
		return url;
	}
	//Get Url da Pesquisa
	public String getUrlFind() {
		return urlFind;
	}
	//Set Url da Pesquisa
	public void setUrlFind(String urlFind) {
		this.urlFind = urlFind;
	}
	//Get Lista de Estoquista no Pesquisa 
	public CarregamentoLazyListForObjeto<Estoquista> getList() throws Exception {
		return list;
	}

	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo 
	 */
	
	//Gera o Relatório 
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_estoquista");
		super.setNomeRelatorioSaida("report_estoquista");
		super.setListDataBeanCollectionReport(estoquistaController.findList(getClassImp()));
		return super.getArquivoReport();
	}
	
	/**
	 * Pesquisa o Cep Via Cep
	 * @param event
	 * @throws Exception
	 */
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

	@Override
	public String save() throws Exception {
	    
		objetoSelecionado = estoquistaController.merge(objetoSelecionado);
		objetoSelecionado = new Estoquista();
		return "";
	}
	
	//Save do Botão salvar *Composite*
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = estoquistaController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Estoquista();
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
		objetoSelecionado = new Estoquista();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();	
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Estoquista) estoquistaController.getSession().get(getClassImp(),  objetoSelecionado.getIdEstoquista());
		estoquistaController.delete(objetoSelecionado);	
		list.remove(objetoSelecionado);
		objetoSelecionado = new Estoquista();
		sucesso();
	}

	@Override
	protected Class<Estoquista> getClassImp() {
		return Estoquista.class;
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Estoquista> getController() {
		return estoquistaController;
	}
	@Override
	public void consultarEntidade() throws Exception {
		 objetoSelecionado = new Estoquista();
		 list.clean();
		 list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
}
