package ph.txtdis.type;

public enum CivilStatus {
	SINGLE,
	MARRIED,
	SEPARATED,
	ANNULLED,
	DIVORCED,
	WIDOW_ER;

	@Override
	public String toString() {
		return name().replace("_", "/");
	}
}
