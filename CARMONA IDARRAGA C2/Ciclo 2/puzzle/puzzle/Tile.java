import java.util.ArrayList;

public class Tile extends Rectangle {
    private int row;
    private int col;
    private boolean isGlued;
    private boolean isRoot;
    private boolean isLeaky;
    private Tile tile;
    private Tile[] adjacentTiles;

    public Tile() {
        this.isGlued = false;
        this.isRoot = false;
        this.isLeaky = false;
        this.tile = null;

    }

    public void setGlued() {
        this.isGlued = true;
    }

    public void setUnglued() {
        this.isGlued = false;
    }

    public void setRoot() {
        this.isRoot = true;
    }

    public boolean getGlued() {
        return this.isGlued;
    }

    public boolean getRoot() {
        return this.isRoot;
    }

    public void setLeaked() {
        this.isLeaky = true;
    }

    public int getRow() {return row;}

    public int getCol() {return col;}

    public boolean hasHole() {
        return this.isLeaky;
    }

    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

}


