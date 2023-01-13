package com.curso.ecommerce.service;

import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdenServiceImpl implements IOrdenService{
    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    // 0000010
    @Override
    public String generarNumeroOrden(){
        int numero = 0;
        String numeroConcatenado = "";
        List<Orden> ordenes = findAll();

        List<Integer> numeros = new ArrayList<Integer>();

        //recorre toda la lista de ordenes y por cada una carga en numeros
        ordenes.stream().forEach(o -> numeros.add( Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()){
            numero = 1;
        }
        else{
            numero = numeros.stream().max( Integer::compare ).get();
            numero++;
        }
        //Codigo propio diferente a lo visto en video 32!
        numeroConcatenado = "00000000000000000000" + String.valueOf(numero);
        numeroConcatenado = numeroConcatenado.substring(numeroConcatenado.length() - 10);

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }
}
