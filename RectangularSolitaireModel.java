package cs3500.marblesolitaire.model.hw04;

import static java.lang.Math.abs;

/**
 * An Abstract class for Solitaire Models that follow a rectangular coordinate system.
 */
public abstract class RectangularSolitaireModel extends AbstractSolitaireModel {
  /**
   * Default constructor for a RectangularSolitaireModel with the default sidelength of 3
   * and the empty slot being in the middle.
   */
  public RectangularSolitaireModel() {
    super(3, 3, 3);
  }

  /**
   * Construct a RectangularSolitaireModel with the given side length and empty slot in the middle.
   *
   * @param sideLength the side length for the model
   */
  public RectangularSolitaireModel(int sideLength) {
    super(sideLength, (sideLength - 1) * 3 / 2, (sideLength - 1) * 3 / 2);
  }

  /**
   * Construct a RectangularSolitaireModel with the default side length of 3 and the emtpy slot at
   * the given location.
   *
   * @param row the row for the empty slot
   * @param col the column for the empty slot
   */
  public RectangularSolitaireModel(int row, int col) {
    super(3, row, col);
  }

  /**
   * Construct a RectangularSolitaireModel with the given size and empty slot location.
   *
   * @param sideLength the side length for the game
   * @param row        the row for the empty slot
   * @param col        the column for the empty slot
   */
  public RectangularSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
  }

  @Override
  protected boolean validSideLength(int sideLength) {
    return (sideLength % 2 != 0) && (sideLength > 0);
  }

  @Override
  protected boolean validEmptySlot(int row, int col) {
    return this.isValidPosition(row, col);
  }

  @Override
  protected abstract boolean isValidPosition(int row, int col);

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    boolean fromMarble = this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble);

    boolean toAvailable = this.getSlotAt(toRow, toCol).equals(SlotState.Empty);

    boolean marbleBetween = this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            .equals(SlotState.Marble);

    boolean twoAway = ((abs(fromRow - toRow) == 2) && (fromCol - toCol == 0)) ||
            ((abs(fromCol - toCol) == 2) && (fromRow - toRow == 0));

    return fromMarble && toAvailable && twoAway && marbleBetween;
  }

  @Override
  protected boolean anyValidMoves() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          if (i != 0 && i != 1) {
            if (this.isValidMove(i, j, i - 2, j)) {
              return true;
            }
          }
          if (i != this.getBoardSize() - 2 && i != this.getBoardSize() - 1) {
            if (this.isValidMove(i, j, i + 2, j)) {
              return true;
            }
          }
          if (j != 0 && j != 1) {
            if (this.isValidMove(i, j, i, j - 2)) {
              return true;
            }
          }
          if (j != this.getBoardSize() - 2 && j != this.getBoardSize() - 1) {
            if (this.isValidMove(i, j, i, j + 2)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public int getBoardSize() {
    return this.sideLength + (this.sideLength - 1) * 2;
  }
}
