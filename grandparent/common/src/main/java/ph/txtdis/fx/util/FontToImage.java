package ph.txtdis.fx.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javax.imageio.ImageIO;

public class FontToImage {

    private Image image;

    public FontToImage(String fontName, String text, Color color) {
        image = new Image(iconStream(fontName, text, color));
    }

    private ByteArrayInputStream iconStream(String fontName, String text, Color color) {
        final double size = 256;
        final Canvas canvas = new Canvas(size, size);

        FX.loadFont(fontName);

        final Font font = new Font(fontName, size);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(color);
        gc.fillText(text, size / 2, size / 2);

        final SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        final WritableImage snapshot = canvas.snapshot(params, null);
        final BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeImage(bufferedImage, out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        return in;
    }

    private void writeImage(final BufferedImage bufferedImage, ByteArrayOutputStream out) {
        try {
            ImageIO.write(bufferedImage, "png", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }
}
