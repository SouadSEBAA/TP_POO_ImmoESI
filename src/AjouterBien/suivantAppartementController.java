package AjouterBien;
import Control.ActionReussiteController;

import Noyau.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.AjoutProp;

import java.io.IOException;
import java.util.Date;

public class suivantAppartementController {
    //composants fxml
    @FXML
    private CheckBox meuble;
    @FXML
    private Label warning;

    @FXML
    private CheckBox asscenseur;

    @FXML
    private TextField nbretage;

    @FXML
    private TextField nbrPiece;

    @FXML
    private MenuButton simpdup;

    @FXML
    private Button suivant;

    @FXML
    private TextField photo;

    @FXML
    private Button ajouter;


    Agence agence;
    boolean nego;
    Transaction transaction;
    double superficie;
    double prix;
    String adresse;
    Wilaya wilaya;
    String descriptif;
    Proprietaire p;

    public void setAll(boolean nego,Transaction transaction, double superficie, double prix, String adresse, Wilaya wilaya, String descriptif, Proprietaire p)
    {
        this.transaction=transaction;
        this.nego=nego;
        this.superficie=superficie;
        this.prix=prix;
        this.adresse=adresse;
        this.descriptif=descriptif;
        this.wilaya=wilaya;
        this.p=p;
        nbrPiece.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}(\\d{0,4})?")){
                    nbrPiece.setText(oldValue);}
            }
        });
        nbretage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}(\\d{0,4})?")){
                    nbretage.setText(oldValue);}
            }
        });
    }
    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    public void creation(ActionEvent event) throws IOException {
        boolean juste=true;
        int nbrp=0,nbre=0;
        double suph=0;

        try{nbrp=Integer.parseInt(nbrPiece.getText());}catch(Exception e){}
        try{nbre=Integer.parseInt(nbretage.getText());}catch(Exception e){}
        if(nbretage.getText().isEmpty()||nbrPiece.getText().isEmpty()||photo.getText().isEmpty()){juste=false;warning.setText("Remplissez tous les champs");}
        if(juste){
            agence.ajouter_bien(new Appartement(nego,transaction,photo.getText(),new Date(),superficie,descriptif, prix,p,
                    adresse,wilaya,nbrp,nbre,meuble.isSelected(),true,asscenseur.isSelected()));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/ActionRea.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(loader.load()));

            ActionReussiteController controller = loader.getController();
            controller.setStage(stage);
            stage.setTitle("Opération réussite");
            stage.show();

        }

    }
}
