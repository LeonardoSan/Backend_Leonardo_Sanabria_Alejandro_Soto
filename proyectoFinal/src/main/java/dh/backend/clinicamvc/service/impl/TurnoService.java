package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.controller.PacienteController;
import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.repository.ITurnoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import dh.backend.clinicamvc.service.ITurnoService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private static final Logger logger = LoggerFactory.getLogger(TurnoService.class);

    //-----------------Instancias----------------------
    private ITurnoRepository iTurnoRepository;
    private IPacienteRepository iPacienteRepository;
    private IOdontologoRepository iOdontologoRepository;
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository iTurnoRepository, IPacienteRepository iPacienteRepository, IOdontologoRepository iOdontologoRepository, ModelMapper modelMapper) {
        this.iTurnoRepository = iTurnoRepository;
        this.iPacienteRepository = iPacienteRepository;
        this.iOdontologoRepository = iOdontologoRepository;
        this.modelMapper = modelMapper;
    }

    //---------------------------------------------------------------

    //------------------------------Metodos--------------------------

    //POST
    @Override
    public TurnoResponseDto registrar(TurnoRequestDto turnoRequestDto){
        logger.info("*************************");
        logger.info("* Agregando Nuevo Turno *");
        logger.info("*************************");
        Optional<Paciente> paciente = iPacienteRepository.findById(turnoRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(turnoRequestDto.getOdontologoId());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(paciente.isPresent() && odontologo.isPresent()){
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = iTurnoRepository.save(turnoARegistrar);

            turnoADevolver = mapToResponseDto(turnoGuardado);

        }


        return turnoADevolver;

    }

    @Override
    public void registrarTurno(TurnoRequestDto turnoRequestDto) throws BadRequestException {
        logger.info("*************************");
        logger.info("* Agregando Nuevo Turno *");
        logger.info("*************************");
        Optional<Paciente> paciente = iPacienteRepository.findById(turnoRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(turnoRequestDto.getOdontologoId());
        Turno turnoARegistrar = new Turno();
        if(paciente.isPresent() && odontologo.isPresent()){
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            iTurnoRepository.save(turnoARegistrar);
        }
        else
            throw new BadRequestException("{\"message\": \"Odontologo o paciente no encontrado\"}");

    }

    //GET
    @Override
    public TurnoResponseDto buscarPorId(Integer id) {
        logger.info("************************************");
        logger.info("* Mostrando registro del ID        *");
        logger.info("************************************");
        Optional<Turno> turnoOptional = iTurnoRepository.findById(id);
        if(turnoOptional.isPresent()){
            Turno turnoEncontrado = turnoOptional.get();
            TurnoResponseDto turnoADevolver = mapToResponseDto(turnoEncontrado);
            return turnoADevolver;
        }
        return null;
    }

    //GET
    @Override
    public List<TurnoResponseDto> buscarTodos() {
        logger.info("*********************************");
        logger.info("* Mostrando todos los registros *");
        logger.info("*********************************");
        List<Turno> turnos = iTurnoRepository.findAll();
        List<TurnoResponseDto> turnosADevolver = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for(Turno turno: turnos){
            turnoAuxiliar = mapToResponseDto(turno);
            turnosADevolver.add(turnoAuxiliar);
        }
        return turnosADevolver;
    }

    //PUT
    @Override
    public void actualizarTurno(Integer id, TurnoRequestDto turnoRequestDto) {
        logger.info("***********************");
        logger.info("* Actualizando Turno  *");
        logger.info("***********************");
        Optional<Paciente> paciente = iPacienteRepository.findById(turnoRequestDto.getPacienteId());
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(turnoRequestDto.getOdontologoId());
        Optional<Turno> turno = iTurnoRepository.findById(id);
        Turno turnoAModificar = new Turno();
        if(paciente.isPresent() && odontologo.isPresent() && turno.isPresent()){
            turnoAModificar.setId(id);
            turnoAModificar.setOdontologo(odontologo.get());
            turnoAModificar.setPaciente(paciente.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            iTurnoRepository.save(turnoAModificar);
        }
    }

    //DELETE
    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        logger.info("************************************");
        logger.info("* Eliminando registro del ID        *");
        logger.info("************************************");
        TurnoResponseDto turnoResponseDto = buscarPorId(id);
        if(turnoResponseDto != null)
            iTurnoRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("{\"message\": \"Turno no encontrado\"}");
    }

    private TurnoResponseDto mapToResponseDto(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return  turnoResponseDto;
    }

    //---------------------------------------------------------------------

    //------------------------Metodos HQL---------------------------------

    //GET
    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        logger.info("******************************************************");
        logger.info("* Mostrando registros de fechas seleccionadas        *");
        logger.info("******************************************************");
        List<Turno> listadoTurnos = iTurnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDto> listadoARetornar = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: listadoTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }
        return listadoARetornar;
    }

    //GET
    @Override
    public List<TurnoResponseDto> buscarTurnoPosterior(LocalDate startDate) {
        logger.info("******************************************************");
        logger.info("* Mostrando registros posterior a fecha seleccionada *");
        logger.info("******************************************************");
        List<Turno> listaDeTurnos = iTurnoRepository.buscarTurnoPosterior(startDate);
        List<TurnoResponseDto> listaARetornar = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno turno: listaDeTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listaARetornar.add(turnoAuxiliar);
        }
        return listaARetornar;
    }
}
