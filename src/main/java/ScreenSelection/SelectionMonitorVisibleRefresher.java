package ScreenSelection;

import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by Indi on 7/16/2016.
 */
public class SelectionMonitorVisibleRefresher extends TimerTask {
    SelectionInformationContainer SIC;
    ArrayList selectionPixelList;
    ImageView IV;
    int selectionPixelAmmount;
    Rectangle selectionRectangle;
    Robot robot;
    boolean runnable = true;
    BufferedImage bf;
    WritableImage wi;


    public SelectionMonitorVisibleRefresher(SelectionInformationContainer SIC, ArrayList selectionPixelList, ImageView IV, int selectionPixelAmmount, Rectangle selectionRectangle) {
        this.SIC = SIC;
        this.selectionPixelList = selectionPixelList;
        this.IV = IV;
        this.selectionPixelAmmount = selectionPixelAmmount;
        this.selectionRectangle = selectionRectangle;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        runnable = false; // guard
        int temp_pixelcount = SIC.getSelectionPixelAmmount();
        ArrayList TEMP_Pixel_Array  = new ArrayList(temp_pixelcount);
        bf = robot.createScreenCapture(selectionRectangle);
        wi = new WritableImage(bf.getWidth(), bf.getHeight());
        PixelWriter pw = wi.getPixelWriter();
        int changecounter = 0;
        int i = 0;
        for (int x = 0; x < bf.getWidth(); x++) {
            for (int y = 0; y < bf.getHeight(); y++) {
                pw.setArgb(x, y, bf.getRGB(x, y)); // write pixels to x y location with RGB value of the buf.img
                TEMP_Pixel_Array.add(i,new Color(bf.getRGB(x, y)));
                if(!TEMP_Pixel_Array.get(i).equals(SIC.getSelectionPixelList().get(i))){
                    changecounter++;
                }
                i++;
            }
        }
        SIC.setSelectionRefreshedPixelList(TEMP_Pixel_Array);
        //System.out.println(SIC.getSelectionRefreshedPixelList().get(4));
        SIC.setSelectionPixelDifference(changecounter);
        //

        IV.setImage(wi);
        runnable = true; // guard

    }
}
