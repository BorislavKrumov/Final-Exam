package com.academy.supermarket.repository;

import com.academy.supermarket.model.entity.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket,String> {
}
