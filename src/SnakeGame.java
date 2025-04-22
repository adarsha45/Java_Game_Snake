import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener,KeyListener {
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    int tileSize = 25; // Size of each tile in pixels
    int boardWidth;
    int boardHeight;
    //snake
    Tile snakeHead;
    //food
    Tile food;
    Random random;
    //game logic
    Timer gameLoop;
    int velocityX; // Speed of the snake
    int velocityY; // Speed of the snake

    ArrayList<Tile> snakeBody = new ArrayList<>(); // List to store the snake's body segments


    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight; 
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        addKeyListener(this); // Add key listener to the panel
        setFocusable(true); // Make the panel focusable

        snakeHead = new Tile(5,5); // Initialize snake head position
        food = new Tile(10, 10);
        snakeBody = new ArrayList<Tile>(); // Initialize the snake body list

        random = new Random();
        placeFood(random); // Place food at a random position

        velocityX = 0; // Set initial speed of the snake
        velocityY = 0; // Set initial speed of the snake

        gameLoop = new Timer(100, this); // Timer to control the game loop
        gameLoop.start();
    
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

        for(int i=0;i<snakeBody.size();i++){
            Tile segment = snakeBody.get(i);
            g.setColor(Color.YELLOW); // Set color for snake body segments
            g.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize); // Draw each segment of the snake body
        }
    }

    public void placeFood(Random random) {
        // Generate random coordinates for food
        int x = random.nextInt(boardWidth / tileSize); //600/25 = 24 => 0-23
        int y = random.nextInt(boardHeight / tileSize); //600/25 = 24 => 0-23
        food.x = x;
        food.y = y;
    
    }

    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x == tile2.x && tile1.y == tile2.y;
    }

    public void move(){
        if(collision(snakeHead, food)){
            // If snake head collides with food, place new food and grow the snake
            snakeBody.add(new Tile(snakeHead.x, snakeHead.y)); // Add new segment to the snake body
            placeFood(random); // Place new food at a random position

            // Increase the size of the snake body by adding a new segment at the current head position             
        }
        snakeHead.x += velocityX; // Move snake head to the right by one tile
        snakeHead.y += velocityY; // Keep snake head in the same row
        if(snakeHead.y >= boardHeight/tileSize){
            snakeHead.y = 0; // Wrap around to the top of the board
        }else if(snakeHead.y < 0){
            snakeHead.y = boardHeight/tileSize - 1; // Wrap around to the bottom of the board
        }else if(snakeHead.x >= boardWidth/tileSize){
            snakeHead.x = 0; // Wrap around to the left of the board
        }else if(snakeHead.x < 0){
            snakeHead.x = boardWidth/tileSize - 1; // Wrap around to the right of the board
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // change the position of the snake head
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            // Prevent snake from moving in the opposite direction
            velocityX = 0; // Move snake head up
            velocityY = -1; // Move snake head up
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
            // Prevent snake from moving in the opposite direction  
            velocityX = 0; // Move snake head down
            velocityY = 1; // Move snake head down
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            // Prevent snake from moving in the opposite direction      
            velocityX = -1; // Move snake head left
            velocityY = 0; // Move snake head left
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1; // Move snake head right
            velocityY = 0; // Move snake head right
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
