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
public class Compensation extends AbstractNumbered {

	private static final long serialVersionUID = -2803418471196037075L;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private BigDecimal dailyRateValue;
}
