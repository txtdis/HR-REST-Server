package ph.txtdis.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.LoanType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan extends AbstractNumbered {

	private static final long serialVersionUID = 4690591771708072167L;

	@Column(nullable = false)
	private LoanType type;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column(nullable = false)
	private BigDecimal loanValue;

	@JoinColumn(name = "loan_id")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payments;

	public Loan(LoanType type, LocalDate startDate, BigDecimal loanValue) {
		this.type = type;
		this.startDate = startDate;
		this.loanValue = loanValue;
	}
}
