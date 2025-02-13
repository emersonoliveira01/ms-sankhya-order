package com.br.mssankhyaorder.domain.repository;

import com.br.mssankhyaorder.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
