package cs3500.marblesolitaire.model.hw04;

import static java.lang.Math.abs;

/**
 * Represent a Triangle Marble Solitaire Game.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {
  /**
   * Default Constructor for a Triangle solitaire game (sideLength is 5 and the empty slot at the
   * top).
   */
  public TriangleSolitaireModel() {
    super(5, 0, 0);
  }

  /**
   * Generates a TriangleSolitaireModel with the given dimensions and empty slot at the top.
   *
   * @param dimensions the number of slots in the bottom most row
   */
  public TriangleSolitaireModel(int dimensions) {
    super(dimensions, 0, 0);
  }

  /**
   * Generates a TriangleSolitaireModel with dimension 5 and the empty slot at the given location.
   *
   * @param row the row of the slot
   * @param col the column of the slot
   */
  public TriangleSolitaireModel(int row, int col) {
    super(5, row, col);
  }

  /**
   * Generates a TriangleSolitaireModel with the given dimension and the empty slot at the
   * given location.
   *
   * @param dimensions the number of slots in the bottom most row
   * @param row        the row of the slot
   * @param col        the column of the slot
   */
  public TriangleSolitaireModel(int dimensions, int row, int col) {
    super(dimensions, row, col);
  }

  @Override
  protected boolean validSideLength(int sideLength) {
    return (sideLength > 0);
  }

  @Override
  protected boolean validEmptySlot(int row, int col) {
    return this.isValidPosition(row, col);
  }

  @Override
  protected boolean isValidPosition(int row, int col) {
    return row >= 0 && row < this.getBoardSize() && col >= 0 &&
            col < this.getBoardSize() && row >= col;
  }

  @Override
  protected boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    boolean fromMarble;
    boolean toAvailable;
    boolean marbleBetween;

    try {
      fromMarble = this.getSlotAt(fromRow, fromCol).equals(SlotState.Marble);
    } catch (IllegalArgumentException e) {
      return false;
    }

    try {
      toAvailable = this.getSlotAt(toRow, toCol).equals(SlotState.Empty);
    } catch (IllegalArgumentException e) {
      return false;
    }

    try {
      marbleBetween = this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
              .equals(SlotState.Marble);
    } catch (IllegalArgumentException e) {
      return false;
    }

    boolean twoAway = ((fromRow - toRow == 0) && (abs(fromCol - toCol) == 2)) ||
            ((toRow - fromRow == 2) && ((toCol == fromCol) || (toCol - fromCol == 2))) ||
            ((toRow - fromRow == -2) && ((toCol == fromCol) || (toCol - fromCol == -2)));

    return fromMarble && toAvailable && twoAway && marbleBetween;
  }

  @Override
  protected boolean anyValidMoves() {
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < i + 1; j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Marble)) {
          if (i > 2) {
            if (j > 1) {
              if (this.isValidMove(i, j, i, j - 2)) {
                return true;
              }
            }
            if (j < i - 1) {
              if (this.isValidMove(i, j, i, j + 2)) {
                return true;
              }
            }
          }
          if (i < this.sideLength - 2) {
            if (this.isValidMove(i, j, i + 2, j)) {
              return true;
            }
            if (this.isValidMove(i, j, i + 2, j + 2)) {
              return true;
            }
          }
          if (i > 1) {
            if (j > 1) {
              if (this.isValidMove(i, j, i - 2, j - 2)) {
                return true;
              }
            }
            if (j < i - 1) {
              if (this.isValidMove(i, j, i - 2, j)) {
                return true;
              }
            }
          }
        }
      }
    }
    return false;
  }

  @Override
  public int getBoardSize() {
    return this.sideLength;
  }
}
