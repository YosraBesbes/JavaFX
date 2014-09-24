package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.app.CustomerSetup;
import ph.txtdis.app.ItemSetup;
import ph.txtdis.app.PurchasingAppImpl;
import ph.txtdis.app.PurchasingSetup;
import ph.txtdis.app.Setup;
import ph.txtdis.fx.dialog.StartUpDialog;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;
    private static String title;

    @Override
    public void start(Stage stage) throws Exception {

        new StartUpDialog() {
            @Override
            protected void begin() {
                context = SpringApplication.run(App.class);
                context.getBean(Setup.class).start();
                context.getBean(CustomerSetup.class).start();
                context.getBean(ItemSetup.class).start();
                context.getBean(PurchasingSetup.class).start();
                title = getParameters().getRaw().get(0);
            }

            @Override
            protected void next() {
                new PurchasingAppImpl().start();
            }
        };
    }

    public static void main(String[] args) {
        launch("Purchasing 0.9.0.0 ");
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static String title() {
        return title;
    }
}
