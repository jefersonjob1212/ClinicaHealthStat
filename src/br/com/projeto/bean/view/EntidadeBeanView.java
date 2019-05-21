package br.com.projeto.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.framework.inteface.crud.InterfaceCrud;
import br.com.projeto.bean.geral.BeanManagedViewAbstract;
import br.com.projeto.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.projeto.geral.controller.EntidadeController;
import br.com.projeto.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean( name ="entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	@Autowired
	private ContextoBean contextoBean;
	
	private static final long serialVersionUID = 1L;
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	Entidade entidade = new Entidade();
	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual()
				.equals(entidadeLogada.getEnt_senha())){
			addMsg("A senha atual não é valida.");
			return;
		}else if (entidadeAtualizaSenhaBean.getSenhaAtual()
			.equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha.");
			return;
		}else if (!entidadeAtualizaSenhaBean.getNovaSenha()
				.equals(entidadeAtualizaSenhaBean.getConfirmaSenha())){
			addMsg("A nova senha e a confirmção não conferem.");
			return;
		}else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController
			.findByPorId(Entidade.class, entidadeLogada.getEnt_codigo());
			if (entidadeLogada.getEnt_senha()
				.equals(entidadeAtualizaSenhaBean.getNovaSenha())){
				sucesso();
			entidadeLogada = new Entidade(); 
			}else {
				addMsg("A nova senha não foi atualizada");
				error();
			}
			
		}

	}
	@Autowired
	private EntidadeController entidadeController;

	//GETTERS E SETTERS 
	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoAcesso();
	}

	
	@Override
	protected Class<Entidade> getClassImp() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
