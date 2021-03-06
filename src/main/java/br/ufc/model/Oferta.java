package br.ufc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Oferta {
	
	@Id
	@Column(name = "oferta_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="pessoa_id",
			referencedColumnName="pessoa_id")
	private Pessoa autor;
	
	@NotNull
	private Float valor;
	
	@OneToOne
	@JoinColumn(name="classificado_id",
			referencedColumnName="classificado_id")
	private Classificado classificado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pessoa getAutor() {
		return autor;
	}
	public void setAutor(Pessoa autor) {
		this.autor = autor;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	public Classificado getClassificado() {
		return classificado;
	}
	public void setClassificado(Classificado classificado) {
		this.classificado = classificado;
	}
	
}
