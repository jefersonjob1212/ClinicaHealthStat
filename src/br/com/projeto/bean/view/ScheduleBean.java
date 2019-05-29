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
import org.joda.time.LocalDate;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.carregamento.lazy.CarregamentoLazyListForObjeto;
import br.com.projeto.enums.TipoEvento;
import br.com.projeto.geral.controller.EventoController;
import br.com.projeto.geral.controller.MedicoController;
import br.com.projeto.geral.controller.PacienteController;
import br.com.projeto.model.classes.CustomScheduleEvent;
import br.com.projeto.model.classes.Evento;

@ManagedBean(name = "scheduleBean")
@Controller
@Scope(value = "session")
public class ScheduleBean extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private ScheduleModel model;
	private Evento evento = new Evento();
	private ScheduleEvent event;
	private List<ScheduleEvent> scheduleEvents;

	Date dataAtual = new Date();

	private CarregamentoLazyListForObjeto<Evento> list = new CarregamentoLazyListForObjeto<Evento>();

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

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_evento");
		super.setNomeRelatorioSaida("report_evento");
		super.setListDataBeanCollectionReport(eventoController.findList(getClassImp()));
		return super.getArquivoReport();
	}

	// LISTA DE PACIENTES
	public List<SelectItem> getPacientes() throws Exception {
		return pacienteController.getListPacientes();
	}

	// LISTA DE MEDICOS
	public List<SelectItem> getMedicos() throws Exception {
		return medicoController.getListMedicos();
	}

	@PostConstruct
	public void init() throws Exception {

		if (this.model != null) {
			List<Evento> eventos = this.eventoController.listarEventos();
			// List<Evento> eventos = this.eventoDAO.listarTodos();
			if (this.scheduleEvents == null) {
				this.scheduleEvents = new ArrayList<ScheduleEvent>();
			}
			for (Evento eventoAtual : eventos) { // lista que popula os eventos e inseri
				ScheduleEvent newEvent = new CustomScheduleEvent(eventoAtual.getTitulo(), eventoAtual.getDataInicio(),
						eventoAtual.getDataFim(), eventoAtual.getTipoEvento().getCss(), eventoAtual.isDiaInteiro(),
						eventoAtual.getDescricao(), eventoAtual.getMedico(), eventoAtual.getPaciente(), eventoAtual);
				if (!this.scheduleEvents.contains(newEvent)) {
					newEvent.setId(eventoAtual.getId().toString());
					this.scheduleEvents.add(newEvent);
					this.model.addEvent(newEvent);
				}
			}
		}
	}

	public boolean validarMedico() throws Exception {
	
		List<Evento> lista = 
		eventoController.
		findListByQueryDinamica("FROM Evento e WHERE e.medico.idMedico = "	+ evento.getMedico().getIdMedico());
		

		/*+" and e.dataInicio >= "+evento.getDataInicio().TO +  " and e.dataFim <= " +evento.getDataFim() */
		
		return lista.isEmpty();
	}

	public void salvar() throws Exception {
		// salva o construtor que implementa a interface do Schedule com os atributos.
		ScheduleEvent newEvent = new CustomScheduleEvent(this.evento.getTitulo(), this.evento.getDataInicio(),
				this.evento.getDataFim(), this.evento.getTipoEvento().getCss(), this.evento.isDiaInteiro(),
				this.evento.getDescricao(), this.evento.getMedico(), this.evento.getPaciente(), this.evento);

		if (evento.getDataInicio().before(evento.getDataFim()) && validarMedico()) {
			
			if (evento.getId() == null) {
				model.addEvent(newEvent);
			} else {
				newEvent.setId(event.getId());
				model.updateEvent(newEvent);
				// eventoController.merge(evento);
			}
			eventoController.merge(evento);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento Salvo",
					"Agendamento para:  " + evento.getTitulo());
			addMessage(message);
		}else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Já existe um médico cadastrado",
					"" );
			addMessage(message);
		}
	}

	public void remover() throws Exception {
		model.deleteEvent(event);
		eventoController.delete(evento);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento Removido",
				"Agendamento Removido :" + evento.getTitulo());
		addMessage(message);
	}

	// AO SALVAR SELEÇÃO DE UMA AGENDAMENTO
	public void onDateSelect(SelectEvent selectEvent) {
		this.evento = new Evento();
		Date dataSelecionada = (Date) selectEvent.getObject();
		@SuppressWarnings("unused")
		DateTime dataSelecionadaJoda = new DateTime(dataSelecionada.getTime());
		this.evento.setDataInicio(dataSelecionada);
		// Adiciona 30min por consulta
		//this.evento.setDataFim(dataSelecionadaJoda.plusMinutes(30).toDate());
	}

	// EVENTO DE SELEÇÃO DOS HORARIOS AGENDADOS
	public void onEventSelect(SelectEvent selectEvent) {
		event = (CustomScheduleEvent) selectEvent.getObject();
		this.evento = (Evento) event.getData();
	}

	// EVENTO QUE PERMITE REDIMENSIONAR HORARIOS
	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Redimensionado",
				"Dia:" + event.getDayDelta() + ", Horário:" + event.getMinuteDelta());
		addMessage(message);
	}

	// EVENTO QUE PERMITE MOVER HORARIOS SELECIONADOS
	public void onEventMove(ScheduleEntryMoveEvent event) {
		/*
		 * if(evento.getDataInicio().getTime() <= evento.getDataFim().getTime()){
		 * 
		 * try{ eventoController.merge(evento); }catch(Exception ex){
		 * FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("Erro ao salvar trabalho", "Erro:" + ex.getMessage())); } evento
		 * = new Evento(); }else{ FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage("Data do começo do evento não pode ser maior que a do final",
		 * "")); }
		 */

		// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event
		// moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" +
		// event.getMinuteDelta());

		// addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TipoEvento[] getTiposEventos() {
		return TipoEvento.values();
	}

	// GETTERS E SETTERS

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
		// Pega somente a data para passar para data minima do calendario
		LocalDate localDate = new LocalDate();
		dataAtual = localDate.toDate();

		this.dataAtual = dataAtual;
	}

	@Override
	protected Class<Evento> getClassImp() {
		return Evento.class;
	}

	@Override
	protected InterfaceCrud<Evento> getController() {
		return eventoController;
	}

	@Override
	public void consultarEntidade() throws Exception {
		evento = new Evento();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());

	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return null;
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

	public CarregamentoLazyListForObjeto<Evento> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObjeto<Evento> list) {
		this.list = list;
	}

}
