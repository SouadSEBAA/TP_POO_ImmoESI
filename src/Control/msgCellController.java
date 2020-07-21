package Control;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class msgCellController {

    @FXML
    private Label msg;

    public void setMsg(String msg) {
        this.msg.setText(msg);
    }
}
