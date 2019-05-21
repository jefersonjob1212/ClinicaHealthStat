package br.com.projeto.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.com.projeto.anotacoes.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name="estado")
@SequenceGenerator(name="estado_seq", sequenceName = "estado_seq" , 
initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta ="estado_id" )
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq")
	private Long estado_id; //(est_id)
	
	@IdentificaCampoPesquisa(descricaoCampo ="Nome:", campoConsulta ="estado_nome", principal = 1)
	@Column(length = 100 , nullable = false)
	private String estado_nome; // (est_nome)
	
	@Column(length = 10 )
	private String estado_uf; // (est_uf)
	
	@NotAudited
	@OneToMany(mappedBy = "estado", orphanRemoval = false) // mappedBy faz referencia ao atributo na classe cidades
	@Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	List<Cidade> cidades = new ArrayList<Cidade>();
	
	/*
	 * @Basic
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER) // sempre que carregar estado ele vai
	 * carregar pais tbm
	 * 
	 * @JoinColumn(name = "pais")
	 * 
	 * @NotNull
	 * 
	 * @ForeignKey(name = "pais_fk") private Pais pais = new Pais();
	 */
	
	

	public Long getEstado_id() {
		return estado_id;
	}

	public void setEstado_id(Long estado_id) {
		this.estado_id = estado_id;
	}

	public String getEstado_nome() {
		return estado_nome;
	}

	public void setEstado_nome(String estado_nome) {
		this.estado_nome = estado_nome;
	}

	public String getEstado_uf() {
		return estado_uf;
	}

	public void setEstado_uf(String estado_uf) {
		this.estado_uf = estado_uf;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	
}
