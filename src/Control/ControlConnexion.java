package Control;

import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import sample.Connexion;

public class ControlConnexion {

    Agence agence ;
    Connexion stage;

    @FXML
    private Label MsgConfirmation;

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    private Button ButtonConnexion;

    @FXML
    private Hyperlink client;


    @FXML
    void Connexion(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        menuAdminController controlleur = loader.getController();
        Parent root;
        try{
            if (! agence.authentifier(new Agent(Username.getText(), Password.getText()))) throw new CnxImpossibleException();
            MsgConfirmation.setText("Confirmed !");
            MsgConfirmation.setTextFill(Color.rgb(21, 117, 84 ));
            //new Stage
            try{
                loader.setLocation(getClass().getResource("../sample/MenuAdmin.fxml"));
                root = loader.load();
                controlleur = loader.getController();
                controlleur.setAgence(this.agence);
                controlleur.setStage(this.stage);
                controlleur.initialise();
                stage.setScene(new Scene(root));
                stage.show();


            }catch(Exception e){
                e.printStackTrace();
            }
        }
        catch (CnxImpossibleException e)
        {
            MsgConfirmation.setText("Mot de passe ou nom d'utilisateur incorrect");
        }

    }

    @FXML
    void pressButtonClient(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/MenuClient.fxml"));//le changer en MenuClient
            Parent root = loader.load();
            menuClientController menuClientController =loader.getController();
            menuClientController.setAgence(agence);
            menuClientController.initialise();
            stage.setScene(new Scene(root));
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public void setStage(Connexion stage) {
        this.stage = stage;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

}
