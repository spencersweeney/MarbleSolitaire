package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the text view of the Marble Solitaire game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private MarbleSolitaireModelState gameState;
  private Appendable destination;

  /**
   * Create an instance of a MarbleSolitaireTextView for the given model state.
   *
   * @param state the state of the game to have the text view for
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) throws IllegalArgumentException {
    if (state == null) {
      throw new IllegalArgumentException("Model State cannot be null");
    }
    this.gameState = state;
    this.destination = System.out;
  }

  /**
   * Create an instance of a MarbleSolitaireTextView for the given model state and appendable.
   *
   * @param state       the state of the game to have the text view for
   * @param destination object that the view uses as a destination
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable destination)
          throws IllegalArgumentException {
    if (state == null || destination == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.gameState = state;
    this.destination = destination;
  }

  @Override
  public String toString() {
    String textView = "";
    for (int i = 0; i < this.gameState.getBoardSize(); i++) {
      for (int j = 0; j < this.gameState.getBoardSize(); j++) {
        switch (this.gameState.getSlotAt(i, j)) {
          case Empty:
            textView += "_";
            if (j != this.gameState.getBoardSize() - 1 &&
                    this.gameState.getSlotAt(i, j + 1) !=
                            MarbleSolitaireModelState.SlotState.Invalid) {
              textView += " ";
            }
            break;
          case Marble:
            textView += "O";
            if (j != this.gameState.getBoardSize() - 1 &&
                    this.gameState.getSlotAt(i, j + 1) !=
                            MarbleSolitaireModelState.SlotState.Invalid) {
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
