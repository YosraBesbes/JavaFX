package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.app.InvoiceBookletSetup;
import ph.txtdis.app.InvoicingSetup;
import ph.txtdis.app.PickingSetup;
import ph.txtdis.app.RemittanceAppImpl;
import ph.txtdis.app.Setup;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {
        context = SpringApplication.run(App.class);
        context.getBean(Setup.class).start();
        context.getBean(InvoiceBookletSetup.class).start();
        context.getBean(PickingSetup.class).start();
        context.getBean(InvoicingSetup.class).start();
        new RemittanceAppImpl().start();
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConfigurableApplicationContext context() {
        return context;
    }
}
