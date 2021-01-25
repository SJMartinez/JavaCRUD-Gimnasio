package com.SantiagoMartinez.Gym.Controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.SantiagoMartinez.Gym.Model.Cuota;
import com.SantiagoMartinez.Gym.Model.Miembro;
import com.SantiagoMartinez.Gym.Repository.*;

@Controller
public class MainController {

	@Autowired
	DaoMiembro daoMiembro;

	@Autowired
	DaoCuota daoCuota;

	@GetMapping("principal")
	public ModelAndView principalGet(
			@RequestParam(value = "habilitarIngreso", required = false) boolean habilitarIngreso) {
		ModelAndView modelAndView = new ModelAndView();
		List<Miembro> miembros = new ArrayList<Miembro>();
		for (Miembro miembro : daoMiembro.findAll()) {
			miembro.esMoroso(daoCuota.findByMiembro(miembro));
			miembros.add(miembro);
		}

		boolean verdadero = true;
		modelAndView.addObject("miembroP", new Miembro());
		modelAndView.addObject("habilitarIngreso", habilitarIngreso);
		modelAndView.addObject("verdadero", verdadero);
		modelAndView.addObject("miembros", miembros);
		modelAndView.setViewName("principal");

		return modelAndView;
	}

	@GetMapping(value = "/borrar/{id}")
	public ModelAndView borrarMiembro(@PathVariable("id") int idMiembro) {
		Miembro miembro = daoMiembro.findByIdMiembro(idMiembro);
		ModelAndView modelAndView = new ModelAndView();
		daoMiembro.delete(miembro);
		List<Miembro> miembros = new ArrayList<Miembro>();
		for (Miembro miembroIterar : daoMiembro.findAll()) {
			miembroIterar.esMoroso(daoCuota.findByMiembro(miembroIterar));
			miembros.add(miembroIterar);
		}
		modelAndView.addObject("miembros", miembros);
		boolean verdadero = true;
		modelAndView.addObject("verdadero", verdadero);
		boolean habilitarIngreso = false;
		modelAndView.addObject("habilitarIngreso", habilitarIngreso);
		modelAndView.setViewName("principal");

		return modelAndView;
	}

	@PostMapping(value = "/principal")
	public ModelAndView ingresarMiembro(@ModelAttribute Miembro miembro) {

		ModelAndView modelAndView = new ModelAndView();
		Miembro miembroAux = new Miembro();
		Cuota cuota = new Cuota();
		daoMiembro.save(miembroAux);
		miembroAux.setNombre(miembro.getNombre());
		miembroAux.setApellido(miembro.getApellido());
		miembroAux.setDni(miembro.getDni());
		miembroAux.setDireccion(miembro.getDireccion());
		miembroAux.setFechaNacimiento(miembro.getFechaNacimiento());
		miembroAux.setTelefono(miembro.getTelefono());
		miembroAux.setFechaIngreso(miembro.getFechaIngreso());
		daoMiembro.save(miembroAux);
		daoCuota.save(cuota);
		cuota.setFechaPago(miembroAux.getFechaIngreso());
		cuota.setMiembro(miembroAux);
		daoCuota.save(cuota);
		List<Miembro> miembros = new ArrayList<Miembro>();
		for (Miembro miembroIterar : daoMiembro.findAll()) {
			miembroIterar.esMoroso(daoCuota.findByMiembro(miembroIterar));
			miembros.add(miembroIterar);
		}
		modelAndView.addObject("miembros", miembros);
		boolean verdadero = true;
		modelAndView.addObject("verdadero", verdadero);
		boolean habilitarIngreso = false;
		modelAndView.addObject("habilitarIngreso", habilitarIngreso);
		modelAndView.setViewName("principal");

		return modelAndView;
	}

	@GetMapping(value = "/modificar/{id}")
	public ModelAndView modificarMiembro(@PathVariable("id") int idMiembro) {

		ModelAndView modelAndView = new ModelAndView();
		Miembro miembroM = new Miembro();
		miembroM = daoMiembro.findByIdMiembro(idMiembro);
		List<Miembro> miembros = new ArrayList<Miembro>();
		for (Miembro miembroIterar : daoMiembro.findAll()) {
			miembroIterar.esMoroso(daoCuota.findByMiembro(miembroIterar));
			miembros.add(miembroIterar);
		}
		modelAndView.addObject("miembroM", miembroM);
		modelAndView.addObject("idModificacion", idMiembro);
		modelAndView.addObject("miembros", miembros);
		boolean verdadero = true;
		modelAndView.addObject("verdadero", verdadero);
		boolean habilitarIngreso = false;
		modelAndView.addObject("habilitarIngreso", habilitarIngreso);
		modelAndView.setViewName("principal");

		return modelAndView;
	}

	@PostMapping(value = "/modificar/{id}")
	public ModelAndView modificarMiembro(@PathVariable("id") int idMiembro, @ModelAttribute Miembro miembroM) {

		ModelAndView modelAndView = new ModelAndView();
		miembroM.setIdMiembro(idMiembro);
		daoMiembro.save(miembroM);
		List<Miembro> miembros = new ArrayList<Miembro>();
		for (Miembro miembroIterar : daoMiembro.findAll()) {
			miembroIterar.esMoroso(daoCuota.findByMiembro(miembroIterar));
			miembros.add(miembroIterar);
		}

		modelAndView.addObject("miembros", miembros);
		boolean verdadero = true;
		modelAndView.addObject("verdadero", verdadero);
		boolean habilitarIngreso = false;
		modelAndView.addObject("habilitarIngreso", habilitarIngreso);
		modelAndView.setViewName("principal");

		return modelAndView;
	}

	@GetMapping(value = "/cuotas/{id}")
	public ModelAndView cuotasMiembro(@PathVariable("id") int idMiembro) {

		ModelAndView modelAndView = new ModelAndView();
		Miembro miembro = new Miembro();
		Cuota cuotaVacia = new Cuota();
		miembro = daoMiembro.findByIdMiembro(idMiembro);
		List<Cuota> cuotas = new ArrayList<Cuota>();
		cuotas = daoCuota.findByMiembro(miembro);

		modelAndView.addObject("miembro", miembro);
		modelAndView.addObject("cuotas", cuotas);
		modelAndView.addObject("cuotaV", cuotaVacia);
		modelAndView.setViewName("cuotas");

		return modelAndView;
	}

	@PostMapping(value = "/cuotas/{id}")
	public ModelAndView ingresarCuota(@PathVariable("id") int idMiembro, @ModelAttribute Cuota cuota) {

		ModelAndView modelAndView = new ModelAndView();
		daoCuota.save(cuota);
		Miembro miembro = daoMiembro.findByIdMiembro(idMiembro);
		cuota.setMiembro(miembro);
		daoCuota.save(cuota);

		List<Cuota> cuotas = new ArrayList<Cuota>();
		cuotas = daoCuota.findByMiembro(miembro);
		Cuota cuotaVacia = new Cuota();
		modelAndView.addObject("miembro", miembro);
		modelAndView.addObject("cuotas", cuotas);
		modelAndView.addObject("cuotaV", cuotaVacia);
		modelAndView.setViewName("cuotas");

		return modelAndView;
	}

	@PostMapping(value = "/borrarCuota/{id}")
	public ModelAndView borrarCuota(@PathVariable("id") int idCuota) {

		ModelAndView modelAndView = new ModelAndView();
		Cuota cuota = daoCuota.findByIdCuota(idCuota);
		Miembro miembro = daoMiembro.findByIdMiembro(cuota.getMiembro().getIdMiembro());
		daoCuota.delete(cuota);

		List<Cuota> cuotas = new ArrayList<Cuota>();
		cuotas = daoCuota.findByMiembro(miembro);
		Cuota cuotaVacia = new Cuota();
		modelAndView.addObject("miembro", miembro);
		modelAndView.addObject("cuotas", cuotas);
		modelAndView.addObject("cuotaV", cuotaVacia);
		modelAndView.setViewName("cuotas");

		return modelAndView;
	}

}
