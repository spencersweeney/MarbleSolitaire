package cs3500.marblesolitaire.view;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.marblesolitaire.BadAppendable;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;

/**
 * Testing the triangular solitaire text view.
 */
public class TriangleSolitaireTextViewTest {
  private MarbleSolitaireView textView1;
  private MarbleSolitaireView textView2;
  private MarbleSolitaireView textView3;
  private MarbleSolitaireView textViewWithOutput;
  private Appendable output;
  private MarbleSolitaireView badTextView;

  @Before
  public void setUp() {
    textView1 = new TriangleSolitaireTextView(new TriangleSolitaireModel());
    textView2 = new TriangleSolitaireTextView(new TriangleSolitaireModel(7));
    textView3 = new TriangleSolitaireTextView(new TriangleSolitaireModel(2, 0));

    output = new StringBuffer();
    textViewWithOutput = new TriangleSolitaireTextView(new TriangleSolitaireModel(), output);

    badTextView = new TriangleSolitaireTextView(new TriangleSolitaireModel(), new BadAppendable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    MarbleSolitaireModel nullModel = null;
    MarbleSolitaireView nullModelText = new TriangleSolitaireTextView(nullModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    MarbleSolitaireView nullAppendable =
            new TriangleSolitaireTextView(new TriangleSolitaireModel(), null);
  }

  @Test
  public void testToString() {
    String text1 = "    _\n   O O\n  O O O\n O O O O\nO O O O O";
    String text2 = "      _\n     O O\n    O O O\n   O O O O\n  O O O O O\n O O O O O O" +
            "\nO O O O O O O";
    String text3 = "    O\n   O O\n  _ O O\n O O O O\nO O O O O";
    assertEquals(this.textView1.toString(), text1);
    assertEquals(this.textView2.toString(), text2);
    assertEquals(this.textView3.toString(), text3);
  }

  @Test
  public void testToStringAfterMove() {
    String text1 = "    _\n   O O\n  O O O\n O O O O\nO O O O O";
    String text2 = "    O\n   _ O\n  _ O O\n O O O O\nO O O O O";
    MarbleSolitaireModel game1 = new TriangleSolitaireModel();
    MarbleSolitaireView textView = new TriangleSolitaireTextView(game1);
    assertEquals(textView.toString(), text1);
    game1.move(2, 0, 0, 0);
    assertEquals(textView.toString(), text2);
  }

  @Test
  public void testRenderBoard() {
    try {
      this.textViewWithOutput.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }

    String board = "    _\n   O O\n  O O O\n O O O O\nO O O O O";

    assertEquals(board, output.toString());
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
  public void testRenderBoardIOError() {
    try {
      this.badTextView.renderBoard();
    } catch (IOException e) {
      throw new RuntimeException("could not write to appendable");
    }
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
}