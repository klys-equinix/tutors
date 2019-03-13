package pl.tutors.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "contact")
@Getter
@Setter
public class ContactProperties {
    private String phone;
    private String email;
    private WorkingHours workingHours;

    @Getter
    @Setter
    public static class WorkingHours {
        private OpenClose workdays;
        private OpenClose saturday;
        private OpenClose sunday;

        @Getter
        @Setter
        public static class OpenClose {
            private String open;
            private String close;
        }

        public WorkingHours() {
            workdays = new OpenClose();
            workdays.open = "8:00";
            workdays.close = "20:00";

            saturday = new OpenClose();
            sunday = new OpenClose();
        }
    }

    public ContactProperties() {
        phone = "22 228 74 86";
        email = "kontakt@tutors.pl";
        workingHours = new WorkingHours();
    }
}
