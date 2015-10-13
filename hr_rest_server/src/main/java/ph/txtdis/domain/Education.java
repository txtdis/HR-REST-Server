package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education extends AbstractNumbered {

    private static final long serialVersionUID = -314345074933355267L;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(length = 16, nullable = false)
    private String institution;

    @Column(length = 16, nullable = false)
    private String program;

    @Column(length = 16, nullable = false)
    private String highestHonor;

    public Education(LocalDate startDate, LocalDate endDate, String institution, String program, String highestHonor) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.institution = institution;
        this.program = program;
        this.highestHonor = highestHonor;
    }
}
