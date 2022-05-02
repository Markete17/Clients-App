package com.clients.restapi.models.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@NotEmpty(message = "is required")
	@Size(min = 4, max = 12,message =  "First Name must have at least 4 characters")
	private String firstName;
	
	@NotEmpty(message = "is required")
	private String lastName;
	
	@Email(message = "has an invalid format")
	@NotEmpty(message = "is required")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotNull(message = "must not be null")
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String photo;
	
	//La carga LAZY, cuando se llama al Get de la region es cuando va a cargar
	
	// @JoinColumn(name = "region_id") por defecto, 
	//con JoinColumn pilla el nombre del atributo(region) y su clave primaria (id) =>region_id por lo que se puede omitir
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //Si la carga es lazy hay que ignorar estos atributos
	@NotNull
	private Region region;
	
	/*
	@PrePersist //Antes de crear el objeto, el create at sera la fecha de hoy
	public void prePersist() {
		createAt = new Date();
	}*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}

