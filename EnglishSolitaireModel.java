package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.RectangularSolitaireModel;

/**
 * Represents the English Solitaire Model of a Marble Solitaire Model game.
 */
public class EnglishSolitaireModel extends RectangularSolitaireModel {
  /**
   * Default constructor for EnglishSolitaireModel (default size 3 with empty slot in middle).
   */
  public EnglishSolitaireModel() {
    super();
  }

  /**
   * Construct an EnglishSolitaireModel with the given side length and the empty slot in the middle.
   *
   * @param sideLength the given side length for the model
   */
  public EnglishSolitaireModel(int sideLength) {
    super(sideLength);
  }

  /**
   * Construct an EnglishSolitaireModel with default size of 3 and the empty slot at
   * the given location.
   *
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   */
  public EnglishSolitaireModel(int row, int col) {
    super(row, col);
  }

  /**
   * Construct an EnglishSolitaireModel with the given side length and the empty slot in the
   * given location.
   *
   * @param sideLength the side length for the model
   * @param row        the row of the empty slot
   * @param col        the column of the empty slot
   */
  public EnglishSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
  }

  @Override
  protected boolean isValidPosition(int row, int col) {
    return !(((row <= this.sideLength - 2 || row >= this.sideLength * 2 - 1)
            && !(col >= this.sideLength - 1 && col <= this.sideLength * 2 - 2))
            || ((col <= this.sideLength - 2 || col >= this.sideLength * 2 - 1)
            && !(row >= this.sideLength - 1 && row <= this.sideLength * 2 - 2)));
  }
}
