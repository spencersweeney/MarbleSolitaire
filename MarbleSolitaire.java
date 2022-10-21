package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Main class for marble solitaire.
 */
public final class MarbleSolitaire {
  /**
   * Main method for marble solitaire.
   *
   * @param args the command line argument
   */
  public static void main(String[] args) {
    MarbleSolitaireModel model;
    MarbleSolitaireView view;
    MarbleSolitaireController controller;

    String gametype = "";
    int size = -1;
    int row = -1;
    int col = -1;

    for (int i = 0; i < args.length; i++) {
      if (i == 0) {
        gametype = args[i];
      } else {
        if (args[i].equals("-size")) {
          try {
            size = Integer.parseInt(args[i + 1]);
          } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("size command must be followed by an integer");
          }
        }
        if (args[i].equals("-hole")) {
          try {
            row = Integer.parseInt(args[i + 1]);
            col = Integer.parseInt(args[i + 2]);
          } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("hole command must be followed by 2 integers");
          }
        }
      }
    }

    switch (gametype) {
      case "english":
        if (size == -1 && row == -1) {
          model = new EnglishSolitaireModel();
        } else if (size == -1) {
          model = new EnglishSolitaireModel(row, col);
        } else if (row == -1) {
          model = new EnglishSolitaireModel(size);
        } else {
          model = new EnglishSolitaireModel(size, row, col);
        }
        view = new MarbleSolitaireTextView(model);
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        break;
      case "european":
        if (size == -1 && row == -1) {
          model = new EuropeanSolitaireModel();
        } else if (size == -1) {
          model = new EuropeanSolitaireModel(row, col);
        } else if (row == -1) {
          model = new EuropeanSolitaireModel(size);
        } else {
          model = new EuropeanSolitaireModel(size, row, col);
        }
        view = new MarbleSolitaireTextView(model);
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        break;
      case "triangular":
        if (size == -1 && row == -1) {
          model = new TriangleSolitaireModel();
        } else if (size == -1) {
          model = new TriangleSolitaireModel(row, col);
        } else if (row == -1) {
          model = new TriangleSolitaireModel(size);
        } else {
          model = new TriangleSolitaireModel(size, row, col);
        }
        view = new TriangleSolitaireTextView(model);
        controller = new MarbleSolitaireControllerImpl(model, view,
                new InputStreamReader(System.in));
        break;
      default:
        throw new IllegalArgumentException("First word in command line should be the game type");
    }

    controller.playGame();
  }
}
