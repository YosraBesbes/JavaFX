package ph.txtdis.fx.button;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.util.Util;

public class BackupButton extends FontButton<Object> {

    public <C> BackupButton(Stage stage) {
        super("\ue821", "Backup", 44);
        button.setOnAction(event -> {
            File path = new DirectoryChooser().showDialog(stage);
            if (path == null)
                return;
            String file = path + "\\" + Util.zdtToFileName(ZonedDateTime.now()) + ".backup";
            ArrayList<String> baseCmds = new ArrayList<>();
            baseCmds.add("c:\\Program Files\\PostgreSQL\\9.3\\bin\\pg_dump.exe");
            baseCmds.add("-h");
            baseCmds.add("localhost");
            baseCmds.add("-p");
            baseCmds.add("5432");
            baseCmds.add("-U");
            baseCmds.add("postgres");
            baseCmds.add("-f");
            baseCmds.add(file);
            baseCmds.add("-F");
            baseCmds.add("c");
            baseCmds.add("mgdc_dm_7");
            final ProcessBuilder pb = new ProcessBuilder(baseCmds);
            pb.environment().put("PGPASSWORD", "I'mAdmin4txtDIS@PostgreSQL");

            try {
                Process process = pb.start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                new ErrorDialog(stage, e.getMessage().replace(". ", ".\n").replace(": ", ":\n"));
            }
        });
    }
}
