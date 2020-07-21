package Control;

import Noyau.Agence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import listeBiens.listeBienController;

import java.io.IOException;

public class menuClientController {

    Agence agence;

    @FXML
    private Button listeBien;

    @FXML
    private Button listeProp;

    @FXML
    private Button rech;

    @FXML
    private SubScene cote;

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public  void initialise(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
        Parent node=null;
        try {
            node = loader.load();
        }catch (Exception e)     {}


        listeBienController controller = loader.getController();
        if (agence == null) {
            System.out.println("nullll");
        }
        controller.setAgence(this.agence);
        controller.ajouter_biens(agence.getListe_biens(),false);
        //  controller.setList(cote.getWidth(), cote.getHeight());
        cote.setRoot(node);
    }

    public void PressButton(ActionEvent event) throws IOException {
        try {


        if (event.getSource() == listeBien) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
            Parent node = loader.load();
            listeBienController controller = loader.getController();
            if (agence == null) {
                System.out.println("nullll");
            }
            controller.setAgence(this.agence);
            controller.ajouter_biens(agence.getListe_biens(),false);
            node.resize(cote.getWidth(), cote.getHeight());
          //  controller.setList(cote.getWidth(), cote.getHeight());
            cote.setRoot(node);
        } else
            if (event.getSource() == rech) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/RechercheMulticriteres.fxml"));
            Parent node = loader.load();
            RechercheMulticriteresController controller = loader.getController();
            controller.setAgence(this.agence);


            node.resize(cote.getWidth(), cote.getHeight());
            System.out.println(cote.getWidth() + " " + cote.getHeight());
            cote.setRoot(node);


        }
            else
            if (event.getSource()==rech){//la recherche
                FXMLLoader loader = new FXMLLoader();
                Parent node;
                loader.setLocation(getClass().getResource("../sample/RechercheMulticriteres.fxml"));
                node = loader.load();

                RechercheMulticriteresController controller = loader.getController();
                controller.setAgence(this.agence);
                System.out.println(cote.getWidth()+" "+ cote.getHeight());
                cote.setRoot(node);
            }
            else
            if (event.getSource()==listeProp){//liste des props
                FXMLLoader loader = new FXMLLoader();
                Parent node;
                loader.setLocation(getClass().getResource("../sample/ListeProprietaires.fxml"));
                node = loader.load();

                ListeProprietairesController controller = loader.getController();
                controller.setAgence(this.agence);
                controller.setCote(this.cote);
                cote.setRoot(node);
            }}
        finally {
            agence.serialisation();
        }
    }

}
