package pl.tutors.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.tutors.domain.dictionary.Discipline;
import pl.tutors.domain.dictionary.Level;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    private String customName;

    private long hourlyRate;

    @Enumerated(EnumType.STRING)
    private Level level;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Offer> offers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();
}
