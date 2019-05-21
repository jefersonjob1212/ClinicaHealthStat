package br.com.projeto.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;



/**
 * @author Humberto
 *
 */
@Entity
@Audited
@Table(name="material")
@SequenceGenerator(name="material_seq", sequenceName="material_seq", initialValue = 1, allocationSize = 1)
public class Material implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="idMaterial", principal = 1 )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "material_seq")
	
	private Long idMaterial;
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "nomeMaterial", principal = 2)
	private String nomeMaterial;
	
	private long quantidade;
	
	private Double valorUnitario;
	
	@Basic
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "fornecedor", nullable = false)
	@ForeignKey(name = "idFornecedorFK")
	private Fornecedor fornecedor = new Fornecedor();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	//EQUALS E HASCODE
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMaterial == null) ? 0 : idMaterial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (idMaterial == null) {
			if (other.idMaterial != null)
				return false;
		} else if (!idMaterial.equals(other.idMaterial))
			return false;
		return true;
	}

	//GETTERS E SETTERS
	public Long getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(Long idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getNomeMaterial() {
		return nomeMaterial;
	}

	public void setNomeMaterial(String nomeMaterial) {
		this.nomeMaterial = nomeMaterial;
	}
	
	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	
	@Override
	public String toString() {
		return "Material [idMaterial=" + idMaterial + ", nomeMaterial=" + nomeMaterial + ", quantidade=" + quantidade
				+ ", valorUnitario=" + valorUnitario + "]";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}
