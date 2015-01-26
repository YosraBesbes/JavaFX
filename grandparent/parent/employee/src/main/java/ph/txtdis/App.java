package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ph.txtdis.fx.dialog.ChangePasswordDialog;
import ph.txtdis.fx.dialog.EmployeeDialog;
import ph.txtdis.fx.dialog.LoginDialog;
import ph.txtdis.fx.dialog.StartUpDialog;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableAutoConfiguration
@ComponentScan
@Import({ DataSourceConfiguration.class })
public class App extends Application {

    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage stage) throws Exception {

        new StartUpDialog() {
            @Override
            protected void begin() {
                context = SpringApplication.run(App.class);
            }

            @Override
            protected void next() {
                LoginDialog loginDialog = new LoginDialog(context);
                if (loginDialog.isValid())
                    switch (loginDialog.getType()) {
                        case LOGIN:
                            new EmployeeDialog(context).showAndWait();
                            break;
                        case SERVER:
                            next();
                            break;
                        case CHANGE:
                            new ChangePasswordDialog(context).showAndWait();
                            next();
                            break;
                        default:
                            break;
                    }
            }
        };
    }

    public static void main(String[] args) {
        launch();
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
