package br.com.projeto.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.joda.time.DateTime;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.projeto.enums.TipoEvento;
import br.com.projeto.geral.controller.EventoController;
import br.com.projeto.geral.controller.MedicoController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.CustomScheduleEvent;
import br.com.projeto.model.classes.Evento;

@ManagedBean( name= "scheduleBean")
@Controller
@Scope(value = "session")
public class ScheduleBean {
	
	private ScheduleModel model;
    private Evento evento;
    private ScheduleEvent event;
    private List<ScheduleEvent> scheduleEvents;
   
    Date dataAtual = new Date();
    
    //List<Paciente> listaPacientes = new ArrayList<Paciente>();
    
    @Autowired
    private EventoController eventoController;
    
    @Autowired
    private PacienteController pacienteController;
    
    @Autowired
    private MedicoController medicoController;

    public ScheduleBean() {
        event = new CustomScheduleEvent();
        model = new DefaultScheduleModel();
        
        evento = new Evento();
    }
    
	public List<SelectItem> getPacientes() throws Exception {
		return pacienteController.getListPacientes();
	}
    
	public List<SelectItem> getMedicos() throws Exception{
		return medicoController.getListMedicos();
	}

    @PostConstruct
    public void init() throws Exception {
    	
    	if (this.model != null) {
        	List<Evento> eventos = this.eventoController.listarEventos();
          //  List<Evento> eventos = this.eventoDAO.listarTodos();
            if (this.scheduleEvents == null) {
                this.scheduleEvents = new ArrayList<ScheduleEvent>();
            }
            for (Evento eventoAtual : eventos) { //lista que popula os eventos e inseri 
                ScheduleEvent newEvent = new CustomScheduleEvent(eventoAtual.getTitulo(), eventoAtual.getDataInicio(), eventoAtual.getDataFim(), eventoAtual.getTipoEvento().getCss(), eventoAtual.isDiaInteiro(), eventoAtual);
                if (!this.scheduleEvents.contains(newEvent)) {
                    newEvent.setId(eventoAtual.getId().toString());
                    this.scheduleEvents.add(newEvent);
                    this.model.addEvent(newEvent);
                }
            }
        }
    }

    public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void salvar() throws Exception {
    	//salva o construtor que implementa a interface do Schedule com os atributos.
    	ScheduleEvent newEvent =
        		new CustomScheduleEvent(this.evento.getTitulo(), 
        								this.evento.getDataInicio(), 
        								this.evento.getDataFim(), 
        								this.evento.getTipoEvento().getCss(), 
        								this.evento.isDiaInteiro(), 
        								this.evento);
        if (evento.getId() == null) {
            model.addEvent(newEvent);
          //  eventoController.merge(evento);
        } else {
            newEvent.setId(event.getId());
            model.updateEvent(newEvent);
          //  eventoController.merge(evento);
        }
       eventoController.merge(evento);
       // eventoDAO.salvar(evento);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento Salvo", "Agendamento para:  "+evento.getTitulo());
        addMessage(message);
    }

    public void remover() throws Exception {
        model.deleteEvent(event);
        eventoController.delete(evento);
       // eventoDAO.remover(evento);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento Removido", "Agendamento Removido :"+ evento.getTitulo());
        addMessage(message);
    }
   //AO SALVAR SELEÇÃO DE UMA AGENDAMENTO
    public void onDateSelect(SelectEvent selectEvent) {
        this.evento = new Evento();
        Date dataSelecionada = (Date) selectEvent.getObject();
        DateTime dataSelecionadaJoda = new DateTime(dataSelecionada.getTime());
        this.evento.setDataInicio(dataSelecionada);
        this.evento.setDataFim(dataSelecionadaJoda.plusMinutes(30).toDate());
    }
    // EVENTO DE SELEÇÃO DOS HORARIOS AGENDADOS
    public void onEventSelect(SelectEvent selectEvent) {
        event = (CustomScheduleEvent) selectEvent.getObject();
        this.evento = (Evento) event.getData();
    }
    //EVENTO QUE PERMITE REDIMENSIONAR HORARIOS
    public void onEventResize(ScheduleEntryResizeEvent  event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Redimensionado", "Dia:" + event.getDayDelta() + ", Horário:" + event.getMinuteDelta());
        addMessage(message);
    }
    //EVENTO QUE PERMITE MOVER HORARIOS SELECIONADOS 
    public void onEventMove(ScheduleEntryMoveEvent event) {
    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public TipoEvento[] getTiposEventos() {
        return TipoEvento.values();
    }

	public List<ScheduleEvent> getScheduleEvents() {
		return scheduleEvents;
	}

	public void setScheduleEvents(List<ScheduleEvent> scheduleEvents) {
		this.scheduleEvents = scheduleEvents;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}
    
    
}

