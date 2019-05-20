package pl.tutors.rest.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.tutors.domain.Lesson;
import pl.tutors.domain.Offer;
import pl.tutors.domain.dictionary.Discipline;
import pl.tutors.domain.dictionary.Level;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {
    public UUID id;

    public Discipline discipline;

    public String customName;

    public long hourlyRate;

    public Level level;

    public List<Offer> offers = new ArrayList<>();

    public List<Lesson> lessons = new ArrayList<>();
}
