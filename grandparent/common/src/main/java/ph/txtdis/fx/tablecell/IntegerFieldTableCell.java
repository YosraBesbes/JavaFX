package ph.txtdis.fx.tablecell;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import ph.txtdis.fx.input.TextStyled;
import ph.txtdis.fx.util.FX;
import ph.txtdis.util.DIS;

public class IntegerFieldTableCell<S> extends TextFieldTableCell<S, Integer>
		implements TextStyled {

	public IntegerFieldTableCell() {
		super(new StringConverter<Integer>() {

			@Override
			public String toString(Integer number) {
				return DIS.formatInt(number);
			}

			@Override
			public Integer fromString(String text) {
				return DIS.parseInt(text);
			}
		});
	}

	@Override
	public void updateItem(Integer number, boolean empty) {
		super.updateItem(number, empty);
		if (!empty)
			FX.styleInt(this, number);
	}
}
