package pl.tutors.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Builder
@Entity
@Data
@Table(name = "\"note\"")
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String text;

    private boolean unrestricted;

    @OneToMany
    private List<User> authorizedUsers;
}
