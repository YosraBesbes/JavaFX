package ph.txtdis.fx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ph.txtdis.fx.tab.ImageStreamed;

public class ImageLoader implements EventHandler<MouseEvent> {
	final ImageStreamed imageStream;
	final Label photoLabel;
	final Stage stage;

	public ImageLoader(Stage stage, Label label, ImageStreamed imageStream) {
		this.stage = stage;
		this.photoLabel = label;
		this.imageStream = imageStream;
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getClickCount() > 1) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Load photo");
			File file = fileChooser.showOpenDialog(stage);
			if (file != null)
				placeImageStream(file);
		}
	}

	private void placeImageStream(File file) {
		try {
			imageStream.setImageStream(new FileInputStream(file));
			photoLabel.setGraphic(FX.toImageView(imageStream.getImageStream()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
