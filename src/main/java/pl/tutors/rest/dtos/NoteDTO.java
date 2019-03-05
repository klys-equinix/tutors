package pl.tutors.rest.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import pl.tutors.domain.Note;

import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteDTO {
    private UUID id;

    private String text;

    private Boolean unrestricted;

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.text = note.getText();
        this.unrestricted = note.isUnrestricted();
    }
}
