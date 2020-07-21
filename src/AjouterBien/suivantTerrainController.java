package AjouterBien;

import Control.ActionReussiteController;
import Noyau.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class suivantTerrainController {
private Agence agence;
    boolean nego;
    Transaction transaction;
    double superficie;
    double prix;
    String adresse;
    Wilaya wilaya;
    String descriptif;
    //les composants fxml
    @FXML
    private Pane tout;
    @FXML
    private Label warning;

    @FXML
    private TextField nrfaçade;

    @FXML
    private TextField statu;

    @FXML
    private TextField photo;

    @FXML
    private Button suivant2;

    @FXML
    private Pane coteprop;

    private Proprietaire p;

    public void setAll(boolean nego,Transaction transaction, double superficie, double prix, String adresse, Wilaya wilaya, String descriptif, Proprietaire p)
    {
        this.transaction=transaction;
        this.nego=nego;
        this.superficie=superficie;
        this.prix=prix;
        this.adresse=adresse;
        this.descriptif=descriptif;
        this.wilaya=wilaya;
        this.p = p;
        nrfaçade.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}(\\d{0,4})?")){
                    nrfaçade.setText(oldValue);}
            }
        });
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    public void creation(ActionEvent event) throws IOException {
        boolean juste=true;
        int nbr=0;

        try{nbr=Integer.parseInt(nrfaçade.getText());}catch(Exception e){}
        if(nrfaçade.getText().isEmpty()||photo.getText().isEmpty()||statu.getText().isEmpty()){juste=false;warning.setText("Remplissez tous les champs");}
        if(juste) {
            agence.ajouter_bien(new Terrain(nego, transaction, photo.getText(), new Date(), superficie, descriptif, prix, p,
                    adresse, wilaya, statu.getText(), nbr));

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/ActionRea.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(loader.load()));

            ActionReussiteController controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Opération réussite");
            stage.show();
            /*
            tout.getChildren().removeAll(tout.getChildren());
            BackgroundImage myBI= new BackgroundImage(new Image("images.png",32,32,false,true),
                    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
           tout.setBackground(new Background(myBI));*/

        }
    }
}
