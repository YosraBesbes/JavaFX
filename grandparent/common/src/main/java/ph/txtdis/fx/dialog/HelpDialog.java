package ph.txtdis.fx.dialog;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.apache.commons.io.IOUtils;

public class HelpDialog extends AbstractInputDialog<String> {

	public HelpDialog(Stage stage, String html) {
		super("Help", stage, html);
		show();
	}

	@Override
	protected void populateGrid(GridPane gridPane, String html) {
		final WebView browser = setBrowser();
		showHelp(inputStreamToHtmlString(html), browser);
		gridPane.add(browser, 0, 0);
	}

	private InputStream getInputStream(String html) {
		final InputStream inputStream = this.getClass().getResourceAsStream(
				"/help/" + html + ".html");
		return inputStream;
	}

	private String inputStreamToString(final InputStream inputStream) {
		try {
			return IOUtils.toString(inputStream, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String inputStreamToHtmlString(String html) {
		final InputStream is = getInputStream(html);
		final String s = inputStreamToString(is);
		return correctLocalPaths(s);
	}

	private void showHelp(final String text, final WebView browser) {
		final WebEngine webEngine = browser.getEngine();
		webEngine.loadContent(text);
	}

	private String correctLocalPaths(final String html) {
		return html.replace("local:", getClass().getResource("/demo/").toString());
	}

	private WebView setBrowser() {
		final WebView browser = new WebView();
		browser.setPrefSize(getStage().getWidth() / 2,
				getStage().getHeight() / 2);
		return browser;
	}

	@Override
	protected Button[] createButtons(String html) {
		return new Button[] { createCloseButton() };
	}
}