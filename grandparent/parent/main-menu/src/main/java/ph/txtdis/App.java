package ph.txtdis;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.fx.dialog.ChangePasswordDialog;
import ph.txtdis.fx.dialog.LoginDialog;
import ph.txtdis.fx.dialog.MainDialog;
import ph.txtdis.fx.dialog.StartUpDialog;
import ph.txtdis.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class App extends Application {

    private static ConfigurableApplicationContext context;
    private static String title;

    private UserService userService;

    @Override
    public void start(Stage stage) throws Exception {

        new StartUpDialog() {
            @Override
            protected void begin() {
                context = SpringApplication.run(App.class);
                // context.getBean(Setup.class).start();
                // context.getBean(CustomerSetup.class).start();
                title = getParameters().getRaw().get(0);
                userService = context.getBean(UserService.class);
            }

            @Override
            protected void next() {
                LoginDialog loginDialog = new LoginDialog(userService);
                loginDialog.showAndWait();
                if (loginDialog.isValid())
                    switch (loginDialog.getType()) {
                        case LOGIN:
                            new MainDialog(context).showAndWait();
                            break;
                        case SERVER:
                            next();
                            break;
                        case CHANGE:
                            ChangePasswordDialog pd = new ChangePasswordDialog(userService);
                            pd.showAndWait();
                            next();
                            break;
                        default:
                            break;
                    }
            }
        };
    }

    public static void main(String[] args) {
        launch("txtDIS 0.0.2.0 ");
    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    public static String title() {
        return title;
    }
}
