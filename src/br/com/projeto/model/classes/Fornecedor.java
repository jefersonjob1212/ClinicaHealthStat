package br.com.projeto.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

/**
 * @author Humberto
 *
 */

@Audited
@Entity
@Table(name = "fornecedor")
@SequenceGenerator(name = "fornecedor_seq", sequenceName = "fornecedor_seq", initialValue = 1, allocationSize = 1)
public class Fornecedor  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idFornecedor", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "fornecedor_seq")
	private Long idFornecedor;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Fornecedo", campoConsulta = "nomeFornecedor", principal = 3)
	@Column(name = "nomeFornecedor")
	private String nomeFornecedor;
	
	@Column(name = "cnpj")
	private String cnpj;
	
	@Column(name = "inscricaoEstadual")
	private String inscricaoEstadual;
	
	@Column(name = "razaoSocial")
	private String razaoSocial;
	
	@Column(name = "nomeFantasia")
	private String nomeFantasia;

	@Column(name = "telefone")
	private String telefone;

	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	
	//GETTERS E SETTERS-------------------------
	
	
	
	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
}
