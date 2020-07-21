package Noyau;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class EmptyInputException extends InputException {
    @Override
    public void Gerer(Label text) {
        text.setText("*Champ Obligatoire");
        text.setTextFill(Color.RED);
    }
}
