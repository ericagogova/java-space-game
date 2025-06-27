package org.ergasia.javaspacegame;

import java.awt.Image;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Random;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class makes the enemies of this
 * game.
 *
 * @author Kosmas Sidiropoulos 2114111
 *
 */
public class Ufo {
	/*X dimension of the object */
	private int x;
	/*Y dimension of the object */
	private int y;
	/*The width of the object */
    private int width;
	/*The height of the object */
    private int height;
    /*Boolean variable that checks if an Ufo is crushed or not */
	private boolean ufoCrushed = false;
	private boolean ufoCollided = false;
	/*The image of the object */
    private Image image;
    /*In this variable is saved the total score */
	private static int score = 0;
	/*In this variable is saved the stage score */
	private static int stageScore = 0;
	/*Random object */
	private Random rand;
	private boolean movingRight;
	private int frameCounter = 0;

	/**
	 * The Constructor.
	 * Sets the position and the image of the object.
	 */
	public Ufo() {
		ImageIcon ii = null;

		try {
			ii = new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ufo.png"))));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		image = ii.getImage();
		rand = new Random();

		do {
			x = 40 + rand.nextInt(720);
			y = 30 + rand.nextInt(440);
		} while (x > 642 && y < 137);

		width = image.getWidth(null);
		height = image.getHeight(null);

		int random = rand.nextInt(2);
		if (random==0){
			movingRight=true;
		}
		else{
			movingRight=false;
		}
	}


	public void reverseDirection(){
		movingRight= !movingRight;
	}
	public void move() {
		x= getX();
		y= getY();


		int limitforx;
		if (y<137){
			limitforx=612;
		}
		else{
			 limitforx=720;
		}
		if (movingRight) {
			x++;
			if (x >= limitforx) { // Adjust this upper limit to match your right boundary
				movingRight = false;
			}
		} else {
			x--;
			if (x <= 40) { // Adjust this lower limit to match your left boundary
				movingRight = true;
			}
		}
	}

	/**
	 * The update method which will check for collision.
	 *
	 * @param pn The game panel.
	 * @param ammo The Ammo object that we want to check for collision.
	 */
	public void update(Panel pn, ArrayList<Ammo> ammo){
		checkForCollision(ammo);

	}

	/**
	 * This method checks for collision and adds the score.
	 *
	 * @param ammos The ammunition of the spaceship(An8rwpaki).
	 */
	private void checkForCollision(ArrayList<Ammo> ammos) {
		for(int i=0; i<ammos.size(); i++){

			int ammoX = ammos.get(i).getX();
			int ammoY = ammos.get(i).getY();
			int ammoWidth = ammos.get(i).getWidth();
			int ammoHeight = ammos.get(i).getHeight();

			if(ammoY + ammoHeight > y && ammoY + ammoHeight < y + height){
				if(ammoX + ammoWidth > x && ammoX < x + width){
					image = null;
					ufoCrushed = true;
					score+=10;
					stageScore += 10;
				}
			}

		}

	}


	public boolean collidesWith(Ufo other) {
		return this.x < other.x + other.width &&
				this.x + this.width > other.x &&
				this.y < other.y + other.height &&
				this.y + this.height > other.y;
	}

	public boolean isCollided() {
		return ufoCollided;
	}

	public void setCollided(boolean value) {
		this.ufoCollided = value;
	}

	public void setImage(Image img) {
		this.image = img;
	}

	public void checkForUfoCollisions(ArrayList<Ufo> ufos) {
		for (int i = 0; i < ufos.size(); i++) {
			Ufo u1 = ufos.get(i);
			if (u1.isCollided()) continue; // skip destroyed UFOs

			for (int j = i + 1; j < ufos.size(); j++) {
				Ufo u2 = ufos.get(j);
				if (u2.isCollided()) continue;

				if (u1.collidesWith(u2)) {
					// Example: destroy both or reverse directions
					u1.setCollided(true);
					u2.setCollided(true);
					u1.reverseDirection();
					u2.reverseDirection();

				}
			}
		}
	}

		// 4. (Optional) repaint or redraw your game screen here




	/**
	 * Get the ufo status. Crushed or no-crushed.
	 *
	 * @return Returns true is the UFO is crushed.
	 */
	public boolean ufoCrushedCheck(){
		return ufoCrushed;
	}

	/**
	 * Sets the stage score
	 *
	 * @param stageScore the stage score.
	 */
	public static void setStageScore(int stageScore) {
		Ufo.stageScore = stageScore;
	}

	/**
	 * Get the total score.
	 *
	 * @return Returns the total score.
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * Get the stage score.
	 *
	 * @return Return the stage score.
	 */
	public static int getStageScore() {
		return stageScore;
	}

	/**
     * Get the image of the object.
     *
     * @return The image of the object.
     */
	public Image getImage() {
		return image;
	}

	/**
	 * Get the x-dimension value.
	 *
	 * @return The x-dimension value.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y-dimension value.
	 *
	 * @return The y-dimension value.
	 */
	public int getY() {
		return y;
	}

	public static void setScore(int score) {
		Ufo.score = score;
	}


}
