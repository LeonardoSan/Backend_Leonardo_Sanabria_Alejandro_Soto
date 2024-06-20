package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String nombre;
    private String apellido;
    private int numMatricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Turno> turnoSet = new HashSet<>();


    //------------NOTAS-----------
    /*
        @Entity: Indica que la clase es una entidad JPA
        @Table(name = "Odontologos"): Nombre de la tablas
        @Id: Indica que el campo ID es la clave primaria de la entidad.
        @GeneratedValue(strategy = GenerationType.IDENTITY): Especifica que el valor de ID se generará automáticamente por la BD.

        @OneToMany(mappedBy = "odontologo", :
            Indica que esta entidad tiene una relación uno a muchos con la entidad Turno,
            y que la propiedad odontologo en la entidad Turno es la que gestiona la relación.

        cascade = CascadeType.ALL):
            Especifica que todas las operaciones de cascada (persistir, eliminar, etc.) aplicadas a un Odontologo
            también se aplicarán a los Turno asociados.
    */


}
