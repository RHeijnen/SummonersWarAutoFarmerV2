package Templates;

import ScreenSelection.SelectionInformationContainer;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.TimerTask;

/**
 * Created by Indi on 7/17/2016.
 *
 */
public class ScenarioFarmingLogic extends TimerTask implements GeneralFarmLogic {
    public static boolean active = false;
    //
    private SelectionInformationContainer replayButtonData;
    private SelectionInformationContainer victoryScreenData;
    private SelectionInformationContainer lootButtonOKData;      // for non runes
    private SelectionInformationContainer lootButtonOKData2;      // for non runes

    private SelectionInformationContainer lootButtonGETRUNEData; // for runes
    private SelectionInformationContainer startBattleButtonData;
    //
    private SelectionInformationContainer RefillAScreen;
    private SelectionInformationContainer RefillBScreen;
    private SelectionInformationContainer RefillCScreen;
    private SelectionInformationContainer RefillDScreen;
    private SelectionInformationContainer RefillEScreen;
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


    //
    public ScenarioFarmingLogic(SelectionInformationContainer lootButtonGETRUNEData, SelectionInformationContainer victoryScreenData, SelectionInformationContainer okButtonData,SelectionInformationContainer okButtonData2, SelectionInformationContainer replayButtonData, SelectionInformationContainer startBattleButtonData, TextArea scene_WinCounter, int offset) {
        this.lootButtonGETRUNEData = lootButtonGETRUNEData;
        this.victoryScreenData = victoryScreenData;
        this.lootButtonOKData = okButtonData;
        this.lootButtonOKData2 = okButtonData2;

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
    public ScenarioFarmingLogic(SelectionInformationContainer lootButtonGETRUNEData, SelectionInformationContainer victoryScreenData, SelectionInformationContainer okButtonData,SelectionInformationContainer okButtonData2, SelectionInformationContainer replayButtonData, SelectionInformationContainer startBattleButtonData, TextArea scene_WinCounter, int offset, int refillMax, SelectionInformationContainer RefillAScreen, SelectionInformationContainer RefillBScreen, SelectionInformationContainer RefillCScreen, SelectionInformationContainer RefillDScreen, SelectionInformationContainer RefillEScreen) {
        this.lootButtonGETRUNEData = lootButtonGETRUNEData;
        this.victoryScreenData = victoryScreenData;
        this.lootButtonOKData = okButtonData;
        this.replayButtonData = replayButtonData;
        this.lootButtonOKData2 = okButtonData2;
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
    //


    public void setConsoleOutput(int content) {
        scene_WinCounter.setText(String.valueOf(content));

    }

    //
    @Override
    public void checkVictory() {
        if(!bool_VictoryPressed){
            if (victoryScreenData.doColorsMatch(150)) {
                System.out.println("victory screen");
                int mousePosition[] = victoryScreenData.getCenterLocationOfRectangleWithRandomOffset(10);
                robo.mouseMove(mousePosition[0], mousePosition[1]);
                robo.delay(100);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(100);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(250);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                robo.mousePress(InputEvent.BUTTON1_MASK);
                robo.delay(250);
                robo.mouseRelease(InputEvent.BUTTON1_MASK);
                robo.delay(1000);
                winCounter++;
                setConsoleOutput(winCounter);
                bool_VictoryPressed = true;
        }

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
            bool_VictoryPressed = false;

        }

    }

    @Override
    public void checkLoot() {
        //System.out.println(lootButtonOKData2.getSelectionPixelDifference());

        if (lootButtonOKData.doColorsMatch(150)) {
            System.out.println("lootButtonOKData");
            int mousePosition[] = lootButtonOKData.getCenterLocationOfRectangleWithRandomOffset(offset);
            robo.mouseMove(mousePosition[0], mousePosition[1]);
            robo.delay(100);
            robo.mousePress(InputEvent.BUTTON1_MASK);
            robo.delay(100);
            robo.mouseRelease(InputEvent.BUTTON1_MASK);
            robo.delay(100);
            bool_OKPressed = true;

        } else if (lootButtonGETRUNEData.doColorsMatch(150)) {
            System.out.println("lootButtonGETRUNEData");
            int mousePosition[] = lootButtonGETRUNEData.getCenterLocationOfRectangleWithRandomOffset(offset);
            robo.mouseMove(mousePosition[0], mousePosition[1]);
            robo.delay(100);
            robo.mousePress(InputEvent.BUTTON1_MASK);
            robo.delay(100);
            robo.mouseRelease(InputEvent.BUTTON1_MASK);
            robo.delay(100);
            bool_OKPressed = true;
        } else if (lootButtonOKData2.doColorsMatch(150)) {

            System.out.println("lootButtonOKData2");
            int mousePosition[] = lootButtonOKData2.getCenterLocationOfRectangleWithRandomOffset(offset);
            robo.mouseMove(mousePosition[0], mousePosition[1]);
            robo.delay(100);
            robo.mousePress(InputEvent.BUTTON1_MASK);
            robo.delay(100);
            robo.mouseRelease(InputEvent.BUTTON1_MASK);
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
        if (!bool_RefillPressed) {
            if (refillCounter != refillMax) {
                if (RefillAScreen.doColorsMatch(0)) {
                    bool_RefillPressed = true;
                    int RefillAScreenPosition[] = RefillAScreen.getCenterLocationOfRectangleWithRandomOffset(offset);
                    robo.mouseMove(RefillAScreenPosition[0], RefillAScreenPosition[1]);
                    robo.delay(100);
                    robo.mousePress(InputEvent.BUTTON1_MASK);
                    robo.delay(100);
                    robo.mouseRelease(InputEvent.BUTTON1_MASK);
                    robo.delay(2000);
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
                    robo.delay(2000);
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

    }
    //


    @Override
    public void run() {
        if (firstRun) {
            robo.delay(5000);
            firstRun = false;
        }
        if (active) {
            if (!bool_farmWithRefill) {
                System.out.println("ok2: "+lootButtonOKData2.getSelectionPixelDifference());
                System.out.println("ok: "+lootButtonOKData.getSelectionPixelDifference());
                System.out.println("victory: "+victoryScreenData.getSelectionPixelDifference());
                System.out.println("replay: "+replayButtonData.getSelectionPixelDifference());
                System.out.println("start: "+startBattleButtonData.getSelectionPixelDifference());
                System.out.println("rune: "+lootButtonGETRUNEData.getSelectionPixelDifference());

                // FARM WITHOUT REFILLS
                if (!bool_busy) {
                    bool_busy = true;
                    checkVictory();
                    checkBattleActive();
                    checkReplay();
                    checkLoot();
                    bool_busy = false;
                }
            } else {
                // FARM WITH REFILLS
                if (!bool_busy) {
                    bool_busy = true;
                    if (refillCounter >= refillMax + 1) {
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


