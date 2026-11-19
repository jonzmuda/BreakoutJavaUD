import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The core of the Breakout game: the game loop, rendering, keyboard input, and
 * all collision / win / lose logic.
 *
 * <p>This panel repaints on a fixed timer. Each tick calls {@link #update()}
 * (game logic) and then repaints the screen. The bricks are stored in a 2D
 * array, just like the alien grid in Space Invaders.</p>
 *
 * <p>Your job is to fill in the game logic in the marked {@code // TODO}
 * sections. The drawing, timer, and input wiring are already done for you.</p>
 *
 * Key Concepts Used: loops, branching, arrays, methods, objects.
 */
public class Game extends JPanel implements ActionListener, KeyListener {

    // ----- Screen / layout constants -----
    /** Width of the play area in pixels. */
    public static final int WIDTH = 600;
    /** Height of the play area in pixels. */
    public static final int HEIGHT = 650;
    /** Delay between game-loop ticks in milliseconds (~60 FPS). */
    private static final int TICK_MS = 16;

    // ----- Brick grid constants -----
    /** Number of brick rows. */
    private static final int ROWS = 5;
    /** Number of brick columns. */
    private static final int COLS = 10;
    /** Left/right margin before the first/last brick column. */
    private static final int GRID_MARGIN_X = 10;
    /** Top margin before the first brick row. */
    private static final int GRID_MARGIN_Y = 60;
    /** Horizontal gap between bricks. */
    private static final int GAP = 2;

    // ----- Game objects -----
    /** The player's paddle. */
    private Paddle paddle;
    /** The bouncing ball. */
    private Ball ball;
    /** The wall of bricks as a 2D array (null entries mean "no brick here"). */
    private Brick[][] bricks;

    // ----- Game state -----
    /** The main game-loop timer. */
    private Timer timer;
    /** Current score. */
    private int score;
    /** Remaining lives. */
    private int lives;
    /** True while the left arrow / A key is held. */
    private boolean leftHeld;
    /** True while the right arrow / D key is held. */
    private boolean rightHeld;
    /** True once the game has ended (win or loss). */
    private boolean gameOver;
    /** True if the player won (all bricks cleared). */
    private boolean won;

    /**
     * Builds the game panel, sets its size, registers keyboard input, and
     * initializes all game objects.
     */
    public Game() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        score = 0;
        lives = 3;
        gameOver = false;
        won = false;

        paddle = new Paddle(WIDTH, HEIGHT);
        resetBall();
        createBricks();
    }

    /** Starts the game loop. Called once from {@link Breakout#main}. */
    public void start() {
        timer = new Timer(TICK_MS, this);
        timer.start();
    }

    /**
     * Places the ball just above the paddle with an initial upward velocity.
     * Called at the start and after a life is lost.
     */
    private void resetBall() {
        ball = new Ball(WIDTH / 2, HEIGHT - 70, 3, -3);
    }

    /**
     * Fills the {@link #bricks} 2D array. Each row gets its own color and point
     * value, so higher rows are worth more (this also satisfies the
     * "different brick types" enhanced feature).
     */
    private void createBricks() {
        bricks = new Brick[ROWS][COLS];
        Color[] rowColors = { Color.RED, Color.ORANGE, Color.YELLOW,
                              Color.GREEN, Color.CYAN };
        int[] rowPoints = { 50, 40, 30, 20, 10 };

        int cellWidth = Brick.WIDTH + GAP;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                int bx = GRID_MARGIN_X + c * cellWidth;
                int by = GRID_MARGIN_Y + r * (Brick.HEIGHT + GAP);
                bricks[r][c] = new Brick(bx, by, rowPoints[r], rowColors[r]);
            }
        }
    }

    // ================================================================
    //  GAME LOOP
    // ================================================================

    /**
     * Called automatically by the timer on every tick. Advances the game one
     * step and requests a repaint. Do not modify this method.
     *
     * @param e the timer action event (unused)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            update();
        }
        repaint();
    }

    /**
     * Runs one step of game logic: moves the paddle, moves the ball, handles
     * all collisions, and checks win/lose conditions.
     */
    private void update() {
        // Paddle movement based on held keys (already wired for you).
        if (leftHeld) {
            paddle.moveLeft();
        }
        if (rightHeld) {
            paddle.moveRight();
        }

        ball.update();
        checkWallCollisions();
        checkPaddleCollision();
        checkBrickCollisions();
        checkLoseCondition();
        checkWinCondition();
    }

    // ================================================================
    //  COLLISION LOGIC  (your TODOs)
    // ================================================================

    /**
     * Bounces the ball off the left, right, and top walls.
     *
     * TODO:
     *   - If the ball's x is at or past the left edge (x <= 0) OR at or past
     *     the right edge (x + Ball.SIZE >= WIDTH), call ball.bounceX().
     *   - If the ball's y is at or past the top edge (y <= 0),
     *     call ball.bounceY().
     *   (The BOTTOM wall is NOT handled here — falling past the bottom costs a
     *    life, which you handle in checkLoseCondition().)
     * Difficulty: Medium
     */



    private void checkWallCollisions() {
        // TODO: reverse ball direction when it hits a side or the top wall
        int x = ball.getX();
        int y = ball.getY();
        //12 is ball size, 600 is width
        if(x <= 0 || x+12 >= 600){
            ball.bounceX();
        }
        if(y <= 0){
            ball.bounceY();
        }    }

    /**
     * Bounces the ball upward when it strikes the paddle.
     *
     * TODO:
     *   - If ball.getBounds() intersects paddle.getBounds() AND the ball is
     *     moving downward (ball.getDy() > 0), call ball.bounceY().
     *   - (Optional polish) Adjust ball.setDx(...) based on where it hit the
     *     paddle so the player can aim.
     * Hint: Rectangle has an intersects(Rectangle) method.
     * Difficulty: Medium
     */
    private void checkPaddleCollision() {
        // TODO: bounce the ball off the paddle
        if(ball.getBounds().intersects(paddle.getBounds()) && ball.getDy() > 0){
            ball.bounceY();
        }
    }

    /**
     * Checks the ball against every brick in the 2D array. The first live brick
     * the ball overlaps is destroyed; the ball bounces and the score increases.
     *
     * TODO:
     *   - Loop over every row r and column c of the bricks array.
     *   - Skip bricks that are null or already destroyed.
     *   - If ball.getBounds() intersects brick.getBounds():
     *         * mark the brick destroyed  (brick.setDestroyed(true))
     *         * add brick.getPoints() to score
     *         * call ball.bounceY()
     *         * return  (handle at most one brick per tick)
     * Difficulty: Hard
     */
    private void checkBrickCollisions() {
        // TODO: destroy the first brick the ball hits, score it, and bounce
    }

    // ================================================================
    //  WIN / LOSE LOGIC  (your TODOs)
    // ================================================================

    /**
     * Ends the game as a WIN when every brick has been destroyed.
     *
     * TODO:
     *   - Loop over the bricks array.
     *   - If any brick is non-null and NOT destroyed, there are bricks left,
     *     so return without ending the game.
     *   - If the loop finishes with no live bricks, set won = true and
     *     call endGame().
     * Difficulty: Easy
     */
    private void checkWinCondition() {
        // TODO: if no live bricks remain, win the game
    }

    /**
     * Handles the ball falling below the bottom of the screen: the player loses
     * a life. When no lives remain, the game ends as a LOSS.
     *
     * TODO:
     *   - If the ball's y has gone past the bottom (ball.getY() > HEIGHT):
     *         * subtract 1 from lives
     *         * if lives <= 0: set won = false and call endGame()
     *         * otherwise: call resetBall() to send a new ball from the paddle
     * Difficulty: Easy
     */
    private void checkLoseCondition() {
        // TODO: lose a life when the ball falls off the bottom; end at 0 lives
    }

    /** Stops the game loop and flags the game as over. */
    private void endGame() {
        gameOver = true;
        if (timer != null) {
            timer.stop();
        }
    }

    // ================================================================
    //  RENDERING  (done for you)
    // ================================================================

    /**
     * Draws the entire scene: bricks, paddle, ball, HUD, and — when the game is
     * over — the win/lose screen.
     *
     * @param g the graphics context provided by Swing
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (bricks[r][c] != null) {
                    bricks[r][c].draw(g);
                }
            }
        }

        paddle.draw(g);
        ball.draw(g);
        drawHud(g);

        if (gameOver) {
            drawGameOver(g);
        }
    }

    /**
     * Draws the score and remaining lives at the top of the screen.
     *
     * @param g the graphics context to draw on
     */
    private void drawHud(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.drawString("Score: " + score, 12, 25);
        g.drawString("Lives: " + lives, WIDTH - 110, 25);
    }

    /**
     * Draws the centered win or loss message with the final score.
     *
     * @param g the graphics context to draw on
     */
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        String msg = won ? "YOU WIN!" : "GAME OVER";
        g.drawString(msg, WIDTH / 2 - 120, HEIGHT / 2 - 20);

        g.setFont(new Font("SansSerif", Font.PLAIN, 22));
        g.drawString("Final Score: " + score, WIDTH / 2 - 90, HEIGHT / 2 + 20);
    }

    // ================================================================
    //  KEYBOARD INPUT  (done for you)
    // ================================================================

    /**
     * Sets movement flags when a key is pressed.
     *
     * @param e the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftHeld = true;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightHeld = true;
        }
    }

    /**
     * Clears movement flags when a key is released.
     *
     * @param e the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
            leftHeld = false;
        }
        if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
            rightHeld = false;
        }
    }

    /**
     * Unused, but required by the KeyListener interface.
     *
     * @param e the key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used.
    }
}
