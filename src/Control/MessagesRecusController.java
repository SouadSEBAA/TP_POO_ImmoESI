package Control;

import Noyau.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MessagesRecusController implements Initializable {

    private Agence agence;

    @FXML
    private TableView<String> table;

    @FXML
    private TableColumn<String, String> Email;

    @FXML
    private ListView<Pane> Messages;

    @FXML
    private Label msg;

    @FXML
    void Select(MouseEvent event) throws IOException {

        Collection<HashMap<String, ArrayList<String>>> l = agence.getMessages().values();
        ObservableList<Pane> li =FXCollections.observableArrayList();

        Iterator<HashMap<String, ArrayList<String>>> i = l.iterator();
        while (i.hasNext())
        {
            HashMap<String, ArrayList<String>> lis = i.next();
            if (lis.keySet().contains(table.getSelectionModel().getSelectedItem()))
            {
                ArrayList<String> var = lis.get(table.getSelectionModel().getSelectedItem());
                for(String s : var)
                {
                    FXMLLoader loader = new FXMLLoader();
                    Pane node;
                    loader.setLocation(getClass().getResource("../sample/msgCell.fxml"));
                    node = loader.load();
                    msgCellController controller = loader.getController();
                    controller.setMsg(s);
                    li.add(node);
                }

            }
        }

        Messages.getItems().addAll(li) ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Email.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                return new SimpleStringProperty(param.getValue());
            }
        });

    }

    public void setAgence(Agence agence) {
        this.agence = agence;
        Collection<HashMap<String, ArrayList<String>>> l = agence.getMessages().values();
        Iterator<HashMap<String, ArrayList<String>>> i = l.iterator();
        while (i.hasNext())
        {
            table.getItems().setAll(i.next().keySet());
        }
    }
}
