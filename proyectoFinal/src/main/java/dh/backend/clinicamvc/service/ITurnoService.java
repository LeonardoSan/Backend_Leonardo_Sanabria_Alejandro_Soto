package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto ) throws BadRequestException;

    TurnoResponseDto buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();
    void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto);
    void eliminarTurno(Integer id) throws ResourceNotFoundException;

    void registrarTurno(TurnoRequestDto turnoRequestDto) throws ResourceNotFoundException, BadRequestException;
    List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
    List<TurnoResponseDto> buscarTurnoPosterior(LocalDate startDate);


}
