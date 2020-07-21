package Control;

import Noyau.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ListeProprietairesController implements Initializable {

    private Agence agence;
    private SubScene cote;

    @FXML
    private TableView<Proprietaire> table ;

    @FXML
    private TableColumn<Proprietaire, String> nom;

    @FXML
    private TableColumn<Proprietaire, Number> num;

    @FXML
    private TableColumn<Proprietaire, String> mail;


    @FXML
    void Select(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2){
            FXMLLoader loader = new FXMLLoader();
            Parent node;
            loader.setLocation(getClass().getResource("../sample/AfficheProprietaireDetails.fxml"));
            node = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(node);
            stage.setScene(scene);

            AfficheProprietaireDetailsController controller = loader.getController();
            controller.setAgence(this.agence);
            controller.setInfo(table.getSelectionModel().getSelectedItem());
            controller.setStage(stage);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getNom()+"  "+param.getValue().getPrenom());
            }

        });

        num.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, Number>, ObservableValue<Number>>() {
            @Override
            public ObservableValue<Number> call(TableColumn.CellDataFeatures<Proprietaire, Number> param) {
                return new SimpleLongProperty(param.getValue().getNum_tel());
            }
        });

        mail.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Proprietaire, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Proprietaire, String> param) {
                return new SimpleStringProperty(param.getValue().getAdr_mail());
            }
        });
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
        table.getItems().addAll(agence.getListe_proprietaires());
    }

    public void setCote(SubScene cote) {
        this.cote = cote;
    }
}
