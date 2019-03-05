package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tutors.rest.dtos.NoteCreationDTO;
import pl.tutors.rest.dtos.NoteDTO;
import pl.tutors.service.NoteService;
import pl.tutors.service.UserManagementFacade;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/note")
@RequiredArgsConstructor
public class NoteResource {

    private final NoteService noteService;
    private final UserManagementFacade userManagementFacade;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<NoteDTO> addNote(@RequestBody @Valid NoteCreationDTO noteCreationDTO) {
        return ResponseEntity.ok(new NoteDTO(noteService.createNote(noteCreationDTO)));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<NoteDTO>> getNotes() {
        return ResponseEntity.ok(noteService.findAllNotesForUser(userManagementFacade.getCurrentUser())
                .stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList())
        );
    }
}
