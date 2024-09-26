import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final double PI=3.1416;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private static ArrayList<int[]> holePositions = new ArrayList<>();

    public Circle() {
        this.diameter = 50;
        this.xPosition = 0;
        this.yPosition = 0;
        this.color = "white";
        this.isVisible = false;
    }
    

    public Circle(int diameter, int xPosition, int yPosition, String color) {
        this.diameter = diameter;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.color = color;
        this.isVisible = false;
    }

    public void setxPosition(int coord) {
        this.xPosition = coord;

    }


    public static void addHolePosition(int row, int column) {
        holePositions.add(new int[]{row, column});
    }

    public static ArrayList<int[]> getHolePositions() {
        return holePositions;
    }


    public void setyPosition(int coord) {
        this.yPosition = coord;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public void makeVisible(){
        isVisible = true;
        draw();
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    public double perimeter() {
        return PI * diameter;
    }

    public double change(char operator, int value) {
        double area = PI * Math.pow((diameter / 2), 2);
        if (operator == '+') {
            area += value;
        } else if (operator == '-') {
            area -= value;
        } else if (operator == '*') {
            area *= value;
        } else if (operator == '/') {
            area /= value;
        }
        return area;
    }

    /** public void roll(int times, int maxDistance) {
        * double distance = (Math.random() *  maxDistance);
        * double angle = (Math.random() * 90);
    }
     */

    
    
    /**
     * Calculate the area of the circle.
     */
    public double area() {
        return PI * Math.pow((diameter / 2), 2);
    }
    
    public void roll(int times, int maxDistance) {
    for (int i = 0; i < times; i++) {
        int distance = (int) (Math.random() * maxDistance);
        double angle = Math.random() * 90;
        double angleInRadians = Math.toRadians(angle);
        int deltaX = (int) (distance * Math.cos(angleInRadians));
        int deltaY = (int) (distance * Math.sin(angleInRadians));
        moveHorizontal(deltaX);
        moveVertical(deltaY);
    }
}

}

