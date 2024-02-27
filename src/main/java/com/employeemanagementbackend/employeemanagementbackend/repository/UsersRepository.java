package com.employeemanagementbackend.employeemanagementbackend.repository;

import com.employeemanagementbackend.employeemanagementbackend.model.usersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<usersModel,Long> {
}
