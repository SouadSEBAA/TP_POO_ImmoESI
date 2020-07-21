package Control;

import AjouterBien.AjouterBienController;
import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import listeBiens.listeBienController;

import java.io.IOException;
import java.util.ArrayList;

public class menuAdminController {

    private Agence agence;
    private Stage stage;

    @FXML
    private Button listeBien;

    @FXML
    private Button listeProp;

    @FXML
    private Button archives;

    @FXML
    private Button messages;

    @FXML
    private SubScene cote;

    @FXML
    private Button ajout;

    @FXML
    private Button rech;

    public  void initialise(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
        Parent node=null;
        try {
            node = loader.load();
        }catch (Exception e){}

        listeBienController controller = loader.getController();
        if (agence == null) {
            System.out.println("nullll");
        }
        controller.setAgence(this.agence);
        controller.ajouter_biens(agence.getListe_biens(), true);
        node.resize(cote.getWidth(), cote.getHeight());
        //   controller.setList(cote.getWidth(),cote.getHeight());
        cote.setRoot(node);
    }

    public void PressButton( ActionEvent event)throws IOException {
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
            controller.ajouter_biens(agence.getListe_biens(),true);
            node.resize(cote.getWidth(),cote.getHeight());
         //   controller.setList(cote.getWidth(),cote.getHeight());
            cote.setRoot(node);
        }
        else
        if(event.getSource()==rech){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../sample/RechercheMulticriteres.fxml"));
            Parent node = loader.load();
            RechercheMulticriteresController controller = loader.getController();
            controller.setAgence(this.agence);


            node.resize(cote.getWidth(),cote.getHeight());
            System.out.println(cote.getWidth()+" "+ cote.getHeight());
            cote.setRoot(node);


        }
        else
        if(event.getSource()==archives){//les Archives
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
            Parent node = loader.load();
            listeBienController controller = loader.getController();
            if (agence == null) {
                System.out.println("nullll");
            }
            controller.setAgence(this.agence);
            controller.ajouter_biens(agence.getArchive(),true);
            node.resize(cote.getWidth(),cote.getHeight());
         //   controller.setList(cote.getWidth(),cote.getHeight());
            cote.setRoot(node);
        }
        else
        if(event.getSource()==ajout){//ajouter un bien
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("../AjouterBien/AjouterBien.fxml"));
            Parent node=null;
            try{
                node = loader2.load();
            }catch (Exception e){e.printStackTrace();System.out.println("c'est ici");}

            AjouterBienController controller = loader2.getController();
            controller.setAgence(agence);
            controller.initialise();
            cote.setRoot(node);
        }
        else
        if(event.getSource()==messages){//Affichage des messages reçus
            FXMLLoader loader = new FXMLLoader();
            Parent node;
            loader.setLocation(getClass().getResource("../sample/MessagesRecus.fxml"));
            node = loader.load();

            MessagesRecusController controller = loader.getController();
            controller.setAgence(this.agence);

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
            controller.setAdmin(true);

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
        }
        }
        finally {
            agence.serialisation();
        }

    }

    @FXML
    void PressButtonQuit(ActionEvent event) {
        stage.close();
    }
    //********************************

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
