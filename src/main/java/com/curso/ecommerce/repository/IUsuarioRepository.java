package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
