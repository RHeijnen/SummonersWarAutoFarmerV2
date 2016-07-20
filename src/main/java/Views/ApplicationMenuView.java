package Views;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class ApplicationMenuView extends SuperView {
    public Label scene_DescriptionLabel;

    public void setConfig(ActionEvent actionEvent) {
        FXController.Controller logout = new FXController.Controller();
        logout.setMainWindow("Home", "/FXML/App/SelectionWindow.fxml");

    }





}
