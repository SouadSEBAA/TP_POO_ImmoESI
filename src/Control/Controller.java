package Control;

import Noyau.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Connexion;

public class Controller {


    private Agence agence;

    @FXML
    private Button Quitter;

    @FXML
    private ImageView logo;

    @FXML
    void PressButtonAdmin(ActionEvent event) {
        Connexion connexion = new Connexion(agence);
        connexion.show();
    }

    @FXML
    void PressButtonClients(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/MenuClient.fxml"));//le changer en MenuClient
            Parent root = loader.load();
            menuClientController menuClientController =loader.getController();
            menuClientController.setAgence(agence);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void PressButtonQuit(ActionEvent event){
        Platform.exit();
    }


    public void setAgence(Agence agence) {
        this.agence = agence;
    }
}

