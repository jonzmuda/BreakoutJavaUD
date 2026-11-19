import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The player-controlled paddle at the bottom of the screen.
 *
 * <p>The paddle moves left and right along the bottom of the play area and must
 * stay fully on screen. It is the Breakout equivalent of the Space Invaders
 * player ship.</p>
 *
 * Key Concepts Used: classes/objects, variables, branching.
 */
public class Paddle {

    /** Width of the paddle in pixels. */
    public static final int WIDTH = 90;
    /** Height of the paddle in pixels. */
    public static final int HEIGHT = 14;
    /** How many pixels the paddle moves per step. */
    public static final int SPEED = 7;

    /** Top-left x position of the paddle. */
    private int x;
    /** Top-left y position of the paddle (fixed near the bottom). */
    private int y;
    /** Width of the play area, used to clamp movement on screen. */
    private final int screenWidth;

    /**
     * Creates a paddle horizontally centered near the bottom of the screen.
     *
     * @param screenWidth  width of the play area in pixels
     * @param screenHeight height of the play area in pixels
     */
    public Paddle(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.x = (screenWidth - WIDTH) / 2;
        this.y = screenHeight - 50;
    }

    /**
     * Moves the paddle left by {@link #SPEED} pixels, clamped so it cannot
     * leave the left edge of the screen.
     *
     * TODO: decrease x by SPEED, then clamp x so it is never less than 0.
     * Difficulty: Easy
     */
    public void moveLeft() {
        x -= SPEED;
        if (x < 0) {
            x = 0;
        }
    }

    /**
     * Moves the paddle right by {@link #SPEED} pixels, clamped so it cannot
     * leave the right edge of the screen.
     *
     * TODO: increase x by SPEED, then clamp x so (x + WIDTH) never exceeds
     *       screenWidth.
     * Difficulty: Easy
     */
    public void moveRight() {
        x += SPEED;
        if (x + WIDTH > screenWidth) {
            x = screenWidth - WIDTH;
        }
    }

    /**
     * Returns the paddle's collision bounds for ball-paddle collision testing.
     *
     * @return a rectangle matching the paddle's current position and size
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Draws the paddle.
     *
     * @param g the graphics context to draw on
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, WIDTH, HEIGHT, 8, 8);
    }

    /** @return the current top-left x position */
    public int getX() { return x; }

    /** @return the current top-left y position */
    public int getY() { return y; }
}
