package shapes;


public class NormalTile extends Tile {
    public NormalTile(int row, int col) {
        super(row, col);

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

}


