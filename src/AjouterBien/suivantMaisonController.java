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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class suivantMaisonController {
    //infos
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
        this.p = p;
        nbretage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}(\\d{0,4})?")){
                    nbretage.setText(oldValue);}
            }
        });
        nbrpiece.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}(\\d{0,4})?")){
                    nbrpiece.setText(oldValue);}
            }
        });
        supHaitable.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")){
                    supHaitable.setText(oldValue);}
            }
        });
    }
    //les composants

    @FXML
    private CheckBox meuble;

    @FXML
    private CheckBox garage;

    @FXML
    private CheckBox piscine;

    @FXML
    private CheckBox jardin;

    @FXML
    private TextField supHaitable;

    @FXML
    private TextField nbretage;

    @FXML
    private TextField nbrpiece;

    @FXML
    private Button suivant2;

    @FXML
    private TextField pathphoto;

    @FXML
    private Pane coteprop;
    @FXML
    private Label warning;


    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    //************
    public void creation(ActionEvent event) throws IOException {
        boolean juste=true;
        int nbrp=0,nbre=0;
        double suph=0;

        try{nbrp=Integer.parseInt(nbrpiece.getText());}catch(Exception e){}
        try{nbre=Integer.parseInt(nbretage.getText());}catch(Exception e){}
        try{suph=Double.parseDouble(supHaitable.getText());}catch(Exception e){}
        if(nbretage.getText().isEmpty()||nbrpiece.getText().isEmpty()||supHaitable.getText().isEmpty()){juste=false;warning.setText("Remplissez tous les champs");}
            if(suph>superficie){juste=false;supHaitable.setText("");supHaitable.setPromptText("Superficie Habitale est superieur à la totale");}
            if(juste){
            agence.ajouter_bien(new Maison(nego,transaction,pathphoto.getText(),new Date(),superficie,descriptif, prix,p,
                adresse,wilaya,nbrp,meuble.isSelected(),nbre,piscine.isSelected(),garage.isSelected(),suph,jardin.isSelected()));
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
