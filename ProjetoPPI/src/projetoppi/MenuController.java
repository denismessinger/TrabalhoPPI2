package projetoppi;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button lista;

    @FXML
    private Button calendario;

    @FXML
    private Button manuais;

    @FXML
    private Button sair;

    @FXML
    void calendario(ActionEvent event) throws IOException {
        Parent fxmlNovos = FXMLLoader.load(getClass().getResource("Novo.fxml"));
        Scene Novoscene = new Scene(fxmlNovos, 900, 553);
        ProjetoPPI.stage.setScene(Novoscene);
        ProjetoPPI.stage.show();
    }

    @FXML
    void lista(ActionEvent event) throws IOException {
        Parent fxmlLista = FXMLLoader.load(getClass().getResource("Listas.fxml"));
        Scene Listasscene = new Scene(fxmlLista, 699, 418);
        ProjetoPPI.stage.setScene(Listasscene);
        ProjetoPPI.stage.show();
    }

    @FXML
    void manuais(ActionEvent event) throws IOException {
        Parent fxmlManuais = FXMLLoader.load(getClass().getResource("ManuaisMenu.fxml"));
        Scene ManuaisMenuscene = new Scene(fxmlManuais, 502, 314);
        ProjetoPPI.stage.setScene(ManuaisMenuscene);
        ProjetoPPI.stage.show();
    }

    @FXML
    void sair(ActionEvent event) {
        ProjetoPPI.changeScreen("login");
    }

}
