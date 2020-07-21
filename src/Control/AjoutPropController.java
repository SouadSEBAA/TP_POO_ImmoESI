package Control;

import Noyau.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import sample.AjoutProp;

public class AjoutPropController {

    private AjoutProp stage;
    private Agence agence;
    private TableView<Proprietaire> table;

    @FXML
    private TextArea Nom;

    @FXML
    private Label nomException;

    @FXML
    private TextArea Prenom;

    @FXML
    private Label prenomException;

    @FXML
    private TextArea Adresse;

    @FXML
    private TextArea Num;

    @FXML
    private Label adrException;

    @FXML
    private TextArea Email;

    @FXML
    private Label emailException;

    @FXML
    private Label numException;
    Proprietaire prop;

    @FXML
    void PressButtonAjouter(ActionEvent event) {
        boolean bool = false;
        try {
            if (Email.getText().isEmpty()) throw new EmptyInputException();
        }catch (EmptyInputException e) {e.Gerer(emailException); bool=true;}
        try {
            if (Nom.getText().isEmpty()) throw new EmptyInputException();
        }catch (EmptyInputException e) {e.Gerer(nomException); bool=true;}
        try {
            if (Prenom.getText().isEmpty()) throw new EmptyInputException();
        }catch (EmptyInputException e) {e.Gerer(prenomException); bool=true;}
        try {
            if (Adresse.getText().isEmpty()) throw new EmptyInputException();
        }catch (EmptyInputException e) {e.Gerer(adrException); bool=true;}
        try{
            if (Num.getText().isEmpty()) throw new EmptyInputException();
            try{Long.parseLong(Num.getText()); }
            catch (Exception e){throw new MismatchInputexception();}
        }catch (EmptyInputException e){e.Gerer(numException); bool= true;}
        catch (MismatchInputexception e) {e.Gerer(numException); bool=true;}
        if (!bool){
        Proprietaire prop = new Proprietaire(Nom.getText(), Prenom.getText(), Email.getText(), Adresse.getText(), Long.parseLong(Num.getText()));
        if (!agence.getListe_proprietaires().contains(prop))
            table.getItems().add(prop);
        agence.Ajouter_prop(prop);
        stage.close();
        this.prop=prop;
        }
    }

    @FXML
    void PressButtonAnnuler(ActionEvent event) {
        stage.close();
    }

    public void setStage(AjoutProp stage) {
        this.stage = stage;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
        Num.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,}?")) {
                    Num.setText(oldValue);
                }
            }
        });
    }

    public void setTable(TableView<Proprietaire> table) {
        this.table = table;
    }
}
