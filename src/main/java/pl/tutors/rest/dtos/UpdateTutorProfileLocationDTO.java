package pl.tutors.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateTutorProfileLocationDTO {
    private double lat;
    private double lng;
}
