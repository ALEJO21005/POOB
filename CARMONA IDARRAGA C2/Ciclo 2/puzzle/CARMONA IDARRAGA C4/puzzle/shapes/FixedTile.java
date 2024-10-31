package shapes;
import javax.swing.*;


public class FixedTile extends Tile {
    private boolean isFixedTile;

    public FixedTile(int row, int column) {
        super(row, column);
        changeColor("magenta");
        isFixedTile = false;
    }

    @Override
    public void relocate(String color) {
        JOptionPane.showMessageDialog(null, "Las 'FixedTiles' no se pueden reubicar");
    }

    @Override
    public void delete(String color) {
        JOptionPane.showMessageDialog(null, "Las 'FixedTiles' no se pueden eliminar");
    }

    @Override
    public void move(String color) {

    }


    public void setFixedTile() {this.isFixedTile = true;}
    public boolean isFixedTile() {return this.isFixedTile;}


}
