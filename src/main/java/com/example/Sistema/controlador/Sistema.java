package com.example.Sistema.controlador;

import com.example.Sistema.modelo.GetAndSet;
import com.example.Sistema.modelo.Modelos;
import com.example.Sistema.modelo.Usuario;
import com.example.Sistema.repositorio.SistemaRepositorio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller //Esto significa que esta clase es un Controlador
@RequestMapping(path = "/sistema") //Esto significa que la URL comienza con /sistema (después de la ruta de la aplicación)
public class Sistema {
    private String sql;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayList<Object> array = new ArrayList<Object>();
    @Autowired //Esto significa obtener el bean llamado (repositorio)
    //Que es generado automáticamente por Spring, lo usaremos para manejar los datos
    private SistemaRepositorio repositorio;

    public GetAndSet getAndSet;
    public Modelos modelos;

    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @GetMapping(path = "/editar/{id}")
    public @ResponseBody Optional<GetAndSet> 
    editar(@PathVariable int id) {
        return repositorio.findById(id);
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @PostMapping(path = "/agregar") //Asignar SOLAMENTE solicitudes POST
    public @ResponseBody ArrayList<Object>
    agregar(@RequestParam int id, @RequestParam String nombre, @RequestParam String marca, @RequestParam Double precio) {
        String pattern                      = "yy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat   = new SimpleDateFormat(pattern);
        String date                         = simpleDateFormat.format(new Date());

        GetAndSet n = new GetAndSet();
        n.setNombre(nombre);
        n.setMarca(marca);
        n.setPrecio(precio);
        n.setFecha(date);

        repositorio.save(n);
        return obtener();
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @GetMapping(path = "/obtener")
    public @ResponseBody ArrayList<Object> 
    obtener() {
        try {
            sql = "SELECT * FROM sistema_mysql LIMIT 5";
            
            ResultSet res = Usuario.getStatement().executeQuery(sql);

            Modelos.todo.clear();
            
            while(res.next()) {  
                getAndSet = new GetAndSet(
                    res.getInt("id"), 
                    res.getString("nombre"), 
                    res.getString("marca"), 
                    res.getDouble("precio"), 
                    res.getString("fecha")
                ); 

                Modelos.todo(getAndSet);
            }
            
            return Modelos.todo;
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @PutMapping(path = "/actualizar")
    public @ResponseBody ArrayList<Object>
    actualizar(@RequestParam int id, @RequestParam String nombre, @RequestParam String marca, @RequestParam Double precio) {
        String sql = "UPDATE sistema_mysql SET nombre='"+nombre+"', marca='"+marca+"', precio="+precio+" WHERE id="+id;
        
        Usuario.executeUpdate(sql);
       
        if(Usuario.executeUpdate(sql) == 1) {
            System.out.println("Actualización exitosa.");
        } else {
            System.out.println("Error de actualización.");
        }
        
        return obtener();
    } 
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @DeleteMapping(path = "/eliminar") 
    public @ResponseBody ArrayList<Object>
    eliminar(@RequestParam int id) {
        repositorio.deleteById(id);
        return obtener();
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @GetMapping(path = "/obtener/{s}")
    public @ResponseBody ArrayList<Object> 
    buscar(@PathVariable String s) {
        try {
            sql = "SELECT * FROM sistema_mysql WHERE nombre LIKE '%"+s+"%' OR marca LIKE '%"+s+"%' ORDER BY id DESC LIMIT 10";
            
            ResultSet res = Usuario.getStatement().executeQuery(sql);

            Modelos.todo.clear();
            
            while(res.next()) {  
                getAndSet = new GetAndSet(
                    res.getInt("id"), 
                    res.getString("nombre"), 
                    res.getString("marca"), 
                    res.getDouble("precio"), 
                    res.getString("fecha")
                ); 

                Modelos.todo(getAndSet);
            }
            
            return Modelos.todo;
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @GetMapping(path = "/limite")
    public @ResponseBody int 
    limite() {
        return (int) repositorio.count();
    }
    @CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
    @GetMapping(path = "/paginacion/{id}")
    public @ResponseBody ArrayList<Object> 
    paginacion(@PathVariable int id) {
        try {
            sql = "SELECT * FROM sistema_mysql ORDER BY id DESC LIMIT 5 OFFSET " + id;
            
            ResultSet res = Usuario.getStatement().executeQuery(sql);

            Modelos.todo.clear();
            
            while(res.next()) {  
                getAndSet = new GetAndSet(
                    res.getInt("id"), 
                    res.getString("nombre"), 
                    res.getString("marca"), 
                    res.getDouble("precio"), 
                    res.getString("fecha")
                ); 

                Modelos.todo(getAndSet);
            }
            
            return Modelos.todo;
        } catch (SQLException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
/* Con parametros de clave y valor (esta sin usar) 
----------------------------------------------------------------------------*/
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/obten")
    public @ResponseBody String 
    obten(@RequestParam(value = "name", defaultValue = "1") String name) {
        System.out.println("Con el metodo 'GET' -- " + name + " --");
 
        return String.format("Hello %s!", name);
    }
}
