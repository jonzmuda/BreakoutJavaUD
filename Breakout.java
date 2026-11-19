import javax.swing.JFrame;

/**
 * Entry point for the Breakout final project.
 *
 * <p>This class creates the application window and starts the game. You should
 * not need to modify this file. All of your game logic lives in {@link Game}
 * and the entity classes ({@link Paddle}, {@link Ball}, {@link Brick}).</p>
 *
 * Key Concepts Used: variables, method calls, objects.
 */
public class Breakout {

    /**
     * Builds the window, adds the game panel, and starts the game loop.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Breakout - CPS 150 Final Project");
        Game game = new Game();

        window.add(game);
        window.pack();                                   // size window to the panel
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);              // center on screen
        window.setVisible(true);

        game.requestFocusInWindow();                     // so key input is received
        game.start();
    }
}
