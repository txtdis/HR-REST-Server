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
public class Discipline extends AbstractNumbered {

	private static final long serialVersionUID = -2655032643722025128L;

	@Column(nullable = false)
	private LocalDate incidenceDate;

	@Column(nullable = false)
	private String allegation;

	private String decision;

	private LocalDate effectivityDate;

	private int dayCount;

	private boolean noticeGiven;

	private boolean replyReceived;

	private boolean decisionGiven;

	public Discipline(LocalDate incidence, String allegation) {
		this.incidenceDate = incidence;
		this.allegation = allegation;
	}
}
