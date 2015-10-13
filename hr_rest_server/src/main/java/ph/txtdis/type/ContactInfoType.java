package ph.txtdis.type;

public enum ContactInfoType {
	HOME_PHONE,
	WORK_PHONE,
	CELLPHONE,
	E$MAIL,
	TWITTER,
	OTHER;

	@Override
	public String toString() {
		return name().replace("_", " ").replace("$", "-");
	}
}
