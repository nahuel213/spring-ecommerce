package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    //OJO EL IntelliJ te ayuda así:
    //Optional<Usuario> findUsuarioByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
