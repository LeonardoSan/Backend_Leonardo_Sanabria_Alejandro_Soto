package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.controller.PacienteController;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.thymeleaf.engine.IThrottledTemplateWriterControl;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private static final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    //---------------------Instancia de pacienteRepository-------------------
    private IPacienteRepository iPacienteRepository;
    public PacienteService(IPacienteRepository iPacienteRepository) {
        this.iPacienteRepository = iPacienteRepository;
    }
    //-----------------------------------------------------------------------



    //---------------------Metodos-------------------------------------------

    //CREATE
    public Paciente registrarPaciente(Paciente paciente){
        logger.info("*************************");
        logger.info("* Registrando Paciente  *");
        logger.info("*************************");
        return iPacienteRepository.save(paciente);}

    //READ
    public Optional<Paciente> buscarPorId(Integer id){
        logger.info("*****************************");
        logger.info("* Buscando Paciente por ID  *");
        logger.info("*****************************");
        return iPacienteRepository.findById(id);}

    public List<Paciente> buscarTodos(){
        logger.info("***********************************");
        logger.info("* Buscando rgistros de Pacientes  *");
        logger.info("***********************************");
        return iPacienteRepository.findAll();}

    //PUT
    @Override
    public void actualizarPaciente(Paciente paciente) {
        logger.info("***************************");
        logger.info("* Actualizando Paciente   *");
        logger.info("***************************");
        iPacienteRepository.save(paciente);}

    //DELETE
    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        logger.info("*************************");
        logger.info("* Eliminando Paciente   *");
        logger.info("*************************");
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if( pacienteOptional.isPresent())
            iPacienteRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
    }
    //----------------------------------------------------------------------


    //--------------------------Metodos HQL---------------------------------
    @Override
    public Paciente BusquedaPorDni(String DNI) {
        logger.info("*****************************");
        logger.info("* Buscando Paciente por DNI *");
        logger.info("*****************************");
        return iPacienteRepository.BusquedaPorDni(DNI);
    }

    //----------------------------------------------------------------------


}
