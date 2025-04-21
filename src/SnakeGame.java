import java.awt.*;
import java.util.Random;

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
    Tile food;
    Random random;
    int tileSize = 25; // Size of each tile in pixels
    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; 
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);

        snakeHead = new Tile(5,5); // Initialize snake head position
        food = new Tile(10, 10);

        random = new Random();
        placeFood(random); // Place food at a random position


    
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for(int i =0;i<boardWidth/tileSize;i++){
            //(x1,y1,x2,y2)
            // Draw vertical and horizontal lines to create a grid
            g.drawLine(i*tileSize,0,i*tileSize, boardWidth);
            g.drawLine(0,i*tileSize,boardWidth,i*tileSize);
            for(i=0;i<boardHeight/tileSize;i++){
                g.drawLine(i*tileSize,0,i*tileSize, boardHeight);
                g.drawLine(0,i*tileSize,boardHeight,i*tileSize);
            }
        }

        //draw snake head
        g.setColor(Color.GREEN);
        g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize,tileSize, tileSize); // Draw snake head

        //draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * tileSize, food.y * tileSize, tileSize, tileSize); // Draw food at (10, 10) for now
    }

    public void placeFood(Random random) {
        // Generate random coordinates for food
        int x = random.nextInt(boardWidth / tileSize); //600/25 = 24 => 0-23
        int y = random.nextInt(boardHeight / tileSize); //600/25 = 24 => 0-23
        food.x = x;
        food.y = y;
    
    }
    
}
