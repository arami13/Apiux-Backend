package co.com.coopeuch.rsadmontareas.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.coopeuch.rsadmontareas.model.entity.Tarea;
import co.com.coopeuch.rsadmontareas.model.services.ITareaService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class TareaRestController {

	@Autowired
	private ITareaService tareaService;

	@GetMapping("/tareas")
	public List<Tarea> index() {
		return tareaService.findAll();
	}

	@GetMapping("/tareas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Tarea tarea = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tarea = tareaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Tarea>(tarea, HttpStatus.OK);
	}

	@PostMapping("/tareas")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Tarea tarea) {

		Tarea tareaNueva = null;
		Map<String, Object> response = new HashMap<>();

		try {
			tareaNueva = tareaService.save(tarea);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La tarea ha sido creado con éxito!");
		response.put("tarea", tareaNueva);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@PutMapping("/tareas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Tarea tarea, @PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		Tarea tareaUpdated = null;
		Tarea currentTarea = this.tareaService.findById(id);

		if (currentTarea == null) {
			response.put("mensaje", "Error: no se pudo editar, la tarea ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentTarea.setDescripcion(tarea.getDescripcion());
			currentTarea.setVigente(tarea.getVigente());
			currentTarea.setFechaCreacion(tarea.getFechaCreacion());

			tareaUpdated = tareaService.save(currentTarea);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la tarea en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La tarea ha sido actualizado con éxito!");
		response.put("tarea", tareaUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/tareas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
				
		try {
			Tarea currentCliente = this.tareaService.findById(id);
			this.tareaService.delete(currentCliente);	
		}catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la tarea de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Tarea eliminada con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
