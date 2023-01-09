package com.curso.ecommerce.controller;

import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.model.Usuario;
import com.curso.ecommerce.repository.IOrdenRepository;
import com.curso.ecommerce.service.IDetalleOrdenService;
import com.curso.ecommerce.service.IOrdenService;
import com.curso.ecommerce.service.IUsuarioService;
import com.curso.ecommerce.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @Autowired
    private IDetalleOrdenService detalleOrdenService;


    //Para almacenar los detales de la orden
    private List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
    //Para almacenar los datos de la orden
    private Orden orden = new Orden();


    @GetMapping("")
    public String home(Model model, HttpSession session){
        log.info("Sesion del usuario: {}", session.getAttribute("idUsuario"));
        model.addAttribute("productos",productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productoHome/{id}")
    public String productoHome( @PathVariable Integer id, Model model){
        log.info("Id producto enviado como parametro {}", id);

        Producto producto = new Producto();
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();

        model.addAttribute("producto",producto);

        return "usuario/productoHome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}",optionalProducto.get());
        log.info("Cantidad {}", cantidad);

        producto = optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        boolean ingresado = detalles.stream().anyMatch( p -> p.getProducto().getId() == id);
        if (!ingresado){
            detalles.add(detalleOrden);
        }
        else{
            for( DetalleOrden detalleOrdenOld:detalles){
                if(detalleOrdenOld.getProducto().getId() == id)
                {
                    detalleOrdenOld.setCantidad( detalleOrdenOld.getCantidad() + cantidad );
                    detalleOrdenOld.setTotal( detalleOrdenOld.getPrecio() * detalleOrdenOld.getCantidad() );

                }
            }

            //detalleOrden.getProducto()
        }


        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);

        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);

        return "usuario/carrito";
    }

    //Quitar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model){
        //Lista de productos
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();

        for( DetalleOrden detalleOrden:detalles){
            if(detalleOrden.getProducto().getId() != id)
            {
                ordenesNueva.add(detalleOrden);
            }
        }
        detalles = ordenesNueva;

        double sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
        return "usuario/carrito";
    }

    @GetMapping("order")
    public String order(Model model, HttpSession session){

        Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString() );
        Usuario usuario = usuarioService.findById( idUsuario ).get();

        model.addAttribute("cart",detalles);
        model.addAttribute("orden",orden);
        model.addAttribute("usuario",usuario);
        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder( HttpSession session ){

        Date fechaCreacion = new Date();
        orden.setFechaCreacion( fechaCreacion );
        orden.setNumero( ordenService.generarNumeroOrden() );

        //usuario
        Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString() );
        Usuario usuario = usuarioService.findById(idUsuario).get();

        orden.setUsuario( usuario );
        ordenService.save(orden);

        //Guardar detalles

        for ( DetalleOrden dt:detalles ){
            dt.setOrden(orden);
            detalleOrdenService.save( dt );
        }
        //Limpiamos los valores de nuestra orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("Nombre del producto: {}",nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().toUpperCase().contains(nombre.toUpperCase())  ).collect(Collectors.toList());
        model.addAttribute("productos",productos);
        return "usuario/home";

    }
}
