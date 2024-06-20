package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.ITurnoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {



    //------------------------Instancia de turnoService--------------------------
    private ITurnoService turnoService;
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }
    //--------------------------------------------------------------------------

    //-----------------------Metodos HTTP---------------------------------------
    @PostMapping
    public ResponseEntity<String> agregarTurno(@RequestBody TurnoRequestDto turno) throws BadRequestException, ResourceNotFoundException {
        turnoService.registrarTurno(turno);
        return  ResponseEntity.ok("{\"message\": \"Turno registrado con exito\"}");
    }
    @GetMapping
    public ResponseEntity<List<TurnoResponseDto>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Integer id, @RequestBody TurnoRequestDto turno){
        turnoService.actualizarTurno(id, turno);
        return ResponseEntity.ok("Turno modificado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id) throws ResourceNotFoundException {
           turnoService.eliminarTurno(id);
           return  ResponseEntity.ok("{\"message\": \"Turno no encontrado\"}");
    }

    //-----------------------------------------------------------------

    //---------------------Metodos HQL---------------------------------
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @GetMapping("/fechas/EntreFechas")
    public ResponseEntity<List<TurnoResponseDto>> buscarEntreFechas(@RequestParam String inicio, @RequestParam String fin){
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
        LocalDate fechaFinal = LocalDate.parse(fin, formatter);
        return ResponseEntity.ok(turnoService.buscarTurnoEntreFechas(fechaInicio, fechaFinal));
    }
    @GetMapping("/fechas/Posterior")
    public ResponseEntity<List<TurnoResponseDto>> buscarFechasPosterior(@RequestParam String inicio){
        LocalDate fechaInicio = LocalDate.parse(inicio, formatter);
        return ResponseEntity.ok(turnoService.buscarTurnoPosterior(fechaInicio));
    }



}
