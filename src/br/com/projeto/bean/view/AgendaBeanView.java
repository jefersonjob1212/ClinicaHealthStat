package br.com.projeto.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.projeto.geral.controller.AgendarController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.Agenda;
import br.com.projeto.model.classes.Paciente;
import br.com.projeto.util.all.BeanViewAbstract;

@ManagedBean( name= "agendaBeanView")
@Controller
@Scope(value = "session")
public class AgendaBeanView extends BeanViewAbstract {

private static final long serialVersionUID = 1L;
	
	private ScheduleModel model;

	private Agenda objetoSelecionado = new Agenda();

	Date dataAtual = new Date();	
	
	List<Paciente> listaPacientes = new ArrayList<>();
	
	private ScheduleEvent event = new DefaultScheduleEvent();
	
	@Autowired
	private AgendarController agendaController;
	
	@Autowired
	private PacienteController pacienteController;
	
	
	// *************** LISTA DE PACIENTES  ************************************
		public List<SelectItem> getPacientes() throws Exception {
			return pacienteController.getListPacientes();
		}
		
	// *************** LISTA DE PACIENTES************************************
	
	@PostConstruct
	public void init() throws Exception {
		model = new DefaultScheduleModel();
		//listaPacientes = pacienteController.listaPacientes();
	}
	
	public void novoItem(SelectEvent event) {
		//objetoSelecionado.setDataInicio((Calendar) event.getObject());
		objetoSelecionado.setDataInicio((Date) event.getObject());
		//objetoSelecionado = new Agenda();
		//Chama banco 
		//listar
	}
	
	@Override
		public void saveNotReturn() throws Exception {
		//list.clean();
		objetoSelecionado = agendaController.merge(objetoSelecionado);
	//	list.add(objetoSelecionado);
		if(event.getId() == null)
			model.addEvent(event);
        else
        	model.updateEvent(event);
         
       // event = new DefaultScheduleEvent();
       
        objetoSelecionado = new Agenda();
		sucesso();
		}
	
	
	
	public Agenda getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Agenda objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}
	

	
	

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public List<Paciente> getListPacientes() {
		return listaPacientes;
	}

	public void setListPacientes(List<Paciente> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	

	
	
}