package com.SantiagoMartinez.Gym.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.SantiagoMartinez.Gym.Model.Miembro;

public interface DaoMiembro extends CrudRepository <Miembro, Long> {

	Miembro findByIdMiembro(int idMiembro);
}



