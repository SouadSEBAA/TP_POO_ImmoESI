package Control;

import Noyau.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import listeBiens.listeBienController;
;import java.net.URL;
import java.util.*;

public class RechercheMulticriteresController implements Initializable {

    private Agence agence;
    private boolean admin;
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @FXML
    private ScrollPane tout;

    @FXML
    private CheckBox Maison;

    @FXML
    private CheckBox Appartement;

    @FXML
    private CheckBox Terrain;

    @FXML
    private CheckBox Vente;

    @FXML
    private CheckBox Location;

    @FXML
    private CheckBox Echange;

    @FXML
    private TextField Prix_min;

    @FXML
    private TextField Prix_max;

    @FXML
    private TextField Superficie_min;

    @FXML
    private TextField Superficie_max;

    @FXML
    private Button Filtrer;

    @FXML
    private TitledPane nb_pcs_min;

    @FXML
    private TextField nb_pcs;

    @FXML
    private ListView<Wilaya> Wilayas;

    private  Map<Wilaya, ObservableValue<Boolean>> wilayas = new HashMap<>();
    //**********************************************************************************//

    @FXML
    void HabitableSelect(ActionEvent event) {
        if (Maison.isSelected() || Appartement.isSelected())
            nb_pcs_min.setDisable(false);
        else
            nb_pcs_min.setDisable(true);
    }
    //**********************************************************************************//
    @FXML
    void PressFiltrer(ActionEvent event) {
        HashMap<Criteres, HashSet<Object>> parametres = new HashMap<>();
        if (Maison.isSelected() || Terrain.isSelected() || Appartement.isSelected())
         parametres.put(Criteres.Type_du_bien, new HashSet<Object>(){{
             if(Terrain.isSelected()) add("Terrain");
             if(Appartement.isSelected()) add("Appartement");
             if(Maison.isSelected()) add("Maison");
         }});

        if (Vente.isSelected() || Echange.isSelected() || Location.isSelected())
            parametres.put(Criteres.Transaction, new HashSet<Object>(){{
                if(Vente.isSelected()) add(Transaction.vente);
                if(Echange.isSelected()) add(Transaction.echange);
                if(Location.isSelected()) add(Transaction.location);
            }});

        if (!Superficie_min.getText().isEmpty())
            parametres.put(Criteres.Superficie_min, new HashSet<Object>(){{ add(Double.parseDouble(Superficie_min.getText())); }});

        if (!Superficie_max.getText().isEmpty())
            parametres.put(Criteres.Superficie_max, new HashSet<Object>(){{ add(Double.parseDouble(Superficie_max.getText())); }});

        if (!Prix_max.getText().isEmpty())
            parametres.put(Criteres.Prix_max, new HashSet<Object>(){{ add(Double.parseDouble(Prix_max.getText())); }});

        if (!Prix_min.getText().isEmpty())
            parametres.put(Criteres.Prix_min, new HashSet<Object>(){{ add(Double.parseDouble(Prix_min.getText())); }});

        if (!nb_pcs.getText().isEmpty())
            parametres.put(Criteres.nb_min_pieces, new HashSet<Object>(){{ add(Integer.parseInt(nb_pcs.getText())); }});


        parametres.put(Criteres.Wilaya, new HashSet<Object>(){{
            for (Wilaya w: wilayas.keySet()) {
                if (wilayas.get(w).getValue())
                    add(w);}
                }});

        //***Lqffichqge
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../listeBiens/listeBien.fxml"));
        Parent node=null;
        try {
            node = loader.load();
        }catch (Exception e){e.printStackTrace();}
        listeBienController controller = loader.getController();
        if (agence == null) {
            System.out.println("nullll");
        }

        controller.setAgence(this.agence);
        controller.ajouter_biens(agence.Filtrer(parametres),admin);
        tout.setContent(node);    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Wilayas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Wilayas.setCellFactory((ListView<Wilaya> l) -> {
            CheckBoxListCell<Wilaya> result = new CheckBoxListCell<Wilaya>();
            result.setPrefSize(70, 30);
            result.setStyle("-fx-font-size: large; -fx-background-color:  #DBDADB;");

            Wilaya [] tab = Wilaya.values();
            for (Wilaya w: tab) {
                BooleanProperty prop = new SimpleBooleanProperty();
                prop.setValue(false);
                wilayas.put(w, prop);
                prop.addListener((ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) -> {
                    prop.setValue(newValue);
                    wilayas.put(w, prop);
                });
            }

            Callback<Wilaya, ObservableValue<Boolean>> callback = (Wilaya w) -> wilayas.get(w);
            result.setSelectedStateCallback(callback);

            result.setConverter(new StringConverter<Wilaya>() {
                @Override
                public String toString(Wilaya object) {
                    return object.name();
                }

                @Override
                public Wilaya fromString(String string) {
                    return null;
                }
            });

            return result;
        });
        Wilayas.getItems().setAll(Wilaya.values());
    }
    //**********************************************************************************//
    public void setAgence(Agence agence) {
        this.agence = agence;
    }

}
