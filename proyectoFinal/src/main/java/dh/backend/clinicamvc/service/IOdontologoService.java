package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    //--------------METODOS------------------------------
    Odontologo registrarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(Integer ID);
    List<Odontologo> buscarTodos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);
    //---------------------------------------------------


    //------------------METODOS HQL---------------------
    List<Odontologo> listaBusquedaPorApellido(String apellido);
    List<Odontologo> buscarPorNombreLike(String nombre);
    //--------------------------------------------------

}
