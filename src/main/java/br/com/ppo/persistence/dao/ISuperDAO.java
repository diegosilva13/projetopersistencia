package br.com.ppo.persistence.dao;

import java.util.List;

import br.com.ppo.persistence.exception.PersistenceException;

public interface ISuperDAO{
	
	public Object save(Object object) throws PersistenceException;
	public Object update(Object object) throws PersistenceException;
	public Boolean remove(Object object)throws PersistenceException;
	public Boolean removeAll(Object object)throws PersistenceException;
	public Object findById(Object object, Object id)throws PersistenceException;
	@SuppressWarnings("rawtypes")
	public List findAll(Class<?> clazz) throws PersistenceException;
	}
