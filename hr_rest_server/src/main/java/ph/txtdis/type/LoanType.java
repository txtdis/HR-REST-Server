package ph.txtdis.type;

public enum LoanType {
	ADVANCE,
	SHORTAGE,
	PENALTY,
	PURCHASE, 
	DEDUCTION,
	SSS,
	PAG_IBIG;

	@Override
	public String toString() {
		return name().replace("_", "-");
	}
}
