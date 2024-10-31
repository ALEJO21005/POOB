package shapes;
import java.awt.Color;

public abstract class Tile extends Rectangle {
    private int row;
    private int col;
    private boolean isGlued;
    protected Circle Glue;
    private boolean isRoot;
    private boolean isLeaky;

    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.isGlued = false;
        this.isRoot = false;
        this.isLeaky = false;
        this.Glue = null;
    }


    public abstract void relocate(String color);
    public abstract void delete(String color);
    public abstract void move(String color);

    public int getRow() {return this.row;}
    public int getCol() {return this.col;}

    public void setRow(int row) {this.row = row;}
    public void setCol(int col) {this.col = col;}


    public void setGlued() {
        this.isGlued = true;
    }

    public void setUnglued() {
        this.isGlued = false;
    }

    public void setRoot(boolean condition) {
        this.isRoot = condition;
    }

    public boolean getGlued() {
        return this.isGlued;
    }

    public boolean getRoot() {
        return this.isRoot;
    }

    public void addGlue(Circle c) {
        this.Glue = c;
    }

    public void setLeaked() {
        this.isLeaky = true;
    }

    public boolean hasHole() {
        return this.isLeaky;
    }


    public final void showGlue(boolean condition) {
        if (Glue != null) {
            if (condition) {
                Glue.makeVisible();
            } else { Glue.makeInvisible(); }
        }
    }

    public final void removeCircleGlue() {
        if (this.Glue!=null) {
            this.Glue.makeInvisible();
            this.Glue = null;
        }
    }

}


