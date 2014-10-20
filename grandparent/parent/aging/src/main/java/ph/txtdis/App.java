package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.app.BookingSetup;
import ph.txtdis.app.CustomerSetup;
import ph.txtdis.app.ItemSetup;
import ph.txtdis.app.PickingSetup;
import ph.txtdis.app.Setup;
import ph.txtdis.app.AgingAppImpl;
import ph.txtdis.app.AgingSetup;
import ph.txtdis.fx.util.FX;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        context = SpringApplication.run(App.class);
        context.getBean(Setup.class).start();
        context.getBean(ItemSetup.class).start();
        context.getBean(CustomerSetup.class).start();
        context.getBean(BookingSetup.class).start();
        context.getBean(PickingSetup.class).start();
        context.getBean(AgingSetup.class).start();
        FX.loadTxtdisIcons();
        new AgingAppImpl().start();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
