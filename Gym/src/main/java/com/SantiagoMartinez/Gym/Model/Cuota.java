package com.SantiagoMartinez.Gym.Model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;
import com.SantiagoMartinez.Gym.Services.ServiciosCuota;

@Entity
public class Cuota implements ServiciosCuota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuota;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private LocalDate fechaPago;

	public Cuota() {
	}

	public Cuota(int idCuota, LocalDate fechaPago) {
		this.idCuota = idCuota;
		this.fechaPago = fechaPago;
	}

	@Override
	public boolean cuotaVencida() {
		if (this.fechaPago == null) {
			return false;
		}
		boolean bool = false;
		LocalDate actual = LocalDate.now();
		LocalDate pago = this.fechaPago;
		pago = pago.plusDays(30);
		if (pago.compareTo(actual) < 1) {
			bool = true;
		}
		return bool;
	}

	@ManyToOne
	@JoinColumn(name = "idMiembro")
	private Miembro miembro;

	public int getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Miembro getMiembro() {
		return miembro;
	}

	public void setMiembro(Miembro miembro) {
		this.miembro = miembro;
	}

}
