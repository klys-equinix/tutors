package pl.tutors.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tutors.domain.dictionary.Discipline;
import pl.tutors.domain.dictionary.Level;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileQuery {
    public Double lat;
    public Double lng;
    public Long radius;
    public Discipline discipline;
    public Level level;
    public Double hourlyRate__loe;
    public Double hourlyRate__goe;
    public Double hourlyRateWithCommute__loe;
    public Double hourlyRateWithCommute__goe;
}
