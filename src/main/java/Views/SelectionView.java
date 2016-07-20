package Views;

import ScreenSelection.ScreenSelection;
import ScreenSelection.SelectionInformationContainer;
import javafx.event.ActionEvent;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Indi on 7/15/2016.
 */
public class SelectionView extends SuperView {
    private SelectionInformationContainer RefillAScreen;
    private SelectionInformationContainer RefillBScreen;
    private SelectionInformationContainer RefillCScreen;
    private SelectionInformationContainer RefillDScreen;
    private SelectionInformationContainer RefillEScreen;

    public SelectionView(){
        File[] dirs = {new File("C://SummonersWarFarmer"),
            new File("C://SummonersWarFarmer//SecretDungeon"),
            new File("C://SummonersWarFarmer//DungeonFarm"),
            new File("C://SummonersWarFarmer//ScenarioFarm")
        };
        // if the directories do not exist, create them
        for (File dir : dirs) {
            if (!dir.exists()) {
                try {
                    dir.mkdir();
                } catch (SecurityException se) {
                    System.out.println("no permissions");
                }
            }
        }
    }


    public void testFunction(ActionEvent actionEvent) {

    }
    //
    public void saveRefillLocations(ActionEvent actionEvent) throws IOException {
            saveData("C://SummonersWarFarmer//RefillAScreen",RefillAScreen);
            saveData("C://SummonersWarFarmer//RefillBScreen",RefillBScreen);
            saveData("C://SummonersWarFarmer//RefillCScreen",RefillCScreen);
            saveData("C://SummonersWarFarmer//RefillDScreen",RefillDScreen);
            saveData("C://SummonersWarFarmer//RefillEScreen",RefillEScreen);
    }
    public void saveData(String filename, SelectionInformationContainer data) throws IOException {
        FileOutputStream myFileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
        myObjectOutputStream.writeObject(data);
        myObjectOutputStream.close();
    }
    public void setRefillStep5(ActionEvent actionEvent) {
        RefillEScreen = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(RefillEScreen);
        SS.startSelectionCanvas();
    }

    public void setRefillStep4(ActionEvent actionEvent) {
        RefillDScreen = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(RefillDScreen);
        SS.startSelectionCanvas();
    }
    public void setRefillStep3(ActionEvent actionEvent) {
        RefillCScreen = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(RefillCScreen);
        SS.startSelectionCanvas();

    }
    public void setRefillStep2(ActionEvent actionEvent) {
        RefillBScreen = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(RefillBScreen);
        SS.startSelectionCanvas();
    }

    public void setRefillStep1(ActionEvent actionEvent) {
        RefillAScreen = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(RefillAScreen);
        SS.startSelectionCanvas();

    }
    public void setUpSecretDungeonLogic(ActionEvent actionEvent) {
        FXController.Controller logout = new FXController.Controller();
        logout.setMainWindow("SecretDungeon", "/FXML/App/SecretDungeonWindow.fxml");
    }
    public void setUpScenarioFarmingLogic(ActionEvent actionEvent) {
        FXController.Controller logout = new FXController.Controller();
        logout.setMainWindow("Scenario", "/FXML/App/ScenarioWindow.fxml");
    }

    public void setUpDungeonFarmingLogic(ActionEvent actionEvent) {
        FXController.Controller logout = new FXController.Controller();
        logout.setMainWindow("Dungeon", "/FXML/App/DungeonWindow.fxml");
    }
}
