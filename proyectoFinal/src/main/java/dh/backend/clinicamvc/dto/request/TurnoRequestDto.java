package dh.backend.clinicamvc.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TurnoRequestDto {
    public Integer pacienteId;
    public Integer odontologoId;
    public String fecha;

}
