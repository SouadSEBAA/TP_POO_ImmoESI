package sample;

import Control.ControlConnexion;
import Noyau.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Connexion extends Stage {

    private Agence agence;

    public Connexion(Agence agence){
        this.agence = agence;

        try{
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource("Connexion.fxml"));
            Parent root = load.load();

            this.setTitle("Connexion");

            //Controleurs
            ControlConnexion controlerConnexion = load.getController();
            controlerConnexion.setAgence(agence);
            controlerConnexion.setStage(this);

            this.setResizable(false);
            this.setScene(new Scene(root));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
