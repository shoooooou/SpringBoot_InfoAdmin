package com.example.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.MyData;

@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {
	Optional<MyData> findById(Long id);
}
