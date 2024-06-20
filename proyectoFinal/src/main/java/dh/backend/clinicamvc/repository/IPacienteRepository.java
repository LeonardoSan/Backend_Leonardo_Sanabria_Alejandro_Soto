package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("Select p from Paciente p where p.dni =?1 ")
    Paciente BusquedaPorDni(String DNI);

    /*@Query("Select p from Paciente p where p.provincia =?1")
    List<Paciente> listaBusquedaPorProvincia(String provincia);*/
}
