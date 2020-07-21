package listeBiens;


import Noyau.Agence;
import Noyau.Bien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EnvoyerMessageController {
    //les indicateurs
    Agence agence;
    private Stage stage;

    Bien bien;

    @FXML
    private Label succes;


    boolean fermer=false;
    //les composantes
    @FXML
    private Pane tout;

    @FXML
    private TextField mail;

    @FXML
    private TextArea message;
    @FXML
    private Label Champs1;

    @FXML
    private Label Champs2;

    @FXML
    private Button envoyer;

    @FXML
    private Button annuler;
    public void set(Agence agence,Bien bien){
        this.agence=agence;
        this.bien=bien;
    }
    public boolean getFerme(){return fermer;}
    //***************************
   public void EnvoyerMessage(ActionEvent actionEvent){
       succes.setText("");

       Champs2.setText("");
        Champs1.setText("");
        if(mail.getText().isEmpty() || message.getText().isEmpty()){

            if(mail.getText().isEmpty()){Champs1.setText("*Champs Obligatoir"); }
            if(message.getText().isEmpty()){Champs2.setText("*Champs Obligatoir"); }
        }
        else{
            agence.envoyer_message(bien,mail.getText(),message.getText());
            fermer=true;
            succes.setText("Message envoyé avec succés");
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
