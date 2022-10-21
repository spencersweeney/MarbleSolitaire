package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * The Implementation for a Controller for a marble solitaire game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private Readable input;

  /**
   * Construct a controller for the given model and view.
   *
   * @param model the model for the controller
   * @param view  the view for the controller
   * @param input the input for the controller to read
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
                                       Readable input) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    if (view == null) {
      throw new IllegalArgumentException("View cannot be null");
    }

    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }

    this.model = model;
    this.view = view;
    this.input = input;
  }

  @Override
  public void playGame() throws IllegalStateException {
    Scanner myScan = new Scanner(input);
    while (!this.model.isGameOver()) {
      this.renderBoardHelp();
      this.renderScore();

      int[] moves = {0, 0, 0, 0};
      int i = 0;

      while (true) {
        if (i == 4) {
          break;
        }
        if (myScan.hasNextInt()) {
          moves[i] = myScan.nextInt();
          i++;
        } else if (myScan.hasNext()) {
          String input = myScan.next();
          if (input.equals("q") || input.equals("Q")) {
            this.renderMessageHelp("Game quit!");
            this.renderMessageHelp("State of game when quit:");
            this.renderBoardHelp();
            this.renderScore();
            return;
          } else {
            this.renderMessageHelp("Invalid Entry. Re-enter Value");
          }
        } else {
          throw new IllegalStateException("Readable ran out of inputs");
        }
      }
      this.moveHelp(moves[0] - 1, moves[1] - 1, moves[2] - 1, moves[3] - 1);
    }

    this.renderMessageHelp("Game over!");
    this.renderBoardHelp();
    this.renderScore();
  }

  /**
   * Help move by checking and dealing with IllegalArgumentException.
   *
   * @param fromRow the row to move from
   * @param fromCol the column to move from
   * @param toRow   the row to move to
   * @param toCol   the column to move to
   */
  private void moveHelp(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      this.model.move(fromRow, fromCol, toRow, toCol);
    } catch (IllegalArgumentException e) {
      this.renderMessageHelp("Invalid move. Play again.");
    }
  }

  /**
   * render a given message to the appendable.
   *
   * @param message the message to render
   * @throws IllegalStateException if an I/O error occurs
   */
  private void renderMessageHelp(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Error writing to Appendable");
    }
  }

  /**
   * Render the current score of the game to the appendable.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  private void renderScore() throws IllegalStateException {
    String scoreMessage = "Score: " + this.model.getScore();
    this.renderMessageHelp(scoreMessage);
  }

  /**
   * Render the board to the Appendable while checking for an I/O error.
   *
   * @throws IllegalStateException if an I/O error occurs
   */
  private void renderBoardHelp() throws IllegalStateException {
    try {
      this.view.renderBoard();
      this.renderMessageHelp("");
    } catch (IOException e) {
      throw new IllegalStateException("Error writing to Appendable");
    }
  }
}
