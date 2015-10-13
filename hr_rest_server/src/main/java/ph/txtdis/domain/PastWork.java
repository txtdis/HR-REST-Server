package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PastWork extends AbstractNumbered {

	private static final long serialVersionUID = 4972100444095311026L;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private LocalDate endDate;

	@Column(nullable = false)
	private String employer;

	@Column(nullable = false)
	private String designation;

	@Column(nullable = false)
	private BigDecimal lastPayValue;

	@Column(nullable = false)
	private String reasonForLeaving;

	@Column(nullable = false)
	private String referenceName;

	@Column(nullable = false)
	private String referenceDesignation;

	@Column(nullable = false)
	private String referencePhone;
}
