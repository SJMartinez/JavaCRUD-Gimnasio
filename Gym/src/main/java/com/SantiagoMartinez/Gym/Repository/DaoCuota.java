package com.SantiagoMartinez.Gym.Repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.SantiagoMartinez.Gym.Model.Cuota;
import com.SantiagoMartinez.Gym.Model.Miembro;

public interface DaoCuota extends CrudRepository <Cuota, Long>{
	List<Cuota> findByMiembro(Miembro miembro);
	
	Cuota findByIdCuota(int idCuota);
	

}

