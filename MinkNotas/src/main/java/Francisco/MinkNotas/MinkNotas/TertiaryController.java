package Francisco.MinkNotas.MinkNotas;

import java.io.IOException;
import javafx.fxml.FXML;

public class TertiaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("tertiary");
    }
}