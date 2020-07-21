package sample;

import Control.Controller;
import Noyau.Agence;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Accueil extends Stage {

    private Agence agence;

    public Accueil(Agence agence){
        this.agence = agence;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            Controller controller = loader.getController();
            controller.setAgence(agence);


            setTitle("Welcome to ImmoESI");
            setResizable(false);
            setScene(new Scene(root));
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
