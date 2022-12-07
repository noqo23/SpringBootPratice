package com.ti.ty.repository;

import com.ti.ty.model.Board;
import com.ti.ty.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
