package pl.tutors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tutors.domain.User;
import pl.tutors.repository.NoteRepository;
import pl.tutors.rest.dtos.NoteCreationDTO;
import pl.tutors.domain.Note;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserManagementFacade userManagementFacade;

    public Note createNote(NoteCreationDTO noteCreationDTO) {
        Note note = Note.builder()
                .text(noteCreationDTO.getText())
                .unrestricted(noteCreationDTO.isUnrestricted())
                .build();
        if(!noteCreationDTO.isUnrestricted()) {
            User user = userManagementFacade.getCurrentUser();
            note.setAuthorizedUsers(noteCreationDTO.getAuthorizedUserIds()
                    .stream()
                    .filter(id -> !id.equals(user.getId()))
                    .map(userManagementFacade::getUserById)
                    .collect(Collectors.toList())
            );
            note.getAuthorizedUsers().add(user);
        }
        return noteRepository.save(note);
    }

    public List<Note> findAllNotesForUser(User user) {
        return noteRepository.findAllForUser(user);
    }
}
