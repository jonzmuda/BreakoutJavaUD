import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * A single destructible brick. The wall of bricks is stored as a 2D array in
 * {@link Game}, exactly like the alien grid in Space Invaders.
 *
 * <p>Each brick has a point value and a color. A brick can be marked as
 * destroyed; destroyed bricks are skipped during drawing and collision
 * checks.</p>
 *
 * Key Concepts Used: classes/objects, variables.
 */
public class Brick {

    /** Width of a brick in pixels. */
    public static final int WIDTH = 58;
    /** Height of a brick in pixels. */
    public static final int HEIGHT = 20;

    /** Top-left x position of the brick. */
    private final int x;
    /** Top-left y position of the brick. */
    private final int y;
    /** Points awarded when this brick is destroyed. */
    private final int points;
    /** Color used to draw this brick. */
    private final Color color;
    /** Whether this brick has been destroyed. */
    private boolean destroyed;

    /**
     * Creates a brick at the given position with a point value and color.
     *
     * @param x      top-left x position
     * @param y      top-left y position
     * @param points points awarded when destroyed
     * @param color  color used to draw the brick
     */
    public Brick(int x, int y, int points, Color color) {
        this.x = x;
        this.y = y;
        this.points = points;
        this.color = color;
        this.destroyed = false;
    }

    /**
     * Returns the brick's collision bounds for overlap testing.
     *
     * @return a rectangle matching the brick's position and size
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Draws the brick if it has not been destroyed.
     *
     * @param g the graphics context to draw on
     */
    public void draw(Graphics g) {
        if (destroyed) {
            return;
        }
        g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);
    }

    /** @return true if this brick has been destroyed */
    public boolean isDestroyed() { return destroyed; }

    /** @param destroyed the new destroyed state */
    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }

    /** @return the point value of this brick */
    public int getPoints() { return points; }
}
