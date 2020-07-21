package sample;

import Control.AjoutPropController;
import Noyau.Agence;
import Noyau.Proprietaire;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutProp extends Stage {



    public AjoutProp(Agence agence, TableView<Proprietaire> table){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AjoutProp.fxml"));
            Parent node = loader.load();

            AjoutPropController controller = loader.getController();
            this.initModality(Modality.APPLICATION_MODAL);
            controller.setStage(this);
            controller.setAgence(agence);
            controller.setTable(table);

            this.setScene(new Scene(node));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
