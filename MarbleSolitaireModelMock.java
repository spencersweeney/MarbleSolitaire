package cs3500.marblesolitaire.model.hw04;

import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Mock solitaire model for testing.
 */
public class MarbleSolitaireModelMock implements MarbleSolitaireModel {
  final StringBuilder log;

  /**
   * Construct a mock model.
   *
   * @param log log to keep track of inputs
   */
  public MarbleSolitaireModelMock(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("fromRow = %d, fromCol = %d, toRow = %d, toCol = %d\n",
            fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public int getBoardSize() {
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append(String.format("row = %d, col = %d\n",
            row, col));
    return null;
  }

  @Override
  public int getScore() {
    return 0;
  }
}
