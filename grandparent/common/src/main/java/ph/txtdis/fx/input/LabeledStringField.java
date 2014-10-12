package ph.txtdis.fx.input;

public class LabeledStringField extends LabeledTextField<String>{

	public LabeledStringField(String name) {
		super(name, new StringField());
	}

	@Override
	public String getValue() {
		return getText();
	}
}
