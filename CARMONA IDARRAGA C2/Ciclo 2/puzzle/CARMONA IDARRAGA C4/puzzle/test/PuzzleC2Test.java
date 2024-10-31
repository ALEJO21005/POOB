package test;

import org.junit.Test;
import puzzle.*;
import static org.junit.Assert.*;

public class PuzzleC2Test {

    @Test
    public void accordingCIshouldInitializeBoard() {
        Puzzle puzzle = new Puzzle(3, 3);

        assertNotNull(puzzle.getStarting());
        assertNotNull(puzzle.getEnding());
        assertEquals(3, puzzle.getStarting().length);
        assertEquals(3, puzzle.getStarting()[0].length);
        assertEquals(3, puzzle.getEnding().length);
        assertEquals(3, puzzle.getEnding()[0].length);
    }

    @Test
    public void accordingCIshouldInitializePuzzleGivenBoard() {
        char[][] starting = {{'r', 'b', '.'}, {'.', 'g', 'y'}, {'.', '.', '.'}};
        char[][] ending = {{'b', 'y', 'r'}, {'g', '.', '.'}, {'.', '.', '.'}};

        Puzzle puzzle = new Puzzle(starting, ending);
        assertArrayEquals(starting, puzzle.getStarting());
        assertArrayEquals(ending, puzzle.getEnding());
    }

    @Test
    public void accordingCITiltShouldNotMoveRightWhenNoSpace() {
        char[][] starting = {{'r', 'b', 'g'}, {'y', 'b', 'r'}, {'g', 'b', 'y'}};
        Puzzle puzzle = new Puzzle(starting, starting);

        puzzle.tilt('r');
        assertArrayEquals(starting, puzzle.getStarting());
    }

    @Test
    public void accordingCITiltShouldMoveLeftWhenThereIsASpace() {
        char[][] starting = {{'.', 'b', 'y'}, {'.', '.', 'r'}, {'g', '.', 'y'}};
        char[][] expected = {{'b', 'y', '.'}, {'r', '.', '.'}, {'g', 'y', '.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.tilt('l');
        assertArrayEquals(expected, puzzle.getStarting());
    }


    @Test
    public void accordingCIShouldRelocateTileCorrectly() {
        char[][] starting = {{'.','r'}, {'.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int [] from = {0,1};
        int [] to = {1,0};
        puzzle.relocateTile(from, to);
        assertArrayEquals(starting, puzzle.getStarting());
}

    @Test
    public void accordingCIShouldShowIsGoal(){
        char[][] starting = {{'.', 'r', 'b', 'g'}, {'y', '.', '.', 'r'}, {'b', 'g', 'y', '.'}, {'.', 'r', 'b', 'g'}};
        char[][] ending = {{'.', 'r', 'b', 'g'}, {'y', '.', '.', 'r'}, {'b', 'g', 'y', '.'}, {'.', 'r', 'b', 'g'}};
        Puzzle puzzle = new Puzzle(starting, ending);
        assertArrayEquals(starting, puzzle.getEnding());
    }


    @Test
    public void accordingCIShouldMakeAHoleInBlackTile(){
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int row = 0;
        int col = 0;
        puzzle.makeHole(row, col);
        assertTrue(puzzle.baldosasStarting[row][col].hasHole());
    }

    @Test
    public void accordingCIShouldNotMakeAHoleInATile(){
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        int row = 0;
        int col = 1;
        puzzle.makeHole(row, col);
        assertFalse(puzzle.baldosasStarting[row][col].hasHole());
    }

    @Test
    public void accordingCIShouldAddGlue() {
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(0,1);
        assertTrue(puzzle.baldosasStarting[0][1].getGlued());
    }

    @Test
    public void accordingCIShouldNotAddGlue() {
        char[][] starting = {{'.','b','r'}, {'b','g','y'}, {'.','.','.'}};
        Puzzle puzzle = new Puzzle(starting, starting);
        puzzle.addGlue(0,0);
        assertFalse(puzzle.baldosasStarting[0][0].getGlued());
    }





    //@Test
    //public void
}
