package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.fx.dialog.MainDialog;
import ph.txtdis.fx.dialog.StartUpDialog;
import ph.txtdis.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;

    private UserService userService;

    @Override
    public void start(Stage stage) throws Exception {

        new StartUpDialog() {
            @Override
            protected void begin() {
                context = SpringApplication.run(App.class);
                userService = context.getBean(UserService.class);
            }

            @Override
            protected void next() {
                // LoginDialog loginDialog = new LoginDialog(userService);
                // loginDialog.showAndWait();
                // if (loginDialog.isValid())
                // switch (loginDialog.getType()) {
                // case LOGIN:
                new MainDialog(context).showAndWait();
                // break;
                // case SERVER:
                // next();
                // break;
                // case CHANGE:
                // ChangePasswordDialog pd = new
                // ChangePasswordDialog(userService);
                // pd.showAndWait();
                // next();
                // break;
                // default:
                // break;
                // }
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
