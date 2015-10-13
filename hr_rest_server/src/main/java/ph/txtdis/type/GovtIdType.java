package ph.txtdis.type;

public enum GovtIdType {
	LTO, SSS, TIN, PHILHEALTH, PAG_IBIG, BARANGAY, COMELEC;

	@Override
	public String toString() {
		return name().replace("_", "-");
	}
}
