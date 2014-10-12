package ph.txtdis.fx.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import javafx.application.Application;
import javafx.scene.paint.Color;
import ph.txtdis.model.Style;
import ph.txtdis.util.Util;

@SuppressWarnings("restriction")
public class StyleSheet {
    private static String MODENA_STYLESHEET_URL = com.sun.javafx.scene.control.skin.ButtonSkin.class.getResource(
            "modena/modena.css").toExternalForm();
    private static String MODENA_STYLESHEET_BASE = MODENA_STYLESHEET_URL.substring(0,
            MODENA_STYLESHEET_URL.lastIndexOf('/') + 1);
    private static StringURLStreamHandlerFactory factory;
    private String content = "";
    private String styleBase = "";
    private Style style;

    public Style getStyle() {
        return style;
    }

    public void update(Style style) {
        this.style = style;

        String base = style.getBase();
        String background = style.getBackground();
        String accent = style.getAccent();
        String font = style.getFont();

        styleBase = MODENA_STYLESHEET_BASE;
        content = loadUrl(MODENA_STYLESHEET_URL) + "\n.root {\n";
        content += " -fx-base: " + base + ";\n";
        if (changed(background))
            content += " -fx-background: " + background + ";\n";
        content += " -fx-accent:" + accent + ";\n";
        content += " -fx-font: 11pt \"" + font + "\";\n" + "}\n";

        Application.setUserAgentStylesheet("internal:stylesheet" + Math.random() + ".css");
    }

    {
        if (factory == null)
            factory = new StringURLStreamHandlerFactory();
        URL.setURLStreamHandlerFactory(factory);
    }

    private boolean changed(String color) {
        return color != null && !color.equals(Util.colorToRGBA(Color.TRANSPARENT));
    }

    private static String loadUrl(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    private class StringURLConnection extends URLConnection {
        public StringURLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException {
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(content.getBytes("UTF-8"));
        }
    }

    private class StringURLStreamHandlerFactory implements URLStreamHandlerFactory {
        URLStreamHandler streamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                if (url.toString().toLowerCase().endsWith(".css")) {
                    return new StringURLConnection(url);
                } else {
                    return new URL(styleBase + url.getFile()).openConnection();
                }
            }
        };

        @Override
        public URLStreamHandler createURLStreamHandler(String protocol) {
            if ("internal".equals(protocol)) {
                return streamHandler;
            }
            return null;
        }
    }
}
