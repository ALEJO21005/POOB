package shapes;

import javax.swing.*;
import java.awt.*;


public class FreelanceTile extends Tile {

    public FreelanceTile(int row, int col) {
        super(row, col);
        changeColor("white");

    }

    @Override
    public void relocate(String color) {
        changeColor(color);
    }

    @Override
    public void delete(String color) {
        changeColor(color);
    }

    @Override
    public void move(String color) {
        changeColor(color);
    }

    @Override
    public void setGlued() {
        JOptionPane.showMessageDialog(null, "Las 'FreelanceTile' no permiten añadirle pegamento");
    }

    @Override
    public void setUnglued() {
        
    }

    @Override
    public void setRoot(boolean condition) {

    }

    @Override
    public void addGlue(Circle c) {
    }
}
