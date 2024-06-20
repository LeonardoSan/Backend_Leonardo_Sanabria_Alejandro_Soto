package dh.backend.clinicamvc.service.impl;



import dh.backend.clinicamvc.controller.OdontologoController;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoService.class);

    //--------------Instancia de IOdontologoRepository-----
    private IOdontologoRepository iOdontologoRepository;
    public OdontologoService(IOdontologoRepository iOdontologoRepository) {
        this.iOdontologoRepository = iOdontologoRepository;
    }
    //------------------------------------------------------


    //-----------Metodos CRUD------------------------------------
    public Odontologo registrarOdontologo(Odontologo odontologo){
        logger.info("***************************");
        logger.info("* Registrando Odontologo  *");
        logger.info("***************************");
        return iOdontologoRepository.save(odontologo);}

    public Optional<Odontologo> buscarPorId(Integer id){
        logger.info("*******************************");
        logger.info("* Buscando Odontologo por ID  *");
        logger.info("*******************************");
        return iOdontologoRepository.findById(id);}

    public List<Odontologo> buscarTodos(){
        logger.info("*************************************");
        logger.info("* Buscando Registros de odontologo  *");
        logger.info("*************************************");
        return iOdontologoRepository.findAll();}
    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        logger.info("****************************");
        logger.info("* Actualizando Odontologo  *");
        logger.info("****************************");
        iOdontologoRepository.save(odontologo);}
    @Override
    public void eliminarOdontologo(Integer id){
        logger.info("**************************");
        logger.info("* Eliminando Odontologo  *");
        logger.info("**************************");
        iOdontologoRepository.deleteById(id);}

    //------------------------------------------------------

    //------------------METODOS HQL-------------------------
    @Override
    public List<Odontologo> listaBusquedaPorApellido(String apellido) {
        logger.info("*************************************");
        logger.info("* Buscando Odontologo por apellido  *");
        logger.info("*************************************");
        return iOdontologoRepository.listaBusquedaPorApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombreLike(String nombre) {
        logger.info("**************************************");
        logger.info("* Registrando Odontologo por nombre  *");
        logger.info("**************************************");
        return iOdontologoRepository.buscarPorNombreLike(nombre);
    }

    //--------------------------------------------------
}




