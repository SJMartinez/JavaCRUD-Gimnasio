package com.SantiagoMartinez.Gym.Model;
import com.SantiagoMartinez.Gym.Services.ServiciosMiembro;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
public class Miembro extends Persona implements ServiciosMiembro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMiembro;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private LocalDate fechaIngreso;
	@Transient

	private Cuota ultimaCuota;
	@Transient
	private boolean moroso;

	public Miembro() {
	}

	public Miembro(String nombre, String apellido, int dni, LocalDate fechaNacimiento, long telefono, String direccion,
			int idMiembro, LocalDate fechaIngreso) {
		super(nombre, apellido, dni, fechaNacimiento, telefono, direccion);
		this.idMiembro = idMiembro;
		this.fechaIngreso = fechaIngreso;
	}

	@OneToMany(mappedBy = "miembro", cascade = { CascadeType.ALL })
	private List<Cuota> listaIntermediaCuota;

	public int getIdMiembro() {
		return idMiembro;
	}

	public void setIdMiembro(int idMiembro) {
		this.idMiembro = idMiembro;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public List<Cuota> getListaIntermediaCuota() {
		return listaIntermediaCuota;
	}

	public void setListaIntermediaCuota(List<Cuota> listaIntermediaCuota) {
		this.listaIntermediaCuota = listaIntermediaCuota;
	}

	public String getUltimaCuotaS() {
		return ultimaCuota.getFechaPago().toString();
	}

	public Cuota getUltimaCuota() {
		return ultimaCuota;
	}

	public void setUltimaCuota(Cuota ultimaCuota) {
		this.ultimaCuota = ultimaCuota;
	}

	public boolean getMoroso() {
		return moroso;
	}

	@Override
	public void esMoroso(List<Cuota> cuotas) {

		Cuota cuotaMasReciente = new Cuota();
		for (Cuota cuota : cuotas) {
			if (cuota.getIdCuota() > cuotaMasReciente.getIdCuota()) {
				cuotaMasReciente = cuota;
			}
		}
		this.ultimaCuota = cuotaMasReciente;
		if (this.ultimaCuota.cuotaVencida()) {
			this.moroso = true;
		}

	}

}
