package listeBiens;

import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Iterator;

public class AffichBienDetailController {
    //les infos du bien  ajouter
    Agence agence;
    Bien bien;
    //les composants de la maison
    @FXML
    private Pane tout;

    @FXML
    private GridPane grille;

    @FXML
    private TextField type;

    @FXML
    private TextField Superficie;

    @FXML
    private TextField wilaya;

    @FXML
    private TextField date;

    @FXML
    private TextField adresse;

    @FXML
    private TextField prix;

    @FXML
    private TextField meuble;

    @FXML
    private TextField nbrpiece;

    @FXML
    private TextField suphabitable;

    @FXML
    private TextField prop;

    @FXML
    private TextField jardin;

    @FXML
    private TextField garage;

    @FXML
    private TextField piscine;

    @FXML
    private TextField nbretage;
    @FXML
    private TextField nature;

    @FXML
    private TextField descriprif;

    @FXML
    private ImageView image;
    //les labels
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;
    //les boutons

    @FXML
    private Button modifier;

    @FXML
    private Button supprimer;

    @FXML
    private Button archiver;
    //le setter
    void set(Agence agence,Bien bien){
        this.agence=agence;
        this.bien=bien;
    }

    //les controleurs des boutons
    public void supprimer(javafx.event.ActionEvent actionEvent) {
        try{
            int ind=(adresse.getText()+wilaya.getText()).hashCode();
            Iterator<Bien> i=(agence.getListe_biens()).iterator();
            Bien bien=i.next();
            while (((bien).hashCode()!=ind && i.hasNext())){bien=i.next();}
            if(bien.hashCode()==ind){ agence.suppr_bien(bien);}

            //comment mettre à jour
        }
        catch (Exception e){}

    }
    public void archiver(javafx.event.ActionEvent actionEvent) {
        try {
            int ind = (adresse.getText() + wilaya.getText()).hashCode();
            Iterator<Bien> i = (agence.getListe_biens()).iterator();
            Bien bien = i.next();
            while (((bien).hashCode() != ind && i.hasNext())) {
                bien = i.next();
            }
            if (bien.hashCode() == ind) {
                agence.archiver_bien(bien);
            }

            //comment mettre à jour
        } catch (Exception e) {
        }

    }
    void Affich_infos(){

        this.adresse.setText(bien.getAdresse());
        this.descriprif.setText(bien.getDescriptif());
        this.nature.setText(bien.getNat_transaction().name());
        this.date.setText(""+bien.getDate());
        this.prix.setText(""+bien.Calcul_prix(false));
        this.type.setText(bien.getClass().getSimpleName());
        this.prop.setText(bien.getProp().getNom()+"  "+bien.getProp().getPrenom());
        this.Superficie.setText(""+bien.getSuperficie());
        this.wilaya.setText(""+bien.getWilaya().name());
        if(bien instanceof Habitable){
            this.nbrpiece.setText(""+((Habitable) bien).getNbrs_pieces());
            if(((Habitable) bien).isMeuble()){this.meuble.setText("Disponible");}
            else {this.meuble.setText("Non disponible");}
            if(bien instanceof Maison){
                System.out.println("Maison");

                this.nbretage.setText(""+((Maison) bien).getEtage());
                this.suphabitable.setText(""+((Maison) bien).getSuperficie_habitable());
                if(((Maison) bien).isPicsine()){this.piscine.setText("Disponible");}
                else {this.piscine.setText("Non disponible");}

                if(((Maison) bien).isGarage()){this.garage.setText("Disponible");}
                else {this.garage.setText("Non disponible");}

                if(((Maison) bien).isJardin()){this.jardin.setText("Disponible");}
                else {this.jardin.setText("Non disponible");}
            }
            else {//appartement
                System.out.println("Appartement");
                grille.getChildren().removeAll(label6,label7,suphabitable,jardin);
                nbretage.setText(""+ ((Appartement)bien).getNum_etage());
                label4.setText("Simplexe /Duplexe.."); /* piscine.setText(((Appartement) bien).getSimp_dup());*/
                label5.setText("Ascenseur");
                if(((Appartement) bien).isAscenseur()){this.garage.setText("Disponible");}
                else {this.garage.setText("Non disponible");}

           }
        }
        else{
            System.out.println("Terrain");
            label1.setText("Status"); meuble.setText(((Terrain)bien).getStatu()+"9999");
            label2.setText("Nombre de façades");nbretage.setText(""+((Terrain)bien).getFaçade());
            grille.getChildren().removeAll(label3,label4,label5,label6,label7,nbrpiece,suphabitable,piscine,garage,jardin);
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        stage.close();
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
