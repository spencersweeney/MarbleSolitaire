package cs3500.marblesolitaire.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;

import cs3500.marblesolitaire.BadAppendable;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.MarbleSolitaireModelMock;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for playing a Marble Solitaire Game.
 */
public class MarbleSolitaireControllerImplTest {
  private MarbleSolitaireModel model1;
  private MarbleSolitaireModel european;
  private MarbleSolitaireModel triangular;
  private MarbleSolitaireModel mock;
  private MarbleSolitaireView view1;
  private MarbleSolitaireView europeanView;
  private MarbleSolitaireView triangularView;
  private MarbleSolitaireView badView;
  private StringBuffer out;
  private Readable input;
  private MarbleSolitaireController controller;
  private StringBuilder log;

  @Before
  public void setUp() {
    model1 = new EnglishSolitaireModel();

    european = new EuropeanSolitaireModel();

    triangular = new TriangleSolitaireModel();

    log = new StringBuilder();

    mock = new MarbleSolitaireModelMock(log);

    out = new StringBuffer();

    view1 = new MarbleSolitaireTextView(model1, out);

    europeanView = new MarbleSolitaireTextView(european, out);

    triangularView = new TriangleSolitaireTextView(triangular, out);

    badView = new MarbleSolitaireTextView(model1, new BadAppendable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadControllerNullModel() {
    MarbleSolitaireController badController =
            new MarbleSolitaireControllerImpl(null, view1, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadControllerNullView() {
    MarbleSolitaireController badController =
            new MarbleSolitaireControllerImpl(model1, null, new StringReader(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadControllerNullInput() {
    MarbleSolitaireController badController =
            new MarbleSolitaireControllerImpl(model1, view1, null);
  }

  @Test(expected = IllegalStateException.class)
  public void testIOError() {
    input = new StringReader("2 4 4 4 q");
    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(model1, badView, input);

    controller.playGame();
  }

  @Test
  public void testMoveInputs() {
    input = new StringReader("2 4 4 4 q");
    controller = new MarbleSolitaireControllerImpl(mock, view1, input);

    controller.playGame();
    assertEquals("fromRow = 1, fromCol = 3, toRow = 3, toCol = 3\n", log.toString());
  }


  @Test
  public void testPlayGame() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFirstPlace() {
    Reader in = new StringReader("s 2 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInSecondPlace() {
    Reader in = new StringReader("2 s 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInThirdPlace() {
    Reader in = new StringReader("2 4 s 4 4 5 4 3 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFourthPlace() {
    Reader in = new StringReader("2 4 4 s 4 5 4 3 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 30\n";
    assertEquals(output, out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInput() {
    Reader in = new StringReader("");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterValidMove() {
    Reader in = new StringReader("2 4 4 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMoves() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMovesAndABadInput() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4 s");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromLeftNotTwoAway() {
    Reader in = new StringReader("4 3 4 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromRightNotTwoAway() {
    Reader in = new StringReader("4 5 4 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromTopNotTwoAway() {
    Reader in = new StringReader("3 4 4 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromBottomNotTwoAway() {
    Reader in = new StringReader("5 4 4 4");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
  }

  @Test
  public void testInvalidMoveFromAllDirectionsThenQuit() {
    Reader inLeft = new StringReader("4 3 4 4 q");
    Reader inRight = new StringReader("4 5 4 4 q");
    Reader inTop = new StringReader("3 4 4 4 q");
    Reader inBottom = new StringReader("5 4 4 4 q");

    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(model1, view1, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(model1, view1, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(model1, view1, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(model1, view1, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveFromAllDirectionsFarAwayThenQuit() {
    Reader inLeft = new StringReader("4 1 4 4 q");
    Reader inRight = new StringReader("4 7 4 4 q");
    Reader inTop = new StringReader("1 4 4 4 q");
    Reader inBottom = new StringReader("7 4 4 4 q");

    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(model1, view1, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(model1, view1, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(model1, view1, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(model1, view1, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToAllDirectionsFromEmptyThenQuit() {
    Reader inLeft = new StringReader("4 4 4 2 q");
    Reader inRight = new StringReader("4 4 4 6 q");
    Reader inTop = new StringReader("4 4 4 2 q");
    Reader inBottom = new StringReader("4 4 6 4 q");

    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(model1, view1, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(model1, view1, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(model1, view1, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(model1, view1, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToMarbleFromAllDirectionsThenQuit() {
    Reader inLeft = new StringReader("4 3 4 5 q");
    Reader inRight = new StringReader("4 7 4 5 q");
    Reader inTop = new StringReader("2 4 4 5 q");
    Reader inBottom = new StringReader("6 4 4 5 q");

    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(model1, view1, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(model1, view1, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(model1, view1, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(model1, view1, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testGameOver() {
    this.model1.move(1, 3, 3, 3);
    this.model1.move(2, 1, 2, 3);
    this.model1.move(3, 3, 1, 3);
    this.model1.move(0, 3, 2, 3);
    this.model1.move(0, 2, 2, 2);
    this.model1.move(2, 3, 2, 1);
    this.model1.move(4, 2, 2, 2);
    this.model1.move(2, 1, 2, 3);
    this.model1.move(5, 3, 3, 3);
    this.model1.move(2, 3, 4, 3);
    this.model1.move(4, 0, 4, 2);
    this.model1.move(3, 0, 3, 2);
    this.model1.move(4, 2, 2, 2);
    this.model1.move(6, 2, 4, 2);
    this.model1.move(4, 3, 4, 1);
    this.model1.move(2, 5, 2, 3);
    this.model1.move(3, 5, 3, 3);
    this.model1.move(4, 5, 4, 3);
    this.model1.move(2, 3, 2, 1);
    this.model1.move(2, 0, 2, 2);
    this.model1.move(4, 3, 2, 3);
    this.model1.move(2, 3, 2, 1);
    this.model1.move(6, 4, 6, 2);

    input = new StringReader("1 5 3 5");
    controller = new MarbleSolitaireControllerImpl(model1, view1, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game over!", lines[lines.length - 9]);
    assertEquals("Score: 8", lines[lines.length - 1]);
  }


  @Test
  public void testSuccessfulMovesThenQuit() {
    input = new StringReader("2 4 4 4 3 2 3 4 4 4 2 4 1 4 3 4 1 3 3 3 3 4 3 2 q");
    controller = new MarbleSolitaireControllerImpl(model1, view1, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game quit!", lines[lines.length - 10]);
    assertEquals("State of game when quit:", lines[lines.length - 9]);
    assertEquals("    _ _ O", lines[lines.length - 8]);
    assertEquals("    _ _ O", lines[lines.length - 7]);
    assertEquals("O O _ _ O O O", lines[lines.length - 6]);
    assertEquals("O O O _ O O O", lines[lines.length - 5]);
    assertEquals("O O O O O O O", lines[lines.length - 4]);
    assertEquals("    O O O", lines[lines.length - 3]);
    assertEquals("    O O O", lines[lines.length - 2]);
    assertEquals("Score: 26", lines[lines.length - 1]);
  }

  @Test
  public void testQuitInputInFirstPlace() {
    Reader in = new StringReader("q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInSecondPlace() {
    Reader in = new StringReader("2 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInThirdPlace() {
    Reader in = new StringReader("2 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInFourthPlace() {
    Reader in = new StringReader("2 4 4 q");

    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model1, view1, in);
    controller.playGame();
    String output = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testPlayGameEuropean() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFirstPlaceEuropean() {
    Reader in = new StringReader("s 2 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInSecondPlaceEuropean() {
    Reader in = new StringReader("2 s 4 4 4 5 4 3 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInThirdPlaceEuropean() {
    Reader in = new StringReader("2 4 s 4 4 5 4 3 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFourthPlaceEuropean() {
    Reader in = new StringReader("2 4 4 s 4 5 4 3 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n";
    assertEquals(output, out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputEuropean() {
    Reader in = new StringReader("");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterValidMoveEuropean() {
    Reader in = new StringReader("2 4 4 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMovesEuropean() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMovesAndABadInputEuropean() {
    Reader in = new StringReader("2 4 4 4 5 4 3 4 s");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromLeftNotTwoAwayEuropean() {
    Reader in = new StringReader("4 3 4 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromRightNotTwoAwayEuropean() {
    Reader in = new StringReader("4 5 4 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromTopNotTwoAwayEuropean() {
    Reader in = new StringReader("3 4 4 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromBottomNotTwoAwayEuropean() {
    Reader in = new StringReader("5 4 4 4");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
  }

  @Test
  public void testInvalidMoveFromAllDirectionsThenQuitEuropean() {
    Reader inLeft = new StringReader("4 3 4 4 q");
    Reader inRight = new StringReader("4 5 4 4 q");
    Reader inTop = new StringReader("3 4 4 4 q");
    Reader inBottom = new StringReader("5 4 4 4 q");

    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(european, europeanView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(european, europeanView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(european, europeanView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(european, europeanView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveFromAllDirectionsFarAwayThenQuitEuropean() {
    Reader inLeft = new StringReader("4 1 4 4 q");
    Reader inRight = new StringReader("4 7 4 4 q");
    Reader inTop = new StringReader("1 4 4 4 q");
    Reader inBottom = new StringReader("7 4 4 4 q");

    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(european, europeanView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(european, europeanView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(european, europeanView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(european, europeanView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToAllDirectionsFromEmptyThenQuitEuropean() {
    Reader inLeft = new StringReader("4 4 4 2 q");
    Reader inRight = new StringReader("4 4 4 6 q");
    Reader inTop = new StringReader("4 4 4 2 q");
    Reader inBottom = new StringReader("4 4 6 4 q");

    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(european, europeanView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(european, europeanView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(european, europeanView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(european, europeanView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToMarbleFromAllDirectionsThenQuitEuropean() {
    Reader inLeft = new StringReader("4 3 4 5 q");
    Reader inRight = new StringReader("4 7 4 5 q");
    Reader inTop = new StringReader("2 4 4 5 q");
    Reader inBottom = new StringReader("6 4 4 5 q");

    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(european, europeanView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(european, europeanView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(european, europeanView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(european, europeanView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testGameOverEuropean() {
    this.european.move(1, 3, 3, 3);
    this.european.move(2, 1, 2, 3);
    this.european.move(3, 3, 1, 3);
    this.european.move(0, 3, 2, 3);
    this.european.move(0, 2, 2, 2);
    this.european.move(2, 3, 2, 1);
    this.european.move(4, 2, 2, 2);
    this.european.move(2, 1, 2, 3);
    this.european.move(5, 3, 3, 3);
    this.european.move(2, 3, 4, 3);
    this.european.move(4, 0, 4, 2);
    this.european.move(3, 0, 3, 2);
    this.european.move(4, 2, 2, 2);
    this.european.move(6, 2, 4, 2);
    this.european.move(4, 3, 4, 1);
    this.european.move(2, 5, 2, 3);
    this.european.move(3, 5, 3, 3);
    this.european.move(4, 5, 4, 3);
    this.european.move(2, 3, 2, 1);
    this.european.move(2, 0, 2, 2);
    this.european.move(4, 3, 2, 3);
    this.european.move(2, 3, 2, 1);
    this.european.move(6, 4, 6, 2);
    this.european.move(0, 4, 2, 4);
    this.european.move(5, 5, 5, 3);
    this.european.move(5, 1, 3, 1);


    input = new StringReader("3 2 5 2");
    controller = new MarbleSolitaireControllerImpl(european, europeanView, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game over!", lines[lines.length - 9]);
    assertEquals("Score: 9", lines[lines.length - 1]);
  }

  @Test
  public void testSuccessfulMovesThenQuitEuropean() {
    input = new StringReader("2 4 4 4 3 2 3 4 4 4 2 4 1 4 3 4 1 3 3 3 3 4 3 2 q");
    controller = new MarbleSolitaireControllerImpl(european, europeanView, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game quit!", lines[lines.length - 10]);
    assertEquals("State of game when quit:", lines[lines.length - 9]);
    assertEquals("    _ _ O", lines[lines.length - 8]);
    assertEquals("  O _ _ O O", lines[lines.length - 7]);
    assertEquals("O O _ _ O O O", lines[lines.length - 6]);
    assertEquals("O O O _ O O O", lines[lines.length - 5]);
    assertEquals("O O O O O O O", lines[lines.length - 4]);
    assertEquals("  O O O O O", lines[lines.length - 3]);
    assertEquals("    O O O", lines[lines.length - 2]);
    assertEquals("Score: 30", lines[lines.length - 1]);
  }

  @Test
  public void testQuitInputInFirstPlaceEuropean() {
    Reader in = new StringReader("q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInSecondPlaceEuropean() {
    Reader in = new StringReader("2 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInThirdPlaceEuropean() {
    Reader in = new StringReader("2 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInFourthPlaceEuropean() {
    Reader in = new StringReader("2 4 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(european, europeanView, in);
    controller.playGame();
    String output = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testPlayGameTriangular() {
    Reader in = new StringReader("3 1 1 1 4 3 2 1 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFirstPlaceTriangular() {
    Reader in = new StringReader("s 3 1 1 1 4 3 2 1 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInSecondPlaceTriangular() {
    Reader in = new StringReader("3 s 1 1 1 4 3 2 1 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInThirdPlaceTriangular() {
    Reader in = new StringReader("3 1 s 1 1 4 3 2 1 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidInputInFourthPlaceTriangular() {
    Reader in = new StringReader("3 1 1 s 1 4 3 2 1 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid Entry. Re-enter Value\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   O O\n" +
            "  _ _ O\n" +
            " O O _ O\n" +
            "O O O O O\n" +
            "Score: 12\n";
    assertEquals(output, out.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputTriangular() {
    Reader in = new StringReader("");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterValidMoveTriangular() {
    Reader in = new StringReader("3 1 1 1");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMovesTriangular() {
    Reader in = new StringReader("3 1 1 1 4 3 2 1");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterMultipleValidMovesAndABadInputTriangular() {
    Reader in = new StringReader("3 1 1 1 4 3 2 1 s");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromNotTwoAwayTriangular() {
    Reader in = new StringReader("2 1 1 1");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test(expected = IllegalStateException.class)
  public void testReadableOutOfInputAfterInValidMoveFromFarAwayTriangular() {
    Reader in = new StringReader("4 1 1 1");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
  }

  @Test
  public void testInvalidMoveFromAllDirectionsThenQuitTriangular() {
    Reader inLeft = new StringReader("4 3 1 1 q");
    Reader inRight = new StringReader("4 5 1 1 q");
    Reader inTop = new StringReader("3 4 1 1 q");
    Reader inBottom = new StringReader("5 4 1 1 q");

    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveFromAllDirectionsFarAwayThenQuitTriangular() {
    Reader inLeft = new StringReader("4 1 1 1 q");
    Reader inRight = new StringReader("4 7 1 1 q");
    Reader inTop = new StringReader("1 4 1 1 q");
    Reader inBottom = new StringReader("7 4 1 1 q");

    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToAllDirectionsFromEmptyThenQuitTriangular() {
    Reader inLeft = new StringReader("4 4 1 1 q");
    Reader inRight = new StringReader("4 4 1 1 q");
    Reader inTop = new StringReader("4 4 1 1 q");
    Reader inBottom = new StringReader("4 4 1 1 q");

    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inRight);
    MarbleSolitaireController controllerTop =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inTop);
    MarbleSolitaireController controllerBottom =
            new MarbleSolitaireControllerImpl(triangular, triangularView, inBottom);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerTop.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerBottom.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testInvalidMoveToMarbleFromAllDirectionsThenQuitTriangular() {
    Reader botLeft = new StringReader("4 1 1 1 q");
    Reader botRight = new StringReader("1 4 1 1 q");

    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Invalid move. Play again.\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";

    MarbleSolitaireController controllerLeft =
            new MarbleSolitaireControllerImpl(triangular, triangularView, botLeft);
    MarbleSolitaireController controllerRight =
            new MarbleSolitaireControllerImpl(triangular, triangularView, botRight);
    controllerLeft.playGame();
    assertEquals(output, out.toString());
    out.delete(0, out.length());
    controllerRight.playGame();
    assertEquals(output, out.toString());
  }

  @Test
  public void testGameOverTriangular() {
    this.triangular.move(2, 0, 0, 0);
    this.triangular.move(3, 2, 1, 0);
    this.triangular.move(0, 0, 2, 0);
    this.triangular.move(2, 2, 0, 0);
    this.triangular.move(3, 0, 1, 0);
    this.triangular.move(0, 0, 2, 0);
    this.triangular.move(4, 4, 2, 2);
    this.triangular.move(4, 1, 2, 1);
    input = new StringReader("5 3 5 5");
    controller = new MarbleSolitaireControllerImpl(triangular, triangularView, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game over!", lines[lines.length - 7]);
    assertEquals("Score: 5", lines[lines.length - 1]);
  }

  @Test
  public void testSuccessfulMovesThenQuitTriangular() {
    input = new StringReader("3 1 1 1 3 3 3 1 5 2 3 2 q");
    controller = new MarbleSolitaireControllerImpl(triangular, triangularView, input);
    controller.playGame();


    String[] lines = out.toString().split("\n");
    assertEquals("Game quit!", lines[lines.length - 8]);
    assertEquals("State of game when quit:", lines[lines.length - 7]);
    assertEquals("    O", lines[lines.length - 6]);
    assertEquals("   _ O", lines[lines.length - 5]);
    assertEquals("  O O _", lines[lines.length - 4]);
    assertEquals(" O _ O O", lines[lines.length - 3]);
    assertEquals("O _ O O O", lines[lines.length - 2]);
    assertEquals("Score: 11", lines[lines.length - 1]);
  }

  @Test
  public void testQuitInputInFirstPlaceTriangular() {
    Reader in = new StringReader("q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInSecondPlaceTriangular() {
    Reader in = new StringReader("2 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInThirdPlaceTriangular() {
    Reader in = new StringReader("2 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    assertEquals(output, out.toString());
  }

  @Test
  public void testQuitInputInFourthPlaceTriangular() {
    Reader in = new StringReader("2 4 4 q");

    MarbleSolitaireController controller =
            new MarbleSolitaireControllerImpl(triangular, triangularView, in);
    controller.playGame();
    String output = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n";
    assertEquals(output, out.toString());
  }
}