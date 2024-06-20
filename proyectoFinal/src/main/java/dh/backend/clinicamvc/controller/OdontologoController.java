package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    //-----------------Instancia de Odontologo service--------------
    private OdontologoService odontologoService;
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    //--------------------------------------------------------------


    //----------------Metodos HTTP----------------------------------
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.registrarOdontologo(odontologo));
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo>  buscarOdontologoPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            Odontologo odontologoARetornar = odontologo.get();
            return ResponseEntity.ok(odontologoARetornar);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }

    @PutMapping
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoOptional = odontologoService.buscarPorId(odontologo.getID());
        if(odontologoOptional.isPresent()){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("{\"message\": \"odontologo modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("{\"message\": \"odontologo eliminado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //---------------METODOS HQL---------------------------

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarOdontologoPorApellido(@PathVariable String apellido){
        List<Odontologo> listOdontologos = odontologoService.listaBusquedaPorApellido(apellido);
        if (listOdontologos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else{
            return ResponseEntity.ok(listOdontologos);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarOdontologoPorNombreLike(@PathVariable String nombre){
        List<Odontologo> listOdontologos = odontologoService.buscarPorNombreLike(nombre);
        if (listOdontologos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else{
            return ResponseEntity.ok(listOdontologos);
        }
    }


}
