import java.awt.*;

import javax.swing.*;

public class SnakeGame extends JPanel {

    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    Tile snakeHead;
    int tileSize = 25; // Size of each tile in pixels
    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; 
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);     
        snakeHead = new Tile(5,5); // Initialize snake head position
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int pixelx = snakeHead.x * tileSize; // Convert tile coordinates to pixel coordinates
        int pixely = snakeHead.y * tileSize; // Convert tile coordinates to pixel coordinates
        g.setColor(Color.GREEN);
        g.fillRect(pixelx, pixely,tileSize, tileSize); // Draw snake head
    }
    
}
