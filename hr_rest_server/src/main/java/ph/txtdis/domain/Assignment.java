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
public class Assignment extends AbstractNumbered {

	private static final long serialVersionUID = -4818949926659012333L;

	@Column(nullable = false)
	private LocalDate startDate;

	private LocalDate endDate;

	@Column(nullable = false)
	private String location;

	@Column(nullable = false)
	private String designation;

	public Assignment(LocalDate startDate, String location, String designation) {
		this.startDate = startDate;
		this.location = location;
		this.designation = designation;
	}
}
