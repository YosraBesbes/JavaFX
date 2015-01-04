package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.app.BookingAppImpl;
import ph.txtdis.app.BookingSetup;
import ph.txtdis.app.Setup;
import ph.txtdis.fx.util.FX;
import ph.txtdis.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        FX.loadIcomoon();
        FX.loadTxtdisIcons();
        context = SpringApplication.run(App.class);
        setup();
        new BookingAppImpl().start();
    }

    private void setup() {
        if (isInitialRun()) {
            context.getBean(Setup.class).start();
            context.getBean(BookingSetup.class).start();
        }
    }

    private boolean isInitialRun() {
        UserService service = context.getBean(UserService.class);
        return !service.exists("JACKIE");
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
