package com.SantiagoMartinez.Gym.Model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@MappedSuperclass
	public abstract class Persona {
			private String nombre;
			private String apellido;
			private int dni;
			@DateTimeFormat(pattern = "yyyy-MM-dd")
			private LocalDate fechaNacimiento;
			private long telefono;
			private String direccion;
			
			public Persona() {		}
			
			
			public Persona(String nombre, String apellido, int dni, LocalDate fechaNacimiento, long telefono,
					String direccion) {			
				this.nombre = nombre;
				this.apellido = apellido;
				this.dni = dni;
				this.fechaNacimiento = fechaNacimiento;
				this.telefono = telefono;
				this.direccion = direccion;
			}

			public String getNombre() {
				return nombre;
			}

			public void setNombre(String nombre) {
				this.nombre = nombre;
			}

			public String getApellido() {
				return apellido;
			}

			public void setApellido(String apellido) {
				this.apellido = apellido;
			}

			public int getDni() {
				return dni;
			}

			public void setDni(int dni) {
				this.dni = dni;
			}

			public LocalDate getFechaNacimiento() {
				return fechaNacimiento;
			}

			public void setFechaNacimiento(LocalDate fechaNacimiento) {
				this.fechaNacimiento = fechaNacimiento;
			}

			public long getTelefono() {
				return telefono;
			}

			public void setTelefono(long telefono) {
				this.telefono = telefono;
			}

			public String getDireccion() {
				return direccion;
			}

			public void setDireccion(String direccion) {
				this.direccion = direccion;
			}
			
		
			
			
			
	}
	
	

