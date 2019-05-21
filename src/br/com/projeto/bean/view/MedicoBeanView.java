package br.com.projeto.bean.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Hibernate;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.geral.controller.CidadeController;
import br.com.projeto.geral.controller.EspecialidadeController;
import br.com.projeto.geral.controller.MedicoController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.Especialidade;
import br.com.projeto.model.classes.Medico;
import br.com.projeto.model.classes.Pessoa;

@Controller
@Scope(value = "session")
@ManagedBean(name = "medicoBeanView")
public class MedicoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private Medico objetoSelecionado = new Medico();

	//private Especialidade especialidadeMedica = new Especialidade();
	
	private List<Especialidade> especialidades = new ArrayList<>();

	private String url = "/cadastro/cadMedico.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/findMedico.jsf?faces-redirect=true";

	private CarregamentoLazyListForObjeto<Medico> list = new CarregamentoLazyListForObjeto<Medico>();
	
	
	@Autowired
	private MedicoController medicoController; //Injetando o Controller do Médico
	
	
	@Autowired
	private EspecialidadeController especialidadeController; //Injetando o Lista de Especialidade
	
	@Autowired
	private CidadeController cidadeController; //Injetando A lista de Cidades
	
	
	//Relatório
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_medico");
		super.setNomeRelatorioSaida("report_medico");
		super.setListDataBeanCollectionReport(medicoController.findList(getClassImp()));
		return super.getArquivoReport();
	}

	//Chamando Lista de Especialidades do EspecialidadeController Camada de Persistênsia
	//Quando inicia o sistema
	@PostConstruct
	public void init() throws Exception {
		try {
			
			especialidades = especialidadeController.listaEspecialidades();
			
		} catch (Exception e) {
			e.printStackTrace();
			addMsg("Ocorreu um erro ao Carrega a lista de Especialidades");
		}  
	}
	
	// *************** LISTA DE CIDADES ************************************
	
	public List<SelectItem> getCidades() throws Exception{
		return cidadeController.getListCidades();
	}
	
	// *************** LISTA DE CIDADES ************************************
	
	
	/**
	 * Metodos para manipular salvamento, exclusões, editar, novo
	 * @throws Exception 
	 */
	
	// Busca o CEP WService
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
			
			
			objetoSelecionado.getPessoa().setCep(gson.getCep());
			objetoSelecionado.getPessoa().setLogradouro(gson.getLogradouro());
			objetoSelecionado.getPessoa().setBairro(gson.getBairro());
			objetoSelecionado.getPessoa().setIbge(gson.getIbge());
			objetoSelecionado.getPessoa().setComplemento(gson.getComplemento());
			objetoSelecionado.getPessoa().setUf(gson.getUf());
			
			System.out.println("CEP Saindo " +jsonCEP);
			
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			addMsg("Cep Inválido");
			error();
			System.out.println("Erro ao buscar cep 'Internet' ");
		} catch (IOException   e) {
			//CAI AQUI SE DIGITAR MAIS NUMEROS DO QUE TEM UM CEP
			//COLOQUEI UM LIMITADOR NO CAMPO DE DIGITOS
			addMsg("Cep Inválido");
		}
	}
	
	@Override
	public String save() throws Exception {

		objetoSelecionado = medicoController.merge(objetoSelecionado);
		objetoSelecionado = new Medico();
		return "";
	}
	//Save na pagina 
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
		objetoSelecionado = medicoController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Medico();
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
		objetoSelecionado = new Medico();
	}

	@Override
	public String editar() throws Exception {
		list.clean();
		return getUrl();
	}

	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Medico) medicoController.getSession().get(getClassImp(), objetoSelecionado.getIdMedico());
		medicoController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		objetoSelecionado = new Medico();
		sucesso();
	}

	@Override
	protected Class<Medico> getClassImp() {
		return Medico.class;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return getUrlFind();
	}

	@Override
	protected InterfaceCrud<Medico> getController() {
		return medicoController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Medico();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
	//Getter e Setters
		public Medico getObjetoSelecionado() {
			return objetoSelecionado;
		}

		public void setObjetoSelecionado(Medico objetoSelecionado) {

			this.objetoSelecionado = objetoSelecionado;
		}
		
	/*
	 * public Especialidade getEspecialidadeMedica() { return especialidadeMedica; }
	 * 
	 * public void setEspecialidadeMedica(Especialidade especialidadeMedica) {
	 * this.especialidadeMedica = especialidadeMedica; }
	 */

		public List<Especialidade> getEspecialidades() {
			if (especialidades == null) {
				especialidades = new ArrayList<Especialidade>();
			}
			return especialidades;
		}

		public void setEspecialidades(List<Especialidade> especialidades) {
			this.especialidades = especialidades;
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

		public CarregamentoLazyListForObjeto<Medico> getList() throws Exception {
			Hibernate.initialize(list);
			return list;
		}
		
		public void setList(CarregamentoLazyListForObjeto<Medico> list) {
			this.list = list;
		}
		
	
	

	

}
