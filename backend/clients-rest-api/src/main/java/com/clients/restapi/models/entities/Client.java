package com.clients.restapi.models.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Client implements Serializable {//Con Serializable permite convertir Java a JSON y almacenarlo en session http

	private static final long serialVersionUID = 1L;
		
	public Client(Long id,
			@NotEmpty(message = "is required") @Size(min = 4, max = 12, message = "First Name must have at least 4 characters") String firstName,
			@NotEmpty(message = "is required") String lastName,
			@Email(message = "has an invalid format") @NotEmpty(message = "is required") String email,
			@NotNull(message = "must not be null") Date createAt, String photo, @NotNull Region region,
			List<Invoice> invoices) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.createAt = createAt;
		this.photo = photo;
		this.region = region;
		this.invoices = invoices;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Incremental
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = {"client","hibernateLazyInitializer","handler"}, allowSetters = true)
	
	private List<Invoice> invoices;
	
	/*
	@PrePersist //Antes de crear el objeto, el create at sera la fecha de hoy
	public void prePersist() {
		createAt = new Date();
	}*/
	
	public Client() {
		this.invoices = new ArrayList<>();
	}
	
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

}

