package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.app.ItemAppImpl;
import ph.txtdis.app.Master;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application implements Master {

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        context = SpringApplication.run(App.class);
        // context.getBean(Setup.class).start();
        new ItemAppImpl().start();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
