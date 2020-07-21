package Control;

import Noyau.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import listeBiens.listeBienController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class AfficheProprietaireDetailsController {

    private Agence agence;

    @FXML
    private Label nom_prenom;

    @FXML
    private ImageView avatar;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private RowConstraints grid;

    @FXML
    private Label mail;

    @FXML
    private Label tel;

    @FXML
    private Label adresse;

    @FXML
    private SubScene listeBien;

    private Stage stage;

    public void setInfo(Proprietaire p) throws IOException {
        this.nom.setText(p.getNom());
        prenom.setText(p.getPrenom());
        mail.setText(p.getAdr_mail());
        tel.setText(String.valueOf(p.getNum_tel()));
        adresse.setText(p.getAdr());
        nom_prenom.setText(p.getNom()+" "+p.getPrenom());
        ArrayList<Bien> l = new ArrayList<>(p.getListe_biens());
        Collections.sort(l, new BienDateComparator());

        //Afficher listeBien
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
        Parent node = loader.load();

        listeBienController controller = loader.getController();
        controller.setAgence(this.agence);
        controller.ajouter_biens(p.getListe_biens(),true);
        node.resize(listeBien.getWidth(),listeBien.getHeight());
        //controller.setList(listeBien.getWidth(),listeBien.getHeight());

        listeBien.setRoot(node);
    }

    @FXML
    void Retour(ActionEvent event) {
        stage.close();
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
