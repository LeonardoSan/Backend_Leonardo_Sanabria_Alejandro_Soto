package dh.backend.clinicamvc.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private String numMatricula;
    private String nombre;
    private String apellido;

    //--------NOTAS---------
    /* ODONTOLOGO RESPONSE RESPONDE A LAS PETICIONES CON LOS CAMPOS:
        ID, NUMMATRICUKA, NOMBRE, APELLIDO    */

}
