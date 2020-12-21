package co.com.coopeuch.rsadmontareas.model.services;

import java.util.List;

import co.com.coopeuch.rsadmontareas.model.entity.Tarea;



public interface ITareaService {
	
	public List<Tarea> findAll();
	
	public Tarea save(Tarea tarea);
	
	public Tarea findById(Long id);
	
	public void delete(Tarea tarea);

}
