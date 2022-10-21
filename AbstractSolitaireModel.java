package cs3500.marblesolitaire.model.hw04;

import java.util.ArrayList;
import java.util.List;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Abstract class to be the base for a Marble Solitaire Model.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  protected int sideLength;
  protected List<List<SlotState>> board;

  /**
   * Constructor for an AbstractSolitaireModel that takes 3 arguments for the empty slot location
   * and the sideLength of the game.
   *
   * @param sideLength the arm thickness of the board
   * @param row        the row to put the empty slot
   * @param col        the column to put the empty slot
   * @throws IllegalArgumentException if the arm thickness isn't a positive odd integer or the empty
   *                                  slot isn't a valid position
   */
  public AbstractSolitaireModel(int sideLength, int row, int col)
          throws IllegalArgumentException {
    if (!this.validSideLength(sideLength)) {
      throw new IllegalArgumentException("Given Side Length not Valid");
    }
    this.sideLength = sideLength;
    this.initBoard(sideLength);
    if (!this.validEmptySlot(row, col)) {
      String message = "Invalid empty cell position (" + row + ", " + col + ")";
      throw new IllegalArgumentException(message);
    }
    this.board.get(row).set(col, SlotState.Empty);
  }

  /**
   * Initialize a full board with the given side length.
   *
   * @param sideLength the size to initialize the board with
   */
  protected void initBoard(int sideLength) {
    this.board = new ArrayList<List<SlotState>>();
    for (int i = 0; i < this.getBoardSize(); i++) {
      ArrayList<SlotState> currentRow = new ArrayList<>();
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.isValidPosition(i, j)) {
          currentRow.add(SlotState.Marble);
        } else {
          currentRow.add(SlotState.Invalid);
        }
      }
      this.board.add(currentRow);
    }
  }

  /**
   * Determine if the given side length is valid for the Solitaire Model.
   *
   * @param sideLength the given side length to check
   * @return true if the side length is valid
   */
  protected abstract boolean validSideLength(int sideLength);

  /**
   * Determine if the given row and column are a valid empty slot choice for the Solitaire Model.
   *
   * @param row the given row for the empty slot
   * @param col the given column for the empty slot
   * @return true if the slot is valid
   */
  protected abstract boolean validEmptySlot(int row, int col);

  /**
   * Determines whether a given row/col is a Valid position for a certain model/board.
   *
   * @param row the row of the slot to determine
   * @param col the coliumn of the slot to determine
   * @return true if the position is valid for the board
   */
  protected abstract boolean isValidPosition(int row, int col);

  /**
   * Determine whether a move from a point to a point is a valid move.
   *
   * @param fromRow the row which the move starts
   * @param fromCol the column which the move starts
   * @param toRow   the row to move to
   * @param toCol   the column to move to
   * @return true if the move is valid
   */
  protected abstract boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol);

  /**
   * Determine if there are any valid moves left.
   *
   * @return true if there are still valid moves
   */
  protected abstract boolean anyValidMoves();

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    if (!this.isValidMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("The attempted move is invalid");
    }

    this.board.get(fromRow).set(fromCol, SlotState.Empty);
    this.board.get((fromRow + toRow) / 2).set((fromCol + toCol) / 2, SlotState.Empty);
    this.board.get(toRow).set(toCol, SlotState.Marble);
  }

  @Override
  public boolean isGameOver() {
    return !this.anyValidMoves();
  }

  @Override
  public abstract int getBoardSize();

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    try {
      return this.board.get(row).get(col);
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Row or Column is beyond dimensions of the board");
    }
  }

  @Override
  public int getScore() {
    int score = 0;
    for (List<SlotState> row : this.board) {
      for (SlotState slotState : row) {
        if (slotState.equals(SlotState.Marble)) {
          score += 1;
        }
      }
    }
    return score;
  }
}
