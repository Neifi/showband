package com.neifi.showband.user.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neifi.showband.services.BaseRepoService;
import com.neifi.showband.user.User;
import com.neifi.showband.user.UserRepository;
import com.neifi.showband.user.exceptions.UserNotFoundException;

@Service
public class UserService extends BaseRepoService<User,Long,UserRepository>{
	@Autowired UserRepository repository;
	
	public Optional<User> findByUsername(String username){
		
		return repository.findByUsername(username);
	}
	
	@Override
	public User save(User t) {
		// TODO Auto-generated method stub
		return super.save(t);
	}

	@Override
	public Optional<User> findById(Long id) {
		 return Optional.ofNullable(super.findById(id).orElseThrow(UserNotFoundException::new));
	}

	@Override
	public List<User> findAll() {
		
		return super.findAll().isEmpty()?null:super.findAll();
	}

	@Override
	public User update(User t) {
		
		return super.update(t);
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		super.deleteById(id);
	}
	
}
