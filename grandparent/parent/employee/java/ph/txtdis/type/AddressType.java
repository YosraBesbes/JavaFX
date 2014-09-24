package ph.txtdis.type;

public enum AddressType {
	HOME,
	WORK,
	MAIN,
	DELIVERY,
	PICK_UP,
	INVOICING,
	PAYMENT;

	@Override
	public String toString() {
		return name().replace("_", "-");
	}
}
