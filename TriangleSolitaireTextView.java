package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Text view for a triangular solitaire game.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState gameState;
  private Appendable destination;

  /**
   * Create an instance of a TriangleSolitaireTextView for the given model state.
   *
   * @param state the state of the game to have the text view for
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state)
          throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Model State cannot be null");
    }
    this.gameState = state;
    this.destination = System.out;
  }

  /**
   * Create an instance of a TriangleSolitaireTextView for the given model state and appendable.
   *
   * @param state       the state of the game to have the text view for
   * @param destination object that the view uses as a destination
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination)
          throws IllegalArgumentException {
    if (state == null || destination == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.gameState = state;
    this.destination = destination;
  }

  /**
   * Create a string representation of the board.
   *
   * @return the string representation of the board
   */
  public String toString() {
    String textView = "";
    for (int i = 0; i < this.gameState.getBoardSize(); i++) {
      for (int k = 0; k < this.gameState.getBoardSize() - i - 1; k++) {
        textView += " ";
      }
      for (int j = 0; j < i + 1; j++) {
        switch (this.gameState.getSlotAt(i, j)) {
          case Empty:
            textView += "_";
            if (j != i) {
              textView += " ";
            }
            break;
          case Marble:
            textView += "O";
            if (j != i) {
              textView += " ";
            }
            break;
          case Invalid:
            if (j < this.gameState.getBoardSize() / 2) {
              textView += "  ";
            }
            break;
          default:
            throw new RuntimeException("Slot state cannot be anything else");
        }
      }

      if (i != this.gameState.getBoardSize() - 1) {
        textView += "\n";
      }
    }
    return textView;
  }

  @Override
  public void renderBoard() throws IOException {
    try {
      this.destination.append(this.toString());
    } catch (IOException e) {
      throw new IOException("Transmission of data failed");
    }
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.destination.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission of data failed");
    }
  }
}
