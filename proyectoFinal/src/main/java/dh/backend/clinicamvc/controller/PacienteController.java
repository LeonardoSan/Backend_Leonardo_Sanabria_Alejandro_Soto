package dh.backend.clinicamvc.controller;


import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/paciente")
public class  PacienteController {

    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);
    //------------------Instancia de PacienteService-----------------------------
    public IPacienteService pacienteService;
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }
    //---------------------------------------------------------------------------


    //---------------------------------ENDPOINTS CRUD-----------------------------
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteARetornar = pacienteService.registrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteARetornar);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> mostrarTodosLosPacientes(){
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        pacienteService.actualizarPaciente(paciente);
        return  ResponseEntity.ok("paciente actualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  borrarPaciente(@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("paciente eliminado");
    }

    //-------------------------------------------------------------------------


    //--------------------OTROS ENDPOINTS------------------------------------------

    //BUSCAR POR ID
    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //BUSCAR POR DNI
    @GetMapping("/buscarDNI/{DNI}")
    public ResponseEntity<Paciente> buscarPacientePorDni(@PathVariable String DNI){
        return ResponseEntity.ok(pacienteService.BusquedaPorDni(DNI));
    }


}
