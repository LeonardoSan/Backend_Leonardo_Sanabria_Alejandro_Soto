package dh.backend.clinicamvc.service;


import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.service.impl.OdontologoService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class OdontologoServiceTest {
    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    @Autowired
    private OdontologoService odontologoService;
    private Odontologo odontologo;
    @BeforeEach
    void setUp(){
        odontologo = new Odontologo();
        odontologo.setNombre("Menganito");
        odontologo.setApellido("Cosme");
        odontologo.setNumMatricula(464646);

    }

    @Test
    @DisplayName("Testear que un paciente fue guardado")
    void testPacienteGuardado(){
        Odontologo probarOdontologo = odontologoService.registrarOdontologo(odontologo);

        assertNotNull(probarOdontologo);
    }

    @Test
    @DisplayName("Testear busqueda paciente por id")
    void testPacientePorId(){
        Integer ID = 1;
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(ID);
        Odontologo odontologo1 =odontologoEncontrado.get();

        assertEquals(ID, odontologo1.getID());
    }

    @Test
    @DisplayName("Testear busqueda todos los pacientes")
    void testBusquedaTodos() {

        odontologoService.registrarOdontologo(odontologo);

        List<Odontologo> odontologos = odontologoService.buscarTodos();

        assertEquals(2, odontologos.size());

    }

}
