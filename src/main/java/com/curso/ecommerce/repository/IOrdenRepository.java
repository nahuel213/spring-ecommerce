package com.curso.ecommerce.repository;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden,Integer> {
    List<Orden> findByUsuario( Usuario usuario );


}
//PASOS PARA AGREGAR METODOS DE DAL
//1: Agregar el método en la interfaz repositorio (Folder: repository)
//      En estos métodos si usas findBy(Un campo de la Clase implementada) no tenes que implementar nada, es automático
//NOTA: SI EMPEZAS POR ACA Y EL METODO YA ESTA DEFINIDO EN LA SUPERCLASE, SEGUI CON LO SIGUIENTE

//2: Modificar la Interfaz service asociada a la interfaz repositorio (Folder: service)
//3: Modificar la implementación de la Interfaz service (Una clase que termina en Impl) (Folder: service)
//      Esta clase llama al repositorio que modificamos en (1)