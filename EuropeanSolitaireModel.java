package cs3500.marblesolitaire.model.hw04;

/**
 * Represents a game of Marble Solitaire in the European variation.
 */
public class EuropeanSolitaireModel extends RectangularSolitaireModel {

  /**
   * Default Constructor with side Length 3 and Empty Hole in center.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructor to create a European Solitaire Game with the given side length.
   *
   * @param sideLength the side length of the game
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
  }

  /**
   * Constructor to create a European Solitaire Game with a specified starting hole location.
   *
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(row, col);
  }

  /**
   * Construct a EuropeanSolitaireModel with the given side length and empty slot location.
   *
   * @param sideLength the side length for the game
   * @param row        the row of the empty slot
   * @param col        the column of the empty slot
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
  }

  @Override
  protected boolean isValidPosition(int row, int col) {
    boolean possibleRow = (row <= this.sideLength - 2 || row >= this.sideLength * 2 - 1);
    boolean badColumnTop = (col >= this.sideLength * 2 - 1 + row) ||
            (col <= this.sideLength - 2 - row);
    boolean badColumnBot = (col <= row - this.sideLength * 2 + 1) ||
            (col >= this.sideLength * 5 - row - 4);

    return !(possibleRow && (badColumnTop || badColumnBot));
  }
}
