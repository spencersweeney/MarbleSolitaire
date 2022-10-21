package cs3500.marblesolitaire.controller;

/**
 * Controller for a Marble Solitaire Game.
 */
public interface MarbleSolitaireController {
  /**
   * Play a new game of marble solitaire.
   *
   * @throws IllegalStateException unable to successfully read input or transmit output
   */
  void playGame() throws IllegalStateException;
}
