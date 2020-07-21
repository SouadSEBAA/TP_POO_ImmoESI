package Control;

import Noyau.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.AjoutProp;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

public class ModifierBienController implements Initializable {
    private Agence agence;
    private Bien bien ;
    private Stage stage;

    private HashMap<String, Object> parametres = new HashMap<>();

    @FXML
    private ChoiceBox<Transaction> TypeTransaction;

    @FXML
    private TextArea adr;

    @FXML
    private ChoiceBox<Wilaya> Wilayas;

    @FXML
    private TextField prix;

    @FXML
    private TextField superficie;

    @FXML
    private TextField superficie_hab;

    @FXML
    private TextArea descriptif;

    @FXML
    private TableView<Proprietaire> PropList;

    @FXML
    private TableColumn<Proprietaire, String> Nom;

    @FXML
    private TableColumn<Proprietaire, String> Adresse;

    @FXML
    private Pane infoMaison;

    @FXML
    private TextField etage;

    @FXML
    private CheckBox piscine;

    @FXML
    private CheckBox garage;

    @FXML
    private CheckBox jardin;

    @FXML
    private Pane infoTerrain;

    @FXML
    private TextField nb_facades;

    @FXML
    private TextArea statut;

    @FXML
    private Pane infoHabitable;

    @FXML
    private TextField nb_pcs;

    @FXML
    private RadioButton meuble;

    @FXML
    private Pane infoAppart;

    @FXML
    private CheckBox asc;

    @FXML
    private CheckBox duplexe;

    @FXML
    private TextField num_etage;

    @FXML
    private RadioButton nego;

    @FXML
    private Label super_hab;

    @FXML
    private Label adrEmptyException;

    @FXML
    private Label prixException;

    @FXML
    private Label superException;

    @FXML
    private Label superHabException;

    @FXML
    private Label descriptifExeption;

    @FXML
    private Label nbfcdException;

    @FXML
    private Label statutException;

    @FXML
    private Label nbpcsException;

    @FXML
    private Label numetagesException;

    @FXML
    private Label nbetagesException;

    @FXML
    void Annuler(ActionEvent event) {
        stage.close();
    }

    @FXML
    public void PressButtonModifier(ActionEvent event){
        boolean bool = false;
        ArrayList<Field> l = bien.recuperer_attributs();

        for (Field attribute : l) {
            String s = attribute.getName();
            switch (s){
                case "adresse" :
                        try {
                            if (adr.getText().isEmpty()) throw new EmptyInputException();
                            parametres.put(s, adr.getText());
                        }catch (EmptyInputException e) {e.Gerer(adrEmptyException); bool= true;}break;

                case "wilaya" : parametres.put(s, Wilayas.getValue());break;

                case "prix" :
                    try{
                        if (prix.getText().isEmpty()) throw new EmptyInputException();
                        try{Double.parseDouble(prix.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Double.parseDouble(prix.getText()));
                    }catch (EmptyInputException e){e.Gerer(prixException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(prixException); bool= true;}break;

                case "superficie" :
                    try{
                        if (superficie.getText().isEmpty()) throw new EmptyInputException();
                        try{Double.parseDouble(superficie.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Double.parseDouble(superficie.getText()));
                    }catch (EmptyInputException e){e.Gerer(superException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(superException); bool= true;}break;

                case "descriptif" :
                    try {
                        if (descriptif.getText().isEmpty()) throw new EmptyInputException();
                        parametres.put(s, descriptif.getText());
                    }catch (EmptyInputException e) {e.Gerer(descriptifExeption); bool= true;}break;

                case "nat_transaction" : parametres.put(s, TypeTransaction.getValue());break;

                case "prix_nego" : parametres.put(s, nego.isSelected());break;

                case "prop" : parametres.put(s, PropList.getSelectionModel().getSelectedItem());break;

                //case "photos" : parametres.put(s, );break;

                case "nbrs_pieces" :
                    try{
                        if (nb_pcs.getText().isEmpty()) throw new EmptyInputException();
                        try{Integer.parseInt(nb_pcs.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Integer.parseInt(nb_pcs.getText()));
                    }catch (EmptyInputException e){e.Gerer(nbpcsException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(nbpcsException); bool= true;}break;

                case "meuble" : parametres.put(s, meuble.isSelected());break;

                case "etage" :
                    try{
                        if (etage.getText().isEmpty()) throw new EmptyInputException();
                        try{Integer.parseInt(etage.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Integer.parseInt(etage.getText()));
                    }catch (EmptyInputException e){e.Gerer(nbetagesException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(nbetagesException); bool= true;}break;

                case "num_etage" :
                    try{
                        if (num_etage.getText().isEmpty()) throw new EmptyInputException();
                        try{Integer.parseInt(num_etage.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Integer.parseInt(num_etage.getText()));
                    }catch (EmptyInputException e){e.Gerer(numetagesException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(numetagesException); bool= true;}break;

                case "simp_dup" : parametres.put(s, !duplexe.isSelected());break;
                case "ascenseur" : parametres.put(s, asc.isSelected());break;
                case "picsine" : parametres.put(s, piscine.isSelected());break;
                case "garage" : parametres.put(s, garage.isSelected());break;
                case "superficie_habitable" :
                    try{
                        if (superficie_hab.getText().isEmpty()) throw new EmptyInputException();
                        try{Double.parseDouble(superficie_hab.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Double.parseDouble(superficie_hab.getText()));
                    }catch (EmptyInputException e){e.Gerer(superHabException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(superHabException); bool= true;}break;

                case "jardin" : parametres.put(s, jardin.isSelected());break;
                case "statu" :
                    try {
                        if (statut.getText().isEmpty()) throw new EmptyInputException();
                        parametres.put(s, statut.getText());
                    }catch (EmptyInputException e) {e.Gerer(statutException); bool= true;}break;

                case "façade" :
                    try{
                        if (nb_facades.getText().isEmpty()) throw new EmptyInputException();
                        try{Integer.parseInt(nb_facades.getText());} catch (Exception e){throw new MismatchInputexception();}
                        parametres.put(s, Integer.parseInt(nb_facades.getText()));
                    }catch (EmptyInputException e){e.Gerer(nbfcdException); bool= true;}
                    catch (MismatchInputexception e) {e.Gerer(nbfcdException); bool= true;}break;
        }
    }
        if (!bool) {
            agence.modifier_bien(bien, parametres);
            stage.close();
        }
    }

    @FXML
    void PressButtonAjouter(ActionEvent event) {
        AjoutProp stage = new AjoutProp(agence, PropList);
        stage.show();
    }

    @FXML
    void PressButtonAnnuler(ActionEvent event) {
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        TypeTransaction.getItems().addAll(Arrays.asList(Transaction.values()));

        Wilayas.getItems().addAll(Arrays.asList(Wilaya.values()));

        Nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getNom()+"  "+param.getValue().getPrenom());
            }

        });

        Adresse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getAdr());
            }

        });

        superficie_hab.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}([\\.]\\d{0,})?")) {
                    superficie_hab.setText(oldValue);
                }
            }
        });

        superficie.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}([\\.]\\d{0,})?")) {
                    superficie.setText(oldValue);
                }
            }
        });

        nb_facades.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}?")) {
                    nb_facades.setText(oldValue);
                }
            }
        });

        nb_pcs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}?")) {
                    nb_pcs.setText(oldValue);
                }
            }
        });

        num_etage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}?")) {
                    num_etage.setText(oldValue);
                }
            }
        });

        etage.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}?")) {
                    etage.setText(oldValue);
                }
            }
        });

        /*prix.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}([E .]?\\d{0,})?")) {
                    prix.setText(oldValue);
                }
            }
        });*/

    }

    public void setBien(Bien bien) {
        this.bien = bien;

        TypeTransaction.setValue(bien.getNat_transaction());

        Wilayas.setValue(bien.getWilaya());

        adr.setText(bien.getAdresse());

        prix.setText(String.valueOf(bien.getPrix()));
        System.out.println(bien.getPrix());

        superficie.setText(String.valueOf(bien.getSuperficie()));

        descriptif.setText(bien.getDescriptif());

        nego.setSelected(bien.isPrix_nego());

        PropList.getSelectionModel().select(bien.getProp());

        if (bien instanceof Habitable)
        {
            infoHabitable.setDisable(false);
            nb_pcs.setText(String.valueOf(((Habitable) bien).getNbrs_pieces()));
            meuble.setSelected(((Habitable) bien).isMeuble());
            if (bien instanceof Maison)
            {
                infoMaison.setDisable(false);
                etage.setText(String.valueOf(((Maison) bien).getEtage()));
                jardin.setSelected(((Maison) bien).isJardin());
                garage.setSelected(((Maison) bien).isGarage());
                piscine.setSelected(((Maison) bien).isGarage());
                super_hab.setDisable(false);
                superficie_hab.setDisable(false);
                superficie_hab.setText(String.valueOf(((Maison) bien).getSuperficie_habitable()));
            }
            else if (bien instanceof Appartement)
            {
                infoAppart.setDisable(false);
                num_etage.setText(String.valueOf(((Appartement) bien).getNum_etage()));
                duplexe.setSelected(((Appartement) bien).getSimp_dup());
                asc.setSelected(((Appartement) bien).getAscenseur());
            }
        }else{
            infoTerrain.setDisable(false);
            nb_facades.setText(String.valueOf(((Terrain) bien).getFaçade()));
            statut.setText(((Terrain) bien).getStatu());
        }

    }

    public void setAgence(Agence agence) {
        this.agence = agence;

        PropList.getItems().addAll(agence.getListe_proprietaires());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
