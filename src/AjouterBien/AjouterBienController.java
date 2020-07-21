package AjouterBien;

import Noyau.*;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.util.Callback;
import listeBiens.AffichBienDetailController;
import sample.AjoutProp;

import javax.swing.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class AjouterBienController {
    //les infos
    private Agence agence;
    @FXML
    private Label warning;

    @FXML
    private AnchorPane tout;

    @FXML
    private TextField prixinit;

    @FXML
    private TextField adresse;

    @FXML
    private TextField superficie;

    @FXML
    private TextField descriptif;

    @FXML
    private Button suivant;

    @FXML
    private RadioButton nego;
    @FXML
    private MenuButton wilaya;
/*
        @FXML
        private RadioButton location;

        @FXML
        private RadioButton vente;

        @FXML
        private RadioButton echange;*/
    @FXML
    private RadioButton loc;

    @FXML
    private ToggleGroup g21;

    @FXML
    private RadioButton ven;

    @FXML
    private RadioButton ech;

    @FXML
    private RadioButton maison;

    @FXML
    private ToggleGroup g2;

    @FXML
    private RadioButton appartement;

    @FXML
    private RadioButton terrain;

    @FXML
    private TableColumn<Proprietaire, String> adrColumn;

    @FXML
    private TableView<Proprietaire> table;

    @FXML
    private TableColumn<Proprietaire, String> nom;

    @FXML
    private Label propException;

    private String wil="0";

    //setters

    public void setAgence(Agence agence) {
        this.agence = agence;
        table.getItems().addAll(agence.getListe_proprietaires());
    }

    public  void initialise(){
        Wilaya [] tab = Wilaya.values();
        wilaya.getItems().removeAll(wilaya.getItems());
        int ind=0;
        try{
            while ( tab[ind]!=null) {
                MenuItem item=new MenuItem(tab[ind].name());
                item.setOnAction(event -> {wilaya.setText(item.getText());wil=item.getText();}  );
                wilaya.getItems().add(ind,item);
                ind++;
            }
        }catch (Exception e){}
        prixinit.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")){
                    prixinit.setText(oldValue);}
            }
        });
        //********
        superficie.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")){
                    superficie.setText(oldValue);}
            }

        });

        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getNom()+"  "+param.getValue().getPrenom());
            }

        });

        adrColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getAdr());
            }

        });

    }
    //configurer les check boxes


    public void Suivant0(javafx.event.ActionEvent event) {
        System.out.println("1111");



        //  nego=new RadioButton("nego");
        double prix = 0;
        double sup = 0;
        boolean juste = true;
        if (descriptif.getText().isEmpty()) {
            descriptif.setPromptText("*Champs Obliguatoir");
            juste = false;
        }
        if(wil=="0"|| !(loc.isSelected()||ven.isSelected()||ech.isSelected())||!(maison.isSelected()||appartement.isSelected()||terrain.isSelected()))
        {juste=false;warning.setText("Remplissez tout les champs");}
            if (adresse.getText().isEmpty()) {
            adresse.setPromptText("*Champs Obliguatoir");
            juste = false;
        }
        try {
            prix = Double.parseDouble(prixinit.getText());
        } catch (Exception e) {
            juste = false;
            prixinit.setPromptText("Valeur numérique exigée");
        }
        try {
            sup = Double.parseDouble(superficie.getText());
        } catch (Exception e) {
            juste = false;
            superficie.setPromptText("Valeur numérique exigée");
        }
        try{
            if (table.getSelectionModel().getSelectedItem() == null) throw new EmptyInputException();
        }catch (EmptyInputException e){ e.Gerer(propException); juste=false;}

        if (juste) {//on recupere les champs
            Transaction transaction = Transaction.location;
            if(ven.isSelected()){transaction=Transaction.vente;}
            if(ech.isSelected()){transaction=Transaction.echange;}
            Wilaya w = Wilaya.valueOf(wil);

            //Maison
            if (maison.isSelected()) {//on crée une maison
                suivantMaisonController controlleur = null;
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../AjouterBien/suivantMaison.fxml"));
                    root = loader.load();
                    controlleur = loader.getController();
                    controlleur.setAgence(agence);
                    controlleur.setAll(nego.isSelected(), transaction, sup, prix, adresse.getText(), w, descriptif.getText(), table.getSelectionModel().getSelectedItem());
                    tout.getChildren().removeAll(tout.getChildren());
                    tout.getChildren().add(root);

                } catch (Exception e) {
                    System.out.println("Erreur");
                    e.printStackTrace();
                }


            }
            else
            if(terrain.isSelected()){
                suivantTerrainController controlleur = null;
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../AjouterBien/suivantTerrain.fxml"));
                    root = loader.load();
                    controlleur = loader.getController();
                    controlleur.setAgence(agence);
                    controlleur.setAll(nego.isSelected(), transaction, sup, prix, adresse.getText(), w, descriptif.getText(), table.getSelectionModel().getSelectedItem());
                    tout.getChildren().removeAll(tout.getChildren());
                    tout.getChildren().add(root);

                } catch (Exception e) {
                    System.out.println("Erreur");
                    e.printStackTrace();
                }
            }
            else
            if(appartement.isSelected()){
                suivantAppartementController controlleur = null;
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../AjouterBien/suivantAppartement.fxml"));
                    root = loader.load();
                    controlleur = loader.getController();
                    controlleur.setAgence(agence);
                    controlleur.setAll(nego.isSelected(), transaction, sup, prix, adresse.getText(), w, descriptif.getText(), table.getSelectionModel().getSelectedItem());
                    tout.getChildren().removeAll(tout.getChildren());
                    tout.getChildren().add(root);

                } catch (Exception e) {
                    System.out.println("Erreur");
                    e.printStackTrace();
                }
            }

        }
    }

    @FXML
    void Ajouterprop(ActionEvent event) {
        AjoutProp stage = new AjoutProp(agence, table);
        stage.show();
    }}

