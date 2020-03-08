package com.rdongol.restdemo.repository;

import org.springframework.data.repository.CrudRepository;
import com.rdongol.restdemo.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
