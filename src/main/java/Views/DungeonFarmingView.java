package Views;

import ScreenSelection.SelectionInformationContainer;
import Templates.DungeonFarmingLogic;
import Templates.ScenarioFarmingLogic;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ScreenSelection.*;

import java.awt.*;
import java.io.*;
import java.util.Timer;

/**
 * Created by Indi on 7/17/2016.
 */
public class DungeonFarmingView extends SuperView {
    public TextArea scene_WinCounter;
    public TextArea scene_TextAreaOutput;
    public TextField scene_InputRefill;
    public CheckBox scene_IncludeRefill;
    private SelectionInformationContainer victoryScreenData;
    private SelectionInformationContainer okButtonData;
    private SelectionInformationContainer getsellButtonData;

    private SelectionInformationContainer replayButtonData;
    private SelectionInformationContainer startBattleButtonData;
    //refill content
    private SelectionInformationContainer RefillAScreen;
    private SelectionInformationContainer RefillBScreen;
    private SelectionInformationContainer RefillCScreen;
    private SelectionInformationContainer RefillDScreen;
    private SelectionInformationContainer RefillEScreen;

    int refillCount = 0;
    // offset the timer for timerTask update pixels  in containers ^
    int timerOffsetParameter = 100;
    public boolean started = false;

    Timer timer;
    public DungeonFarmingView(){
        timer = new Timer();

    }
    // select DATA
    public void selectStartBattleScreen(ActionEvent actionEvent) {
        startBattleButtonData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(startBattleButtonData);
        SS.startSelectionCanvas();
    }

    public void selectOkScreen(ActionEvent actionEvent) {
        okButtonData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(okButtonData);
        SS.startSelectionCanvas();
    }
    public void selectReplayScreen(ActionEvent actionEvent) {
        replayButtonData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(replayButtonData);
        SS.startSelectionCanvas();
    }

    public void selectVictoryScreen(ActionEvent actionEvent) {
        victoryScreenData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(victoryScreenData);
        SS.startSelectionCanvas();
    }
    public void selectSellGetScreen(ActionEvent actionEvent) {
        getsellButtonData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(getsellButtonData);
        SS.startSelectionCanvas();
    }
    // START automation
    public void stopSecretDungeonFarm(ActionEvent actionEvent) {
        setConsoleOutput("Bot paused");
        started = false;
        ScenarioFarmingLogic.active = false;

    }

    public void startSecretDungeonFarm(ActionEvent actionEvent)throws AWTException {
        if(scene_IncludeRefill.isSelected()){
            if(!started){
                refillCount = Integer.parseInt(scene_InputRefill.getText());
                startMonitoring();
                new Thread() {
                    public void run() { // ADD REFILL LIMIT
                        timer.schedule(new DungeonFarmingLogic(getsellButtonData,victoryScreenData,okButtonData,replayButtonData,startBattleButtonData,scene_WinCounter,10,refillCount,RefillAScreen,RefillBScreen,RefillCScreen,RefillDScreen,RefillEScreen), 0, 1000);
                    }
                }.start();
                setConsoleOutput("Bot logic started with "+refillCount+" refills");
                started = true;
                DungeonFarmingLogic.active = true;
            }else{
                setConsoleOutput("Bot logic resumed");

            }
        }else{ // without refills
            if(!started){
                startMonitoring();
                new Thread() {
                    public void run() {
                        timer.schedule(new DungeonFarmingLogic(getsellButtonData,victoryScreenData,okButtonData,replayButtonData,startBattleButtonData,scene_WinCounter,10), 0, 1000);
                    }
                }.start();
                setConsoleOutput("Bot logic started");
                started = true;
                DungeonFarmingLogic.active = true;
            }else{
                setConsoleOutput("Bot logic resumed");

            }
        }


    }

    public void startMonitoring() throws AWTException {
        SelectionMonitor victoryMonitor = new SelectionMonitor(victoryScreenData,timerOffsetParameter);
        victoryMonitor.createVisibleMonitor();
        SelectionMonitor okMonitor = new SelectionMonitor(okButtonData,timerOffsetParameter);
        okMonitor.createVisibleMonitor();
        SelectionMonitor startMonitor = new SelectionMonitor(startBattleButtonData,timerOffsetParameter);
        startMonitor.createVisibleMonitor();
        SelectionMonitor replayMonitor = new SelectionMonitor(replayButtonData,timerOffsetParameter);
        replayMonitor.createVisibleMonitor();
        SelectionMonitor lootMonitor = new SelectionMonitor(getsellButtonData,timerOffsetParameter);
        lootMonitor.createVisibleMonitor();

        // if refill selected
        if(scene_IncludeRefill.isSelected()) {
            SelectionMonitor refillAMonitor = new SelectionMonitor(RefillAScreen,timerOffsetParameter);
            refillAMonitor.createMonitor();
            SelectionMonitor refillBMonitor = new SelectionMonitor(RefillBScreen,timerOffsetParameter);
            refillBMonitor.createMonitor();
            SelectionMonitor refillCMonitor = new SelectionMonitor(RefillCScreen,timerOffsetParameter);
            refillCMonitor.createMonitor();
            SelectionMonitor refillDMonitor = new SelectionMonitor(RefillDScreen,timerOffsetParameter);
            refillDMonitor.createMonitor();
            SelectionMonitor refillEMonitor = new SelectionMonitor(RefillEScreen,timerOffsetParameter);
            refillEMonitor.createMonitor();
        }
    }

    // SAVE LOAD functions
    public void saveSetup(ActionEvent actionEvent) throws IOException {
        saveData("C://SummonersWarFarmer//DungeonFarm//SCENE-victory",victoryScreenData);
        saveData("C://SummonersWarFarmer//DungeonFarm//SCENE-ok",okButtonData);
        saveData("C://SummonersWarFarmer//DungeonFarm//SCENE-replay",replayButtonData);
        saveData("C://SummonersWarFarmer//DungeonFarm//SCENE-battlestart",startBattleButtonData);
        saveData("C://SummonersWarFarmer//DungeonFarm//SCENE-loot",getsellButtonData);

    }


    public void loadSetup(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        loadData(1);
        loadData(2);
        loadData(3);
        loadData(4);
        loadData(5);

        if(scene_IncludeRefill.isSelected()){
            loadData(6);
            loadData(7);
            loadData(8);
            loadData(9);
            loadData(10);

        }
    }
    public void loadData(int type) throws IOException, ClassNotFoundException {
        FileInputStream myFileInputStream = null;
        ObjectInputStream myObjectInputStream = null;
        switch(type){
            case 1:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//DungeonFarm//SCENE-ok");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                okButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 2:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//DungeonFarm//SCENE-victory");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                victoryScreenData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 3:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//DungeonFarm//SCENE-replay");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                replayButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 4:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//DungeonFarm//SCENE-battlestart");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                startBattleButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 5:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//DungeonFarm//SCENE-loot");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                getsellButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 6:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillAScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillAScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 7:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillBScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillBScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 8:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillCScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillCScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 9:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillDScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillDScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 10:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillEScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillEScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
        }
    }
    public void saveData(String filename,SelectionInformationContainer data) throws IOException {
        FileOutputStream myFileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
        myObjectOutputStream.writeObject(data);
        myObjectOutputStream.close();
    }
    // console output
    public void setConsoleOutput(String content){
        scene_TextAreaOutput.appendText(content);
        scene_TextAreaOutput.appendText("\n\r");

    }
    public void clearConsoleOutput(){
        scene_TextAreaOutput.clear();
    }
}
