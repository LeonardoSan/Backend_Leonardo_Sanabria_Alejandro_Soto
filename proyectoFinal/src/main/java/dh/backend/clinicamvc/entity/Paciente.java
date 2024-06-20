package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apellido;
    private String nombre;
    private String dni;
    private LocalDate fechaIngreso;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente",  cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnoSet = new HashSet<>();


    //------------NOTAS-----------
    /*
        @Entity: Indica que la clase es una entidad JPA
        @Table(name = "Pacientes"): Nombre de la tablas
        @Id: Indica que el campo ID es la clave primaria de la entidad.
        @GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor de ID se generará automáticamente por la BD.

        @OneToOne(cascade = CascadeType.ALL): Indica que existe una relación uno a uno entre esta entidad y la entidad Domicilio.
        @JoinColumn(name = "domicilio_id"): Especifica que la columna domicilio_id en la tabla de la base de datos se utiliza como clave foránea para esta relación.

        @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL):
                Indica que existe una relación uno a muchos entre esta entidad y la entidad Turno.
                El atributo mappedBy = "paciente" indica que la relación es bidireccional y que la propiedad paciente en la entidad Turno es la que gestiona la relación

    */


}
