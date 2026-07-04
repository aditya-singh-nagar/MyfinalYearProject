package mpack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mpack.entities.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
	
	public MyUser findByEmail(String email);

}


