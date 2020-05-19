package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Estudiante;

@Repository
public class EstudianteDAOlmpl implements EstudianteDAO {
	
	@PersistenceContext(unitName="demo")
	private EntityManager entityManager;
	
	@Override
	public List<Estudiante> findAll() throws DataAccessException{
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.estudiante");
		
		Query query = entityManager.createNativeQuery(sb.toString(),Estudiante.class);
		List<Estudiante>result=query.getResultList();
		return result;
	}
	

	@Override
	public Estudiante findOne(Integer code) throws DataAccessException {
		Estudiante estudiante = entityManager.find(Estudiante.class, code);
		return estudiante;
	}


	@Override
	public void save(Estudiante estudiante) throws DataAccessException {
		try {
			if(estudiante.getCodigoEstudiante() == null) {
				entityManager.persist(estudiante);
			}
			else {
				entityManager.merge(estudiante);
				entityManager.flush();
			}
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer codigoEstudiante) throws DataAccessException {
		Estudiante estudiante = entityManager.find(Estudiante.class, codigoEstudiante);
		entityManager.remove(estudiante);
		
	}
	
	
	
	
}
