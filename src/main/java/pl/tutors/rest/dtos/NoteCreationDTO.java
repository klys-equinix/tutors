package pl.tutors.rest.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteCreationDTO {
    @Length(max = 100)
    private String text;

    private boolean unrestricted;

    private List<UUID> authorizedUserIds;
}
