package Templates;

import ScreenSelection.SelectionInformationContainer;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.TimerTask;

/**
 * Created by Indi on 7/16/2016.
 //////////// Color Logic /////////////
 // yellow in STAGE CLEAR
 // R 254 / G 205 / B 23
 /////////////////////////
 // OK button on loot
 // R 201 / G 152 / B 84
 ///////////////////////////
 // Replay button
 // R 25 / G 16 / B 17
 //////////////////////////
 // Start battle button
 // R 211 / G 169 / B 78
 /////////////////////////
 */
public class SecretDungeonLogic extends TimerTask implements GeneralFarmLogic {
    public static boolean active = false;
    private SelectionInformationContainer victoryScreenData;
    private SelectionInformationContainer okButtonData;
    private SelectionInformationContainer replayButtonData;
    private SelectionInformationContainer startBattleButtonData;
    //
    private SelectionInformationContainer RefillAScreen;
    private SelectionInformationContainer RefillBScreen;
    private SelectionInformationContainer RefillCScreen;
    private SelectionInformationContainer RefillDScreen;
    private SelectionInformationContainer RefillEScreen;
    //
    private boolean firstRun = true;
    private TextArea scene_WinCounter;
    private int offset;
    int sleepyCounter = 0;
    boolean bool_busy = false;
    boolean bool_VictoryPressed = false;
    boolean bool_BattlePressed = false;
    boolean bool_farmWithRefill = false;
    boolean bool_OKPressed = false;
    boolean bool_ReplayPressed = false;
    boolean bool_RefillPressed = false;
    int winCounter = 0;
    int refillMax = 0;
    int refillCounter = 0;
    Robot robo;

    public void setConsoleOutput(int content){
        scene_WinCounter.setText(String.valueOf(content));

    }
    public SecretDungeonLogic(SelectionInformationContainer victoryScreenData, SelectionInformationContainer okButtonData, SelectionInformationContainer replayButtonData, SelectionInformationContainer startBattleButtonData, TextArea scene_WinCounter, int offset){
        this.victoryScreenData = victoryScreenData;
        this.okButtonData = okButtonData;
        this.replayButtonData = replayButtonData;
        this.startBattleButtonData = startBattleButtonData;
        this.scene_WinCounter = scene_WinCounter;
        this.offset = offset;
        setConsoleOutput(winCounter);
        try {
            robo = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    // overloaded constructor
    public SecretDungeonLogic(SelectionInformationContainer victoryScreenData, SelectionInformationContainer okButtonData, SelectionInformationContainer replayButtonData, SelectionInformationContainer startBattleButtonData, TextArea scene_WinCounter, int offset, int refillMax, SelectionInformationContainer RefillAScreen, SelectionInformationContainer RefillBScreen, SelectionInformationContainer RefillCScreen, SelectionInformationContainer RefillDScreen, SelectionInformationContainer RefillEScreen){
        this.victoryScreenData = victoryScreenData;
        this.okButtonData = okButtonData;
        this.replayButtonData = replayButtonData;
        this.startBattleButtonData = startBattleButtonData;
        this.scene_WinCounter = scene_WinCounter;
        this.offset = offset;
        this.refillMax = refillMax;
        this.RefillAScreen = RefillAScreen;
        this.RefillBScreen = RefillBScreen;
        this.RefillCScreen = RefillCScreen;
        this.RefillDScreen = RefillDScreen;
        this.RefillEScreen = RefillEScreen;

        bool_farmWithRefill = true;
        setConsoleOutput(winCounter);
        try {
            robo = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void checkVictory() {
        if(!bool_VictoryPressed){
            if(victoryScreenData.doColorsMatch(150)) {
                bool_VictoryPressed = true;
                System.out.println("victory screen");
                int mousePosition[] = victoryScreenData.getCenterLocationOfRectangleWithRandomOffset(10);
                robo.mouseMove(mousePosition[0],mousePosition[1]);
                robo.delay(100);
                robo.mousePress( InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease( InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                robo.mousePress( InputEvent.BUTTON1_MASK);
                robo.delay(250);
                robo.mouseRelease( InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                robo.mousePress( InputEvent.BUTTON1_MASK);
                robo.delay(250);
                robo.mouseRelease( InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                winCounter++;
                setConsoleOutput(winCounter);
                bool_VictoryPressed = true;
                bool_BattlePressed = false;
            }// else if colors match > losing

        }

    }
    // R 25 / G 16 / B 17

    @Override
    public void checkBattleActive() {
            if (startBattleButtonData.doColorsMatch(150)) {
                System.out.println("Start btn");
                int mousePosition[] = startBattleButtonData.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(mousePosition[0], mousePosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                bool_BattlePressed = true;
                bool_VictoryPressed = false;
            }

    }

    @Override
    public void checkLoot() {
            if(okButtonData.doColorsMatch(150)){
                System.out.println("OK btn");
                int mousePosition[] = okButtonData.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(mousePosition[0],mousePosition[1]);
                robo.delay(100);
                robo.mousePress( InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease( InputEvent.BUTTON1_MASK);
                robo.delay(100);
                bool_OKPressed = true;

        }

    }

    @Override
    public void checkReplay() {
            if (replayButtonData.doColorsMatch(150)) {
                bool_ReplayPressed = true;
                bool_BattlePressed = false;
                System.out.println("Replay btn");
                int mousePosition[] = replayButtonData.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(mousePosition[0], mousePosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(100);
            }

    }

    @Override
    public void checkRefill() {
        if(!bool_RefillPressed){
            if(RefillAScreen.doColorsMatch(0)){
                bool_RefillPressed = true;
                int RefillAScreenPosition[] = RefillAScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(RefillAScreenPosition[0], RefillAScreenPosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                //
                int RefillBScreenPosition[] = RefillBScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(RefillBScreenPosition[0], RefillBScreenPosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(2000);
                //
                int RefillCScreenPosition[] = RefillCScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(RefillCScreenPosition[0], RefillCScreenPosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(2000);
                //
                int RefillDScreenPosition[] = RefillDScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(RefillDScreenPosition[0], RefillDScreenPosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                //
                int RefillEScreenPosition[] = RefillEScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                robo.mouseMove(RefillEScreenPosition[0], RefillEScreenPosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                refillCounter++;
                bool_ReplayPressed = false;

            }
        }

    }
    /*

        Loot Logic

     */
    @Override
    public void run() {
        if(firstRun){
            robo.delay(5000);
            firstRun = false;
        }
        if(active){
            if(!bool_farmWithRefill){
                // FARM WITHOUT REFILLS
                if(!bool_busy){
                    bool_busy = true;
                    checkVictory();
                    checkBattleActive();
                    checkReplay();
                    checkLoot();
                    bool_busy = false;
                }
            }else {
                // FARM WITH REFILLS
                if(!bool_busy) {
                    bool_busy = true;
                    if(refillCounter >= refillMax){
                        active = false;
                    }
                    //logic here
                    checkVictory();
                    checkBattleActive();
                    checkLoot();
                    checkReplay();
                    checkRefill();
                    //checkReplay();
                    bool_busy = false;

                }
            }
        }
    }
}
