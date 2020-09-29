package com.neifi.showband.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neifi.showband.user.User;

public abstract class BaseRepoService<T, ID, R extends JpaRepository<T, ID>> {
	@Autowired
	protected R repository;
	
	public T save(T t) {
		return repository.save(t);
		
	}
	
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}
	
	
	public List<T> findAll() {
		return repository.findAll();
	}
	

	public List<T> findByExample(Example<T> example) {
		return repository.findAll(example);
	}

	
	public T update(T t) {
		return repository.save(t);
	}
	
	public void delete(T t) {
		repository.delete(t);
	}
	
	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	
}
