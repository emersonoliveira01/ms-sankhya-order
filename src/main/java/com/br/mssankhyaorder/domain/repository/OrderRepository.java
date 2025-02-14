package com.br.mssankhyaorder.domain.repository;

import com.br.mssankhyaorder.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
