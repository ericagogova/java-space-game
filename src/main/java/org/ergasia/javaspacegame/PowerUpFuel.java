package org.ergasia.javaspacegame;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PowerUpFuel {
    /*X dimension of the object */
    private int x;
    /*Y dimension of the object */
    private int y;
    /*The width of the object */
    private int width;
    /*The height of the object */
    private int height;
    private Image image;
    /*In this variable is saved the total score */
    /*Random object */
    private Random rand;
    private boolean fuelhit;
    private boolean isActive=false;

    public PowerUpFuel() {
        ImageIcon ii = null;
        try {
            ii = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/gasolina.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image = ii.getImage();
        rand = new Random();

        do{
            x = 40 + rand.nextInt(720);
            y = 30 + rand.nextInt(440);
        }while(x > 642 && y < 137);

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    public Image getImage() {
        return image;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public void checkForCollision(ArrayList<Ammo> ammos) {
        if (fuelhit) return;

        for(int i=0; i<ammos.size(); i++){

            int ammoX = ammos.get(i).getX();
            int ammoY = ammos.get(i).getY();
            int ammoWidth = ammos.get(i).getWidth();
            int ammoHeight = ammos.get(i).getHeight();

            if(ammoY + ammoHeight > y && ammoY + ammoHeight < y + height){
                if(ammoX + ammoWidth > x && ammoX < x + width){
                    image = null;
                    fuelhit = true;
                    boolean isActive = true;
                }
            }

        }

    }
    public boolean fuelCheck(){
        return fuelhit;
    }

    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }
}
