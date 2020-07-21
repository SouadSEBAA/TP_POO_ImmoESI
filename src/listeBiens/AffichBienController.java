package listeBiens;

import Control.ModifierBienController;
import Control.ActionReussiteController;
import Noyau.Agence;
import Noyau.Bien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;

public class AffichBienController extends Pane {
    private Bien bien;
    private Agence agence;

    //les composantes
    @FXML
    private Pane tout;
    @FXML
    private TextField prix;

    @FXML
    private TextField Superficie;

    @FXML
    private TextField wilaya;

    @FXML
    private TextField adresse;

    @FXML
    private ImageView image;
    @FXML
    private TextField type;

    //les boutons

    @FXML
    private Button afficher;

    @FXML
    private Button supprimer;

    @FXML
    private Button modifier;
    @FXML
    private Button archiver;
    @FXML
    private Button renseignement;
    @FXML
    private TextField nature;
    @FXML
    private TextField date;
    @FXML
    private VBox box;


    public void setBien(Bien bien) {
        this.bien = bien;
    }


    public void setAgence(Agence agence){
        this.agence=agence;
    }

    void ajouter_info(Bien a)   {
        try {
            type.setText(""+a.getClass().getName().substring(6));
            prix.setText(""+a.Calcul_prix(false));
            Superficie.setText(""+a.getSuperficie());
            wilaya.setText(""+a.getWilaya().name());
            adresse.setText(a.getAdresse());
            nature.setText(a.getNat_transaction().name());
            date.setText(""+a.getDate());
         /*   image=new ImageView();
           InputStream input=this.getClass().getResourceAsStream(a.getPhotos());
            Image image1=new Image(input);
            image.setImage(image1);
            tout.getChildren().add(image);
           // image.setImage(photo);
            System.out.println(a.getPhotos());*/


        }
        catch(Exception e){}


    }


    public void supprimer(ActionEvent actionEvent) throws IOException {
        try{
            int ind=(adresse.getText()+wilaya.getText()).hashCode();
            Iterator<Bien> i=(agence.getListe_biens()).iterator();
            Bien bien=i.next();
            while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
            if(bien.hashCode()==ind){ agence.suppr_bien(bien);}

            //comment mettre à jour
        }
        catch (Exception e){}

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../sample/ActionRea.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        ActionReussiteController controller = loader.getController();
        controller.setStage(stage);
        stage.setTitle("Opération réussite");
        stage.show();

    }
    public void archiver(ActionEvent actionEvent) throws IOException, InterruptedException {
        try{
            if(agence==null){System.out.println("Archiver");}
            int ind=(adresse.getText()+wilaya.getText()).hashCode();
            Iterator<Bien> i=(agence.getListe_biens()).iterator();
            Bien bien=i.next();
            while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
            if(bien.hashCode()==ind){ agence.archiver_bien(bien);System.out.println("Archiver");}

            //comment mettre à jour
        }
        catch (Exception e){}
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../sample/ActionRea.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));

        ActionReussiteController controller = loader.getController();
        controller.setStage(stage);
        stage.setTitle("Opération réussite");
        stage.show();

    }
    public void Afficher(ActionEvent actionEvent){
        try{
            int ind=(adresse.getText()+wilaya.getText()).hashCode();
            Iterator<Bien> i=(agence.getListe_biens()).iterator();
            Bien bien=i.next();
            while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
            if(bien.hashCode()==ind){
                AffichBienDetailController controlleur=null;
                Parent root=null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../listeBiens/AffichBienDetail.fxml"));
                    root = loader.load();

                    Scene scene=new Scene(root);
                    Stage stage=new Stage();stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Affichage dun bien");

                    controlleur = loader.getController();
                    controlleur.set(agence,bien);
                    controlleur.setStage(stage);
                    controlleur.Affich_infos();

                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                }
                catch(Exception e){System.out.println("Erreur");e.printStackTrace();}
            }

            //comment mettre à jour
        }
        catch (Exception e){}

    }
    //***************************
    public void EnvoyerMessage(ActionEvent actionEvent){
        int ind=(adresse.getText()+wilaya.getText()).hashCode();
        Iterator<Bien> i=(agence.getListe_biens()).iterator();
        Bien bien=i.next();
        while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
        if(bien.hashCode()==ind){
            EnvoyerMessageController controlleur=null;
            Parent root=null;
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../listeBiens/EnvoyerMessage.fxml"));
                root = loader.load();
                controlleur = loader.getController();
                controlleur.set(agence,bien);
                Scene scene=new Scene(root);
                Stage stage=new Stage();stage.setScene(scene);
                stage.setTitle("Envoyer un Message");
                stage.setResizable(false);
                controlleur.setStage(stage);

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                if(controlleur.getFerme()){stage.close();}

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void PrepareClient(){
        box.getChildren().remove(archiver);
        box.getChildren().remove(modifier);
        box.getChildren().remove(supprimer);
    }
    public void PrepareAdmin(){
        box.getChildren().removeAll(renseignement);
    }


    @FXML
    void Modifier(ActionEvent event) throws IOException {
        int ind=(adresse.getText()+wilaya.getText()).hashCode();
        Iterator<Bien> i=(agence.getListe_biens()).iterator();
        Bien bien=i.next();
        while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
        if(bien.hashCode()==ind){
        FXMLLoader loader = new FXMLLoader();
        Parent node;
        loader.setLocation(getClass().getResource("../sample/ModifierBien.fxml"));//Afficher liste bien
        node = loader.load();

        Stage stage = new Stage();
        Scene scene = new Scene(node, 900, 750);
        stage.setX(497);
        stage.setY(47);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        ModifierBienController controller1 = loader.getController();
        controller1.setBien(bien);
        controller1.setAgence(this.agence);
        controller1.setStage(stage);

        stage.show();}
    }

}
