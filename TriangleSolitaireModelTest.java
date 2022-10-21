package cs3500.marblesolitaire.model.hw04;

import org.junit.Before;
import org.junit.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testing the triangular solitaire model.
 */
public class TriangleSolitaireModelTest {
  private MarbleSolitaireModel game1;
  private MarbleSolitaireModel game2;
  private MarbleSolitaireModel game3;
  private MarbleSolitaireModel game4;
  private MarbleSolitaireModel game5;
  private MarbleSolitaireModel game6;
  private MarbleSolitaireModel game7;
  private MarbleSolitaireModel game8;

  @Before
  public void setUp() {
    game1 = new TriangleSolitaireModel();
    game2 = new TriangleSolitaireModel(4);
    game3 = new TriangleSolitaireModel(7);
    game4 = new TriangleSolitaireModel(4, 3);
    game5 = new TriangleSolitaireModel(4, 1);
    game6 = new TriangleSolitaireModel(4, 2);
    game7 = new TriangleSolitaireModel(7, 4, 2);
    game8 = new TriangleSolitaireModel(3, 2, 2);
  }

  @Test
  public void testInitBoard() {
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game7.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegSideLength() {
    MarbleSolitaireModel invalid = new TriangleSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty() {
    MarbleSolitaireModel invalid = new TriangleSolitaireModel(0, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyAndNegArmThickness() {
    MarbleSolitaireModel invalid = new TriangleSolitaireModel(-2, 0, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty3ArgConstructor() {
    MarbleSolitaireModel invalid = new TriangleSolitaireModel(5, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegArmThickness3ArgConstructor() {
    MarbleSolitaireModel invalid = new TriangleSolitaireModel(-3, 2, 0);
  }

  @Test
  public void testMoveUpLeft() {
    assertEquals(this.game1.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(2, 0, 0, 0);
    assertEquals(this.game1.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(1, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveUpRight() {
    assertEquals(this.game1.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(2, 2, 0, 0);
    assertEquals(this.game1.getSlotAt(2, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(1, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveFromLeft() {
    assertEquals(this.game6.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game6.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    this.game6.move(4, 0, 4, 2);
    assertEquals(this.game6.getSlotAt(4, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveFromRight() {
    assertEquals(this.game6.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game6.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    this.game6.move(4, 4, 4, 2);
    assertEquals(this.game6.getSlotAt(4, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveDownLeft() {
    assertEquals(this.game5.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game5.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game5.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Empty);
    this.game5.move(2, 1, 4, 1);
    assertEquals(this.game5.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game5.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game5.getSlotAt(4, 1), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveDownRight() {
    assertEquals(this.game4.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game4.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game4.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    this.game4.move(2, 1, 4, 3);
    assertEquals(this.game4.getSlotAt(2, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game4.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game4.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveFromEmpty() {
    this.game1.move(2, 0, 0, 0);
    this.game1.move(3, 2, 1, 0);
    this.game1.move(0, 0, 2, 0);
    this.game1.move(2, 2, 0, 0);
    this.game1.move(4, 4, 2, 2);
    this.game1.move(3, 3, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenFromLeft() {
    this.game6.move(4, 1, 4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenFromRight() {
    this.game6.move(4, 3, 4, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenUpLeft() {
    this.game1.move(1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenUpRight() {
    this.game1.move(1, 1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenDownLeft() {
    this.game5.move(3, 1, 4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenDownRight() {
    this.game4.move(3, 2, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenFromLeft() {
    MarbleSolitaireModel game = new TriangleSolitaireModel(7, 6, 5);
    game.move(6, 2, 6, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenFromRight() {
    MarbleSolitaireModel game = new TriangleSolitaireModel(7, 6, 3);
    game.move(6, 6, 6, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenUpLeft() {
    this.game1.move(3, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenUpRight() {
    this.game1.move(3, 3, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenDownLeft() {
    this.game5.move(1, 1, 4, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenDownRight() {
    this.game4.move(1, 0, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmpty() {
    MarbleSolitaireModel game = new TriangleSolitaireModel(7, 6, 3);
    game.move(6, 1, 6, 3);
    game.move(6, 0, 6, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveFromDoesntExist() {
    game1.move(0, 2, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToDoesntExist() {
    game1.move(1, 0, -1, 0);
  }

  @Test
  public void testIsGameOver() {
    assertFalse(this.game1.isGameOver());
    this.game1.move(2, 0, 0, 0);
    assertFalse(this.game1.isGameOver());
    this.game1.move(3, 2, 1, 0);
    this.game1.move(0, 0, 2, 0);
    assertFalse(this.game1.isGameOver());
    this.game1.move(2, 2, 0, 0);
    this.game1.move(3, 0, 1, 0);
    assertFalse(this.game1.isGameOver());
    this.game1.move(0, 0, 2, 0);
    this.game1.move(4, 4, 2, 2);
    assertFalse(this.game1.isGameOver());
    this.game1.move(4, 1, 2, 1);
    this.game1.move(4, 2, 4, 4);
    assertTrue(this.game1.isGameOver());
    assertTrue(new TriangleSolitaireModel(1).isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(this.game1.getBoardSize(), 5);
    assertEquals(this.game2.getBoardSize(), 4);
    assertEquals(this.game3.getBoardSize(), 7);
    assertEquals(this.game5.getBoardSize(), 5);
    assertEquals(this.game6.getBoardSize(), 5);
    assertEquals(this.game7.getBoardSize(), 7);
    assertEquals(this.game8.getBoardSize(), 3);
    assertEquals(new EnglishSolitaireModel(1).getBoardSize(), 1);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(2, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game4.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game4.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game4.getSlotAt(3, 0), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game6.getSlotAt(4, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.game6.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Invalid);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt() {
    game1.getSlotAt(0, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt1() {
    game1.getSlotAt(-1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt2() {
    game1.getSlotAt(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetSlotAt3() {
    game1.getSlotAt(7, 7);
  }

  @Test
  public void testGetScore() {
    assertEquals(this.game1.getScore(), 14);
    this.game1.move(2, 0, 0, 0);
    this.game1.move(2, 2, 2, 0);
    assertEquals(this.game1.getScore(), 12);
    assertEquals(new EnglishSolitaireModel(1).getScore(), 0);
  }
}