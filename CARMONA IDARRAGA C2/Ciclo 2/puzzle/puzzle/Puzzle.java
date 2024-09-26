import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Un puzzle que simula el problema de Tilting Tiles
 *
 * @author  Santiago Carmona, Sergio Idarraga
 * @version 1.0  (08 Sept 2024)()
 */

public class Puzzle {
    private int h;
    private int w;
    private char[][] starting;
    private char[][] ending;
    private boolean isVisible;
    private Tile[][] baldosasStarting;
    private Tile[][] baldosasEnding;
    Scanner sc = new Scanner(System.in);
    private boolean isFinished;

    /**
     * Crea un nuevo Puzzle generando los tableros de forma aleatoria e indicado solo las dimensiones.
     */
    public Puzzle(int h, int w) {
        this.h = h;
        this.w = w;
        this.starting = createMatrix(h, w);
        this.ending = createMatrix(h, w);
        this.isVisible = false;
        this.baldosasStarting = new Tile[h][w];
        this.baldosasEnding = new Tile[h][w];
        this.push(this.starting, this.baldosasStarting);
        this.endingPush(this.ending, this.baldosasEnding);
        this.isFinished = false;
    }

    /**
     * Crea un nuevo Puzzle indicado el tablero inicial y final
     */
    public Puzzle(char[][] starting, char[][] ending) {
        this.isVisible = false;
        this.starting = starting;
        this.ending = ending;
        this.baldosasStarting = new Tile[this.starting.length][this.starting[0].length];
        this.baldosasEnding = new Tile[this.starting.length][this.starting[0].length];
        this.push(this.starting, this.baldosasStarting);
        this.endingPush(this.ending, this.baldosasEnding);
        this.isFinished = false;
    }

    /**
     * Crea un nuevo Puzzle indicado solamente el tablero final
     */
    public Puzzle(char[][] ending) {
        this.isVisible = false;
        this.ending = ending;
        this.starting = new char[this.ending.length][this.ending[0].length];
        this.baldosasStarting = new Tile[this.starting.length][this.starting[0].length];
        this.baldosasEnding = new Tile[this.ending.length][this.ending[0].length];
        this.push(this.starting, this.baldosasStarting);
        this.endingPush(this.ending, this.baldosasEnding);
        this.isFinished = false;

        this.starting = createMatrix(this.ending.length, this.ending[0].length);
    }

    private void push(char[][] tablero, Tile[][] baldosas) {
        int height = 300 / tablero.length;
        int width = 300 / tablero[0].length;
        boolean condition = false;

        for (int i = 0; i < tablero.length; i++) {
            Tile baldosaInicial = new Tile();
            baldosaInicial.changeColor(calculateColor(this.starting[i][0]));
            baldosaInicial.changeSize(height, width);

            if (condition) {
                baldosaInicial.setyPosition(baldosas[i - 1][getArrayPosition(baldosas[i - 1])].getyPosition());
                baldosaInicial.moveVertical(height + 6);
            }
            baldosas[i][0] = baldosaInicial;

            for (int j = 1; j < tablero[i].length; j++) {
                Tile baldosa = new Tile();
                baldosa.changeColor(calculateColor(this.starting[i][j]));
                baldosa.changeSize(height, width);
                baldosa.setxPosition(baldosas[i][getArrayPosition(baldosas[i])].getxPosition() + width + 6);
                baldosa.setyPosition(baldosas[i][getArrayPosition(baldosas[i])].getyPosition());
                baldosas[i][j] = baldosa;
            }
            condition = true;
        }
    }

    private void endingPush(char [][] tablero, Tile[][] baldosas){
        char direction;

        for (int i = 0; i < baldosasStarting.length; i++) {
            for (int j = 0; j < baldosasStarting[i].length; j++) {
                Tile baldosa = new Tile();
                baldosa.changeColor(baldosasStarting[i][j].getColor());
                baldosa.changeSize(baldosasStarting[i][j].getHeight(), baldosasStarting[i][j].getWidth());
                baldosa.setxPosition(baldosasStarting[i][j].getxPosition() + 500);
                baldosa.setyPosition(baldosasStarting[i][j].getyPosition());
                baldosas[i][j] = baldosa;
            }
        }

        for (int j = 0; j < (Math.random() * 5) + 1; j++) {
            int move = (int) (Math.random() * 5);
            switch (move) {
                case 0:
                    direction = 'r';
                    this.tiltEnding(direction);
                    break;
                case 1:
                    direction = 'l';
                    this.tiltEnding(direction);
                case 2:
                    direction = 'u';
                    this.tiltEnding(direction);
                case 3:
                    direction = 'd';
                    this.tiltEnding(direction);
            }
        }
    }


    private  int getArrayPosition(Tile[] Array) {
        int pos = -1;
        for (int i = Array.length - 1; i >= 0; i--) {
            if (Array[i] != null) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public char[][] getStarting(){
        return this.starting;
    }

    public char[][] getEnding(){
        return this.ending;
    }

    private String calculateColor(char color) {
        switch (color) {
            case 'r':
                return "red";
            case 'g':
                return "green";
            case 'b':
                return "blue";
            case 'y':
                return "yellow";
            case '.':
                return "black";
            default:
                return null;
        }
    }

    private char[][] createMatrix(int rows, int columns) {
        char[][] temp = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int n = (int) (Math.random() * 8);
                switch (n) {
                    case 0:
                        temp[i][j] = 'r';
                        break;
                    case 1:
                        temp[i][j] = 'b';
                        break;
                    case 2:
                        temp[i][j] = 'g';
                        break;
                    case 3:
                        temp[i][j] = 'y';
                        break;
                    case 4, 5, 6, 7:
                        temp[i][j] = '.';
                        break;
                }
            }

        }
        return temp;
    }

    /**
     * Permite hacer visible los puzzles
     */
    public void makeVisible() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }

        this.isVisible = true;

        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (Tile baldosa : this.baldosasStarting[i]) {
                baldosa.makeVisible();
            }
        }
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (Tile baldosa : this.baldosasEnding[i]) {
                baldosa.makeVisible();
            }
        }
    }

    /**
     * Permite hacer invisible los puzzles
     */
    public void makeInvisible() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        this.isVisible = false;
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (Tile baldosa : this.baldosasStarting[i]) {
                baldosa.makeInvisible();
            }
        }
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (Tile baldosa : this.baldosasEnding[i]) {
                baldosa.makeInvisible();
            }
        }
    }

    /**
     * Añade una nueva baldosa al tablero
     */
    public void addTile(int row, int column, char color) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        if (color != 'r' && color != 'g' && color != 'b' && color != 'y') {
            JOptionPane.showMessageDialog(null, "Color incorrecto");
        } else if (!this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "Ya existe una ficha en esa posición");
        }
        else {
            this.baldosasStarting[row][column].changeColor(calculateColor(color));
        }
    }

    /**
     * Elimina una baldosa del puzzle
     */
    public void deleteTile (int row, int column) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        if (this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No hay ninguna ficha en la posición indicada.");
        } else {
            this.baldosasStarting[row][column].changeColor("black");
        }
    }

    /**
     * Mueve de lugar una baldosa indicada por teclado
     */
    public void relocateTile (int[] from, int[] to) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            int row1 = from[0];
            int column1 = from[1];
            int row2 = to[0];
            int column2 = to[1];

            if (this.baldosasStarting[row1][column1].getColor().equals("black") || !this.baldosasStarting[row2][column2].getColor().equals("black")) {
                JOptionPane.showMessageDialog(null, "No se puede realizar el movimiento");
            } else {
                this.baldosasStarting[row2][column2].changeColor(this.baldosasStarting[row1][column1].getColor());
                this.baldosasStarting[row1][column1].changeColor("black");
            }
        }

    }

    /**
     * Gira el tablero en la dirección indicada por teclado
     */
    public void tilt(char direction) {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
            return;
        }
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            int moves = calculateMoves(this.baldosasStarting[i], direction);
            switch (direction) {
                case 'r':
                    if (isRoot(this.baldosasStarting[i])) {
                        this.baldosasStarting[i] = moveRight(this.baldosasStarting[i], moves, true);
                        moves = min(minMoves(i, 'r'));
                        tiltGlued(i, direction);
                    }
                    if ((isRoot(this.baldosasStarting[i])) || !isGlued(this.baldosasStarting[i])) {
                        this.baldosasStarting[i] = moveRight(this.baldosasStarting[i], moves, false);
                    }
                    break;
                case 'l':
                    moves = calculateMoves(this.baldosasStarting[i], 'l');
                    if (isRoot(this.baldosasStarting[i])) {
                        this.baldosasStarting[i] = moveLeft(this.baldosasStarting[i], moves, true);
                        moves = min(minMoves(i, 'l'));
                        tiltGlued(i, direction);
                    }

                    if ((isRoot(this.baldosasStarting[i])) || !isGlued(this.baldosasStarting[i])) {
                        //System.out.println("i: " + i);
                        //System.out.println(moves);
                        this.baldosasStarting[i] = moveLeft(baldosasStarting[i], moves, false);
                    }
                    break;
                case 'u':

                    this.baldosasStarting = moveUp(baldosasStarting);
                    break;
                case 'd':
                    this.baldosasStarting = moveDown(baldosasStarting);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Dirección incorrecta");
                    break;
            }
        }
    }

    private ArrayList<Integer> minMoves(int i, char direction) {
        ArrayList<Integer> cantidadMovimientos = new ArrayList<>();
        int j = indexForColumn(this.baldosasStarting[i]);

        //System.out.println("i: " + calculateMoves(this.baldosasStarting[i],direction ));
        if (calculateMoves(this.baldosasStarting[i], direction) / getCount(this.baldosasStarting[i]) <= 0) {
            cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i], direction));
        } else {cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i], direction) / getCount(this.baldosasStarting[i]));}

        if (i > 0 && !this.baldosasStarting[i - 1][j].getColor().equals("black")) {
            //System.out.println("i - 1: " + calculateMoves(this.baldosasStarting[i - 1], direction));
            if (calculateMoves(this.baldosasStarting[i - 1], direction) / getCount(this.baldosasStarting[i - 1]) <= 0) {
                cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i - 1], direction));
            } else {cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i - 1], direction) / getCount(this.baldosasStarting[i - 1]));}
        }

        if (i < this.baldosasStarting.length - 1 && !this.baldosasStarting[i + 1][j].getColor().equals("black")) {
            //System.out.println("i + 1: " + calculateMoves(this.baldosasStarting[i + 1], direction));
            if ((calculateMoves(this.baldosasStarting[i + 1], direction)) / getCount(this.baldosasStarting[i + 1]) <= 0) {
                cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i + 1], direction));
            } else {cantidadMovimientos.add(calculateMoves(this.baldosasStarting[i + 1], direction) / getCount(this.baldosasStarting[i + 1]));}
        }
        return cantidadMovimientos;
    }

    private void tiltGlued(int i, char direction) {
        int moves = min(minMoves(i, direction));

        if (moves != 0) {
            switch (direction) {
                case 'r':
                    moveRight(i, moves);
                case 'l':
                    moveLeft(i, moves);
                //case 'u':
                  //  moveUp(i, moves);
                //case 'd':
                  //  moveDown(i, moves);
            }
        }
    }



    private void moveRight(int i, int moves) {
        int j = indexForColumn(this.baldosasStarting[i]);
        if (i > 0 && !this.baldosasStarting[i - 1][j].getColor().equals("black")) {
            this.baldosasStarting[i - 1] = moveRight(baldosasStarting[i - 1], calculateMoves(this.baldosasStarting[i - 1], 'r'), true);
            this.baldosasStarting[i - 1] = moveRight(baldosasStarting[i - 1], moves, false);
        }

        if (i < this.baldosasStarting.length - 1 && !this.baldosasStarting[i + 1][j].getColor().equals("black")) {
            this.baldosasStarting[i + 1] = moveRight(baldosasStarting[i + 1], calculateMoves(this.baldosasStarting[i + 1], 'r'), true);
            this.baldosasStarting[i + 1] = moveRight(baldosasStarting[i + 1], moves, false);
        }

    }

    private void moveLeft(int i, int moves) {
        int j = indexForColumn(this.baldosasStarting[i]);
        if (i > 0 && !this.baldosasStarting[i - 1][j].getColor().equals("black")) {
            this.baldosasStarting[i - 1] = moveLeft(baldosasStarting[i - 1], calculateMoves(this.baldosasStarting[i - 1], 'l'), true);
            this.baldosasStarting[i - 1] = moveLeft(baldosasStarting[i - 1], moves, false);
        }

        if (i < this.baldosasStarting.length - 1 && !this.baldosasStarting[i + 1][j].getColor().equals("black")) {
            this.baldosasStarting[i + 1] = moveLeft(baldosasStarting[i + 1], calculateMoves(this.baldosasStarting[i + 1], 'l'), true);
            this.baldosasStarting[i + 1] = moveLeft(baldosasStarting[i + 1], moves, false);
        }
    }


    //{{'r', 'g', 'y', '.'}, {'b', '.', '.', '.'}, {'g', 'b', '.', '.'}, {'g', 'b', '.', '.'}}



    private int min(ArrayList<Integer> Array) {
        int minimo = Array.get(0);
        for (int i = 1; i < Array.size(); i++) {
            if (Array.get(i) < minimo) {
                minimo = Array.get(i);
            }
        }
        return minimo;
    }

    private boolean isRoot(Tile[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].getRoot()) {
                return true;
            }
        }
        return false;
    }


    private boolean isGlued(Tile[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].getGlued()) {
                return true;
            }
        }
        return false;
    }


    private void tiltEnding(char direction) {
        for (int i = 0; i < this.baldosasEnding.length; i++) {
            int moves = calculateMoves(this.baldosasEnding[i], direction);
            switch (direction) {
                case 'r':
                    this.baldosasEnding[i] = moveRight(baldosasEnding[i], moves, false);
                    break;
                case 'l':
                    this.baldosasEnding[i] = moveLeft(baldosasEnding[i], moves, false);
                    break;
                case 'u':
                    this.baldosasEnding = moveUp(baldosasEnding);
                    break;
                case 'd':
                    this.baldosasEnding= moveDown(baldosasEnding);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Dirección incorrecta");
                    break;
            }
        }
    }

    private int calculateMoves(Tile[] row, char direction) {
        int cantidadFichas = getCount(row);
        int[] fichas = getPosicionFichas(cantidadFichas, row);
        int movimientos = getMoves(cantidadFichas, row, fichas, direction);
        return movimientos;
    }

    private static void printAsPythonArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

        private Tile[] moveRight(Tile[] row, int movimientos, boolean countGlued) {
            if (countGlued) {
                for (int i = 0; i < movimientos; i++) {
                    for (int col = row.length - 1; col > 0; col--) {
                        if (row[col].getColor().equals("black") && (!row[col - 1].getColor().equals("black") && !row[col - 1].getGlued())) {
                            row[col].changeColor(row[col - 1].getColor());
                            row[col - 1].changeColor("black");
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < movimientos; i++) {
                    for (int col = row.length - 1; col > 0; col--) {
                        if (row[col].getColor().equals("black") && (!row[col - 1].getColor().equals("black"))) {
                            row[col].changeColor(row[col - 1].getColor());
                            row[col - 1].changeColor("black");
                        }
                        if(row[col].hasHole()) {
                            row[col].changeColor("black");
                            showHoleAgain();
                        }
                    }
                }
            }

            return row;
        }

    private Tile[] moveLeft(Tile[] row, int movimientos, boolean countGlued) {
        if (countGlued) {
            for (int i = 0; i < movimientos; i++) {
                for (int col = 0; col < row.length - 1; col++) {
                    if (row[col].getColor().equals("black") && (!row[col + 1].getColor().equals("black") && !row[col + 1].getGlued())) {
                        row[col].changeColor(row[col + 1].getColor());
                        row[col + 1].changeColor("black");
                    }
                }
            }
        } else {
            for (int i = 0; i < movimientos; i++) {
                for (int col = 0; col < row.length - 1; col++) {
                    if (row[col].getColor().equals("black") && !row[col + 1].getColor().equals("black")) {
                        row[col].changeColor(row[col + 1].getColor());
                        row[col + 1].changeColor("black");
                        if(row[col].hasHole()) {
                            row[col].changeColor("black");
                            showHoleAgain();
                        }
                    }
                }
            }
        }
        return row;
    }


    private Tile[][] moveUp(Tile[][] board) {
        if (board.length == 1) {
            return board;
        }
        // Recorre cada columna del tablero
        for (int col = 0; col < board[0].length; col++) {
            int cantidadFichas = getCountColumn(col, board); // Cuenta las fichas no negras en la columna
            int[] fichas = getPosicionFichasColumn(cantidadFichas, col, board); // Obtén las posiciones de las fichas
            int movimientos = getMovesColumn(cantidadFichas, col, board, fichas, 'u'); // Calcula los movimientos necesarios

            // Ejecuta el número total de movimientos para esta columna
            for (int i = 0; i < movimientos + 1; i++) {
                for (int row = 0; row < board.length - 1; row++) {
                    // Si la celda actual es negra y la celda de abajo no lo es, intercambia colores
                    if (board[row][col].getColor().equals("black") && !board[row + 1][col].getColor().equals("black")) {
                        board[row][col].changeColor(board[row + 1][col].getColor()); // Cambia el color de la celda actual al de la celda de abajo
                        board[row + 1][col].changeColor("black"); // La celda de abajo se vuelve negra
                    }
                    if(board[row][col].hasHole()) {
                        board[row][col].changeColor("black");
                        showHoleAgain();
                    }
                }
            }
        }
        return board;
    }

    private Tile[][] moveDown(Tile[][] board) {
        if (board.length == 1) {
            return board;
        }
        // Recorre cada columna del tablero
        for (int col = 0; col < board[0].length; col++) {
            int cantidadFichas = getCountColumn(col, board); // Cuenta las fichas no negras en la columna
            int[] fichas = getPosicionFichasColumn(cantidadFichas, col, board); // Obtén las posiciones de las fichas
            int movimientos = getMovesColumn(cantidadFichas, col, board, fichas, 'd'); // Calcula los movimientos necesarios

            // Ejecuta el número total de movimientos para esta columna
            for (int i = 0; i < movimientos + 1; i++) {
                for (int row = board.length - 1; row > 0; row--) {
                    // Si la celda actual es negra y la celda de arriba no lo es, intercambia colores
                    if (board[row][col].getColor().equals("black") && !board[row - 1][col].getColor().equals("black")) {
                        board[row][col].changeColor(board[row - 1][col].getColor()); // Cambia el color de la celda actual al de la celda de arriba
                        board[row - 1][col].changeColor("black"); // La celda de arriba se vuelve negra
                    }
                    if(board[row][col].hasHole()) {
                        board[row][col].changeColor("black");
                        showHoleAgain();
                    }
                }
            }
        }

        return board;
    }

    private static int getCount(Tile[] row) {
        int count = 0;
        for (int i = 0; i < row.length; i++) {
            if (!row[i].getColor().equals("black")) {
                count ++;
            }
        }
        return count;
    }

    private static int getCountColumn(int col, Tile[][] board) {
        int count = 0;
        for (int i = 0; i < board[0].length; i++) {
            if (!board[col][i].getColor().equals("black")) {
                count ++;
            }
        }
        return count;
    }

    private static int[] getPosicionFichas(int count, Tile[] row) {
        int index = 0;
        int[] posicionFichas = new int[count];
        for (int i = 0; i < row.length; i++) {
            if (!row[i].getColor().equals("black")) {
                posicionFichas[index] = i;
                index ++;
            }
        }
        return posicionFichas;
    }

    private static int[] getPosicionFichasColumn(int count, int col, Tile[][] board) {
        int index = 0;
        int[] posicionFichas = new int[count];
        for (int i = 0; i < board[0].length; i++) {
            if (!board[col][i].getColor().equals("black")) {
                posicionFichas[index] = i;
                index ++;
            }
        }
        return posicionFichas;
    }

    private static int getMoves(int count, Tile[] row, int[] fichas, char direction) {
        int totalMovimientos = 0;
        switch (direction) {
            case 'r':
                for (int i = 0; i < fichas.length; i++) {
                    for (int j = fichas[i]; j < row.length; j++) {
                        if (row[j].getColor().equals("black")) {
                            totalMovimientos++;
                        }
                    }
                }
                break;
            case 'l':
                for (int i = 0; i < fichas.length; i++) {
                    for (int j = fichas[i]; j >= 0; j--) {
                        if (row[j].getColor().equals("black")) {
                            totalMovimientos++;
                        }
                    }
                }
                break;
            }
        return totalMovimientos;
    }


    private static int getMovesColumn(int count, int col, Tile[][] board, int[] fichas, char direction) {
        int totalMovimientos = 0;
        switch (direction) {
            case 'd':
                for (int i = 0; i < fichas.length; i++) {
                    for (int j = fichas[i]; j < board[0].length; j++) {
                        if (board[j][i].getColor().equals("black")) {
                            totalMovimientos++;
                        }
                    }
                }
            case 'u':
                for (int i = 0; i < fichas.length; i++) {
                    for (int j = fichas[i]; j > 0; j--) {
                        if (board[j][i].getColor().equals("black")) {
                            totalMovimientos++;
                        }
                    }
                }
        }
        return totalMovimientos;
    }
    /**
     * Retorna si el tablero inicial es igual al tablero final
     */
    public boolean isGoal() {
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            for (int i = 0; i < baldosasStarting.length; i++) {
                for (int j = 0; j < baldosasStarting[i].length; j++) {
                    if (!baldosasStarting[i][j].getColor().equals(baldosasEnding[i][j].getColor())) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void print(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Imprime el estado actual del tablero en formato matriz
     */
    public char[][] actualArrangement() {
        char[][] matrixActual = new char[this.baldosasStarting.length][this.baldosasStarting[0].length];
        if (this.isFinished) {
            JOptionPane.showMessageDialog(null, "No puedes realizar la acción porque el programa ha finalizado");
        } else {
            for (int i = 0; i < this.baldosasStarting.length; i++) {
                for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                    String color = baldosasStarting[i][j].getColor();
                    if (color.equals("black")) {
                        matrixActual[i][j] = '.';
                    } else {
                        matrixActual[i][j] = color.charAt(0);
                    }
                }
            }
            print(matrixActual);
        }
        return matrixActual;
    }

    private int indexForRow(Tile[] row) {
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            if (this.baldosasStarting[i].equals(row)) {
                return i;
            }
        }
        return -1;

    }


    private int indexForColumn(Tile[] row) {
        for (int i = 0; i < row.length;i++) {
            if (row[i].getRoot()) {
                return i;
            }
        }
        return -1;
    }



    public void addGlue(int row, int column) {

        if (this.baldosasStarting[row][column].getRoot()) {
            JOptionPane.showMessageDialog(null, "La baldosa ya tiene pegamento aplicado");
        } else if (!this.baldosasStarting[row][column].getRoot() && this.baldosasEnding[row][column].getGlued()) {
            JOptionPane.showMessageDialog(null, "La baldosa ya está pegada a otra baldosa");
        } else if (this.baldosasStarting[row][column].getColor().equals("black")) {
            JOptionPane.showMessageDialog(null, "No hay ninguna baldosa en esta posición");
        }
        else {
            this.baldosasStarting[row][column].setGlued();
            this.baldosasStarting[row][column].setRoot();

            if (row > 0 && !this.baldosasStarting[row - 1][column].getColor().equals("black")) {
                this.baldosasStarting[row - 1][column].setGlued();
            }

            if (column > 0 && !this.baldosasStarting[row][column - 1].getColor().equals("black")) {
                this.baldosasStarting[row][column - 1].setGlued();
            }

            if (row < this.baldosasStarting.length - 1 && !this.baldosasStarting[row + 1][column].getColor().equals("black")) {
                this.baldosasStarting[row + 1][column].setGlued();
            }

            if (column < this.baldosasStarting[0].length - 1 &&  !this.baldosasStarting[row][column + 1].getColor().equals("black")) {
                this.baldosasStarting[row][column + 1].setGlued();
            }
        }
    }




    // PENDIENTE
    private void pushCharMatrix(char[][] matrixReference, char[][] tablero) {
        for (int i = 0; i < matrixReference.length; i++) {
            for (int j = 0; j < matrixReference[i].length; j++) {
                tablero[i][j] = matrixReference[i][j];
            }
        }
    }

    // PENDIENTE
    private void exchangeCharMatrix() {
        char[][] tempTablero = new char[this.starting.length][this.starting[0].length];
        pushCharMatrix(tempTablero, this.starting);

        for (int i = 0; i < this.starting.length; i++) {
            for (int j = 0; j < this.starting[i].length; j++) {
                this.starting[i][j] = this.ending[i][j];
            }
        }

        for (int i = 0; i < this.starting.length; i++) {
            for (int j = 0; j < this.starting[i].length; j++) {
                this.ending[i][j] = tempTablero[i][j];
            }
        }

    }

    public void exchange() {
        Tile[][] tempTablero = new Tile[this.baldosasStarting.length][this.baldosasStarting[0].length];
        push(this.starting, tempTablero);

        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (int j = 0; j < this.baldosasEnding[i].length; j++) {
                this.baldosasStarting[i][j].changeColor(this.baldosasEnding[i][j].getColor());
            }
        }

        for (int i = 0; i < this.baldosasEnding.length; i++) {
            for (int j = 0; j < this.baldosasEnding[i].length; j++) {
                this.baldosasEnding[i][j].changeColor(tempTablero[i][j].getColor());
            }
        }
    }

    public void makeHole(int row, int column) {
        int fila = row;
        int columna = column;

        Circle.addHolePosition(row, column);

        if (this.baldosasStarting[row][column].getColor().equals("black")) {
            this.baldosasStarting[row][column].setLeaked();
            Circle hole = new Circle();
            hole.setxPosition(this.baldosasStarting[row][column].getxPosition());
            hole.setyPosition(this.baldosasStarting[row][column].getyPosition());
            hole.setDiameter(this.baldosasStarting[row][column].getHeight());
            hole.makeVisible();
        } else {
            JOptionPane.showMessageDialog(null, "Solamente puedes agujerear celdas vacías");
        }
    }

    private void showHoleAgain() {
        for(int[] hole:Circle.getHolePositions()){
            int holeRow = hole[0];
            int holeColumn = hole[1];
            Circle holeA = new Circle();
            holeA.setxPosition(this.baldosasStarting[holeRow][holeColumn].getxPosition());
            holeA.setyPosition(this.baldosasStarting[holeRow][holeColumn].getyPosition());
            holeA.setDiameter(this.baldosasStarting[holeRow][holeColumn].getHeight());
            holeA.makeVisible();
        }

    }

    public void misplacedTiles() {
        int count = 0;
        for (int i = 0; i < this.baldosasStarting.length; i++) {
            for (int j = 0; j < this.baldosasStarting[i].length; j++) {
                if (!this.baldosasStarting[i][j].getColor().equals(this.baldosasEnding[i][j].getColor()) && !this.baldosasStarting[i][j].getColor().equals("black")) {
                    //System.out.println("Baldosa mal colocada: [" + i + "][" + j + "] - Color inicial: " + this.baldosasStarting[i][j].getColor() + ", Color final: " + this.baldosasEnding[i][j].getColor());
                    count++;
                }
            }
        }
        System.out.println("La cantidad de baldosas que no están en su posición son: " + count);
    }
    //{{'y','.','g','.'},{'.','r','r','r'},{'.','b','.','.'},{'.','.','.','.'}}
    //{{'.','.','.','.'},{'.','.','.','g'},{'.','.','y','r'},{'.','r','r','b'}}

    /**
     * Finaliza la ejecución del programa y no se puede hacer ningún otro movimiento.
     */
    public void finish() {
        this.isFinished = true;
    }
}

