package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById( id );
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save( producto );
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById( id );
    }
}
