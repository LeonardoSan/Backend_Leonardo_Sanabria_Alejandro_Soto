package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Odontologo;
import org.hibernate.annotations.processing.HQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Integer> {

    //-----------------------HQL QUERYS-----------------------
    @Query("Select o from Odontologo o where LOWER(o.apellido) = LOWER(:apellido)")
    List<Odontologo> listaBusquedaPorApellido(String apellido);
    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Odontologo> buscarPorNombreLike(@Param("nombre") String nombre);
    //---------------------------------------------------------

}
