import org.junit.Test;
import static org.junit.Assert.*;

public class PuzzleC2Test {

    @Test
    public void accordingCIshouldInitializeBoard() {
        Puzzle puzzle = new Puzzle(3,3);

        assertNotNull(puzzle.getStarting());
        assertNotNull(puzzle.getEnding());
        assertEquals(3, puzzle.getStarting().length);
        assertEquals(3, puzzle.getStarting()[0].length);
        assertEquals(3, puzzle.getEnding().length);
        assertEquals(3, puzzle.getEnding()[0].length);
    }

    @Test
    public void accordingCIshouldInitializePuzzleGivenBoard() {
        char [][] starting = {{'r', 'b','.'}, {'.','g','y'}, {'.','.','.'}};
        char [][] ending = {{'b','y','r'},{'g','.','.'},{'.','.','.'}};

        Puzzle puzzle = new Puzzle(starting,ending);
        assertArrayEquals(starting, puzzle.getStarting());
        assertArrayEquals(ending, puzzle.getEnding());
    }

    @Test
    public void accordingCITiltShouldNotMoveRightWhenNoSpace() {
        char[][] starting = {{'r', 'b', 'g'}, {'y', 'b', 'r'}, {'g','b','y'}};
        Puzzle puzzle = new Puzzle(starting, starting);

        puzzle.tilt('r');
        assertArrayEquals(starting, puzzle.getStarting());
    }

}
