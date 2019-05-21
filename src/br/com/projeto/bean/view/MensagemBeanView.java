package br.com.projeto.bean.view;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.geral.controller.EntidadeController;
import br.com.projeto.geral.controller.MensagemController;
import br.com.projeto.model.classes.Entidade;
import br.com.projeto.model.classes.Mensagem;

@Controller
@Scope(value= "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private Mensagem objetoSelecionado = new Mensagem();
	
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private MensagemController mensagemController;

	@Override
	protected Class<?> getClassImp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	//GETTERS E SETTERS
	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	public ContextoBean getContextoBean() {
		return contextoBean;
	}
	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}
	public EntidadeController getEntidadeController() {
		return entidadeController;
	}
	public void setEntidadeController(EntidadeController entidadeController) {
		this.entidadeController = entidadeController;
	}
	//--------------------------------------------------------------------------
	
	//METODOS
	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Mensagem();
		//seta ao campo usuario origem o usurio atual 
		objetoSelecionado.setUsr_origem(contextoBean.getEntidadeLogada());
		return "";
	}
	@Override
	public void saveNotReturn() throws Exception {
		if(objetoSelecionado.getUsr_destino().getEnt_codigo()
				.equals(objetoSelecionado.getUsr_origem().getEnt_codigo())){
			addMsg("Origem não pode ser igual ao destino.");
			return;
		}

		mensagemController.merge(objetoSelecionado);
		novo();
		addMsg("Mensagem enviada com sucesso!");
	}
	@RequestMapping("**/buscarUsuarioDestinoMsg")
	public void buscarUsuarioDestinoMsg( HttpServletResponse httpServletResponse
			, @RequestParam(value = "codEntidade") Long codEntidade) throws Exception{
		
		Entidade entidade = entidadeController.findByPorId(Entidade.class, codEntidade);
		if(entidade != null ) {
			objetoSelecionado.setUsr_destino(entidade);
			httpServletResponse.getWriter().write(entidade.getJson().toString());
		}
	}
	
	
}
