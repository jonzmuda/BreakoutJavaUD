import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * The bouncing ball. This is the Breakout analog of the Space Invaders bullet,
 * except it travels in BOTH the x and y directions and bounces off walls,
 * the paddle, and bricks.
 *
 * Key Concepts Used: classes/objects, variables, branching.
 */
public class Ball {

    /** Diameter of the ball in pixels. */
    public static final int SIZE = 12;

    /** Top-left x position of the ball. */
    private int x;
    /** Top-left y position of the ball. */
    private int y;
    /** Horizontal velocity in pixels per step (negative = left). */
    private int dx;
    /** Vertical velocity in pixels per step (negative = up). */
    private int dy;

    /**
     * Creates a ball at the given position with the given velocity.
     *
     * @param x  starting top-left x position
     * @param y  starting top-left y position
     * @param dx starting horizontal velocity
     * @param dy starting vertical velocity
     */
    public Ball(int x, int y, int dx, int dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Advances the ball one step by adding its velocity to its position.
     *
     * TODO: add dx to x, and add dy to y.
     * Difficulty: Easy
     *
     * Jon Zmuda
     */
    public void update() {
        // TODO: move the ball by its velocity
        this.x += dx;
        this.y += dy;
    }

    /**
     * Reverses horizontal direction (used when hitting a left/right wall or the
     * side of a brick).
     */
    public void bounceX() {
        dx = -dx;
    }

    /**
     * Reverses vertical direction (used when hitting the top wall, the paddle,
     * or the top/bottom of a brick).
     */
    public void bounceY() {
        dy = -dy;
    }

    /**
     * Returns the ball's collision bounds for overlap testing.
     *
     * @return a rectangle matching the ball's current position and size
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    /**
     * Draws the ball.
     *
     * @param g the graphics context to draw on
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, SIZE, SIZE);
    }

    /** @return current top-left x position */
    public int getX() { return x; }
    /** @return current top-left y position */
    public int getY() { return y; }
    /** @return current horizontal velocity */
    public int getDx() { return dx; }
    /** @return current vertical velocity */
    public int getDy() { return dy; }

    /** @param x the new top-left x position */
    public void setX(int x) { this.x = x; }
    /** @param y the new top-left y position */
    public void setY(int y) { this.y = y; }
    /** @param dx the new horizontal velocity */
    public void setDx(int dx) { this.dx = dx; }
    /** @param dy the new vertical velocity */
    public void setDy(int dy) { this.dy = dy; }
}
