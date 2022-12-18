package com.curso.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.curso.ecommerce.model.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
