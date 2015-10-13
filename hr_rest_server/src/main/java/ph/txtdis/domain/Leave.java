package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.LeaveType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leave extends AbstractNumbered {

	private static final long serialVersionUID = -3939404843675593545L;

	@Column(nullable = false)
	private LeaveType type;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private int dayCount;
}
