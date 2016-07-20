package Views;

import Templates.SecretDungeonLogic;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import ScreenSelection.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.*;
import java.util.Timer;

/**
 * Created by Indi on 7/16/2016.
 */
public class SecretDungeonView extends SuperView  {
    public TextArea scene_WinCounter;
    public TextArea scene_TextAreaOutput;
    public TextField scene_InputRefill;
    public CheckBox scene_IncludeRefill;
    private SelectionInformationContainer victoryScreenData;
    private SelectionInformationContainer okButtonData;
    private SelectionInformationContainer replayButtonData;
    private SelectionInformationContainer startBattleButtonData;
    //refill content
    private SelectionInformationContainer RefillAScreen;
    private SelectionInformationContainer RefillBScreen;
    private SelectionInformationContainer RefillCScreen;
    private SelectionInformationContainer RefillDScreen;
    private SelectionInformationContainer RefillEScreen;

    int refillCount = 0;
    // offse the timer for timerTask update pixels  in containers ^
    int timerOffsetParameter = 100;
    public boolean started = false;

    Timer timer;
    public SecretDungeonView(){
        timer = new Timer();

    }
    // Start and pause secret dungeon timertask
    // the TimerTask controls the bot logic
    public void startSecretDungeonFarm(ActionEvent actionEvent) throws AWTException {
        if(scene_IncludeRefill.isSelected()){
            if(!started){
                refillCount = Integer.parseInt(scene_InputRefill.getText());
                startMonitoring();
                new Thread() {
                    public void run() { // ADD REFILL LIMIT
                        timer.schedule(new SecretDungeonLogic(victoryScreenData,okButtonData,replayButtonData,startBattleButtonData,scene_WinCounter,10,refillCount,RefillAScreen,RefillBScreen,RefillCScreen,RefillDScreen,RefillEScreen), 0, 1000);
                    }
                }.start();
                setConsoleOutput("Bot logic started with "+refillCount+" refills");
                started = true;
                SecretDungeonLogic.active = true;
            }else{
                setConsoleOutput("Bot logic resumed");

            }
        }else{ // without refills
            if(!started){
                startMonitoring();
                new Thread() {
                    public void run() {
                        timer.schedule(new SecretDungeonLogic(victoryScreenData,okButtonData,replayButtonData,startBattleButtonData,scene_WinCounter,10), 0, 1000);
                    }
                }.start();
                setConsoleOutput("Bot logic started");
                started = true;
                SecretDungeonLogic.active = true;
            }else{
                setConsoleOutput("Bot logic resumed");

            }
        }


    }

    public void stopSecretDungeonFarm(ActionEvent actionEvent) {
        setConsoleOutput("Bot paused");
        started = false;
        SecretDungeonLogic.active = false;

    }
    // Save and Load the selected areas
    public void saveSetup(ActionEvent actionEvent) throws IOException {
        saveData("C://SummonersWarFarmer//SecretDungeon//SD-victory",victoryScreenData);
        saveData("C://SummonersWarFarmer//SecretDungeon//SD-ok",okButtonData);
        saveData("C://SummonersWarFarmer//SecretDungeon//SD-replay",replayButtonData);
        saveData("C://SummonersWarFarmer//SecretDungeon//SD-battlestart",startBattleButtonData);

    }

    public void loadSetup(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        loadData(1);
        loadData(2);
        loadData(3);
        loadData(4);

        if(scene_IncludeRefill.isSelected()){
             loadData(5);
             loadData(6);
             loadData(7);
             loadData(8);
             loadData(9);

        }
    }

    // Get rectangle / screen information
    public void selectVictoryScreen(ActionEvent actionEvent) {
        victoryScreenData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(victoryScreenData);
        SS.startSelectionCanvas();
    }

    public void startMonitoring() throws AWTException {
        SelectionMonitor victoryMonitor = new SelectionMonitor(victoryScreenData,timerOffsetParameter);
        victoryMonitor.createMonitor();
        SelectionMonitor okMonitor = new SelectionMonitor(okButtonData,timerOffsetParameter);
        okMonitor.createMonitor();
        SelectionMonitor startMonitor = new SelectionMonitor(startBattleButtonData,timerOffsetParameter);
        startMonitor.createMonitor();
        SelectionMonitor replayMonitor = new SelectionMonitor(replayButtonData,timerOffsetParameter);
        replayMonitor.createMonitor();

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





    public void selectReplayScreen(ActionEvent actionEvent) {
        replayButtonData = new SelectionInformationContainer();
        ScreenSelection SS = new ScreenSelection(replayButtonData);
        SS.startSelectionCanvas();
    }



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




    // save load functions detailed

    public void saveData(String filename, SelectionInformationContainer data) throws IOException {
        FileOutputStream myFileOutputStream = new FileOutputStream(filename);
        ObjectOutputStream myObjectOutputStream = new ObjectOutputStream(myFileOutputStream);
        myObjectOutputStream.writeObject(data);
        myObjectOutputStream.close();
    }
    public void loadData(int type) throws IOException, ClassNotFoundException {
        FileInputStream myFileInputStream = null;
        ObjectInputStream myObjectInputStream = null;
        switch(type){
            case 1:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//SecretDungeon//SD-ok");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                okButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 2:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//SecretDungeon//SD-victory");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                victoryScreenData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 3:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//SecretDungeon//SD-replay");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                replayButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 4:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//SecretDungeon//SD-battlestart");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                startBattleButtonData = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 5:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillAScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillAScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 6:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillBScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillBScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 7:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillCScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillCScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 8:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillDScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillDScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
            case 9:
                myFileInputStream = new FileInputStream("C://SummonersWarFarmer//RefillEScreen");
                myObjectInputStream = new ObjectInputStream(myFileInputStream);
                RefillEScreen = (SelectionInformationContainer) myObjectInputStream.readObject();
                myObjectInputStream.close();
                break;
        }
    }

    public void setConsoleOutput(String content){
        scene_TextAreaOutput.appendText(content);
        scene_TextAreaOutput.appendText("\n\r");

    }
    public void clearConsoleOutput(){
        scene_TextAreaOutput.clear();
    }


}
