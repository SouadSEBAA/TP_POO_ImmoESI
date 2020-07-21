package Noyau;

import Noyau.InputException;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MismatchInputexception extends InputException {
    @Override
    public void Gerer(Label text) {
        text.setText("*Vérifier votre syntaxe");
        text.setTextFill(Color.RED);
    }
}
