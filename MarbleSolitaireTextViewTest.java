package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.BadAppendable;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  private MarbleSolitaireView textView1;
  private MarbleSolitaireView textView2;
  private MarbleSolitaireView textView3;
  private MarbleSolitaireView textViewWithOutput;

  private MarbleSolitaireView textView1European;
  private MarbleSolitaireView textView2European;
  private MarbleSolitaireView textView3European;
  private MarbleSolitaireView textViewWithOutputEuropean;

  private Appendable output;

  private MarbleSolitaireView badTextView;
  private MarbleSolitaireView badTextViewEuropean;

  @Before
  public void setUp() {
    textView1 = new MarbleSolitaireTextView(new EnglishSolitaireModel());
    textView2 = new MarbleSolitaireTextView(new EnglishSolitaireModel(5));
    textView3 = new MarbleSolitaireTextView(new EnglishSolitaireModel(0, 2));

    textView1European = new MarbleSolitaireTextView(new EuropeanSolitaireModel());
    textView2European = new MarbleSolitaireTextView(new EuropeanSolitaireModel(5));
    textView3European = new MarbleSolitaireTextView(new EuropeanSolitaireModel(0, 2));

    output = new StringBuffer();
    textViewWithOutput = new MarbleSolitaireTextView(new EnglishSolitaireModel(), output);
    textViewWithOutputEuropean = new MarbleSolitaireTextView(new EuropeanSolitaireModel(), output);

    badTextView = new MarbleSolitaireTextView(new EnglishSolitaireModel(), new BadAppendable());
    badTextViewEuropean = new MarbleSolitaireTextView(new EuropeanSolitaireModel(),
            new BadAppendable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    MarbleSolitaireModel nullModel = null;
    MarbleSolitaireTextView nullModelText = new MarbleSolitaireTextView(nullModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    MarbleSolitaireTextView nullAppendable =
            new MarbleSolitaireTextView(new EnglishSolitaireModel(), null);
  }

  @Test
  public void testToString() {
    String text1 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O";
    String text2 = "        O O O O O\n        O O O O O\n"
            + "        O O O O O\n        O O O O O\n"
            + "O O O O O O O O O O O O O\nO O O O O O O O O O O O O\nO O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\nO O O O O O O O O O O O O\n"
            + "        O O O O O\n        O O O O O\n        O O O O O\n"
            + "        O O O O O";
    String text3 = "    _ O O\n    O O O\nO O O O O O O\nO O O O O O O\nO O O O O O O\n"
            + "    O O O\n    O O O";
    assertEquals(this.textView1.toString(), text1);
    assertEquals(this.textView2.toString(), text2);
    assertEquals(this.textView3.toString(), text3);
  }

  @Test
  public void testToStringAfterMove() {
    String text1 = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O";
    String text2 = "    O O O\n    O _ O\nO O O _ O O O\nO O O O O O O\n"
            + "O O O O O O O\n    O O O\n    O O O";
    MarbleSolitaireModel game1 = new EnglishSolitaireModel();
    MarbleSolitaireView textView = new MarbleSolitaireTextView(game1);
    assertEquals(textView.toString(), text1);
    game1.move(1, 3, 3, 3);
    assertEquals(textView.toString(), text2);
  }

  @Test
  public void testToStringEuropean() {
    String text1 = "    O O O\n  O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n  O O O O O\n    O O O";
    String text2 = "        O O O O O\n      O O O O O O O\n"
            + "    O O O O O O O O O\n  O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\nO O O O O O O O O O O O O\nO O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\nO O O O O O O O O O O O O\n"
            + "  O O O O O O O O O O O\n    O O O O O O O O O\n      O O O O O O O\n"
            + "        O O O O O";
    String text3 = "    _ O O\n  O O O O O\nO O O O O O O\nO O O O O O O\n"
            + "O O O O O O O\n  O O O O O\n    O O O";
    assertEquals(this.textView1European.toString(), text1);
    assertEquals(this.textView2European.toString(), text2);
    assertEquals(this.textView3European.toString(), text3);
  }

  @Test
  public void testToStringAfterMoveEuropean() {
    String text1 = "    O O O\n  O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n  O O O O O\n    O O O";
    String text2 = "    O O O\n  O O _ O O\nO O O _ O O O\nO O O O O O O\n"
            + "O O O O O O O\n  O O O O O\n    O O O";
    MarbleSolitaireModel game1 = new EuropeanSolitaireModel();
    MarbleSolitaireView textView = new MarbleSolitaireTextView(game1);
    assertEquals(textView.toString(), text1);
    game1.move(1, 3, 3, 3);
    assertEquals(textView.toString(), text2);
  }

  @Test
  public void testRenderBoard() {
    try {
      this.textViewWithOutput.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }

    String board = "    O O O\n    O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n    O O O\n    O O O";

    assertEquals(board, output.toString());
  }

  @Test(expected = RuntimeException.class)
  public void testRenderBoardIOError() {
    try {
      this.badTextView.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }
  }

  @Test
  public void testRenderMessage() {
    String message = "Hello World";

    try {
      this.textViewWithOutput.renderMessage(message);
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }

    assertEquals(message, output.toString());
  }

  @Test(expected = RuntimeException.class)
  public void testRenderMessageIOError() {
    String message = "Hello World";

    try {
      this.badTextView.renderMessage(message);
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }
  }

  @Test
  public void testRenderBoardEuropean() {
    try {
      this.textViewWithOutputEuropean.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }

    String board = "    O O O\n  O O O O O\nO O O O O O O\nO O O _ O O O\n"
            + "O O O O O O O\n  O O O O O\n    O O O";

    assertEquals(board, output.toString());
  }

  @Test(expected = RuntimeException.class)
  public void testRenderBoardEuropeanIOError() {
    try {
      this.badTextViewEuropean.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }
  }

  @Test
  public void testRenderMessageEuropean() {
    String message = "Hello World";

    try {
      this.textViewWithOutputEuropean.renderMessage(message);
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }

    assertEquals(message, output.toString());
  }

  @Test(expected = RuntimeException.class)
  public void testRenderMessageEuropeanIOError() {
    String message = "Hello World";

    try {
      this.badTextViewEuropean.renderMessage(message);
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }
  }
}