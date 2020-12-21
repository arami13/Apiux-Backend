package co.com.coopeuch.rsadmontareas.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.coopeuch.rsadmontareas.model.dao.ITareaDao;
import co.com.coopeuch.rsadmontareas.model.entity.Tarea;


@Service
public class TareaServiceImpl implements ITareaService{


	@Autowired
	private ITareaDao tareaDao;
	
	@Override
	public List<Tarea> findAll() {
		return (List<Tarea>) tareaDao.findAll();
	}

	@Override
	public Tarea save(Tarea tarea) {
		return tareaDao.save(tarea);
		
	}

	@Override
	public Tarea findById(Long id) {
		return tareaDao.findById(id).orElse(null);
	}

	@Override
	public void delete(Tarea tarea) {
		tareaDao.delete(tarea);		
	}
	
}
