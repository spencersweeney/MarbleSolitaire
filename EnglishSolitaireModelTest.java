package cs3500.marblesolitaire.model.hw02;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the EnglishSolitaireModel class.
 */
public class EnglishSolitaireModelTest {
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
    game1 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(5);
    game3 = new EnglishSolitaireModel(7);
    game4 = new EnglishSolitaireModel(2, 5);
    game5 = new EnglishSolitaireModel(3, 4);
    game6 = new EnglishSolitaireModel(0, 3);
    game7 = new EnglishSolitaireModel(7, 6, 9);
    game8 = new EnglishSolitaireModel(3, 2, 4);
  }

  @Test
  public void testInitBoard() {
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.game1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.game2.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(0, 3), MarbleSolitaireModelState.SlotState.Empty);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenArmThickness() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegArmThickness() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(1, 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyAndEvenArmThickness() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(1, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmptyAndNegArmThickness() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(-3, 6, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEmpty3ArgConstructor() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(1, 6, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegArmThickness3ArgConstructor() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(-3, 2, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEvenArmThickness3ArgConstructor() {
    MarbleSolitaireModel invalid = new EnglishSolitaireModel(1, 2, 4);
  }

  @Test
  public void testMove() {
    assertEquals(this.game1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(1, 3, 3, 3);
    assertEquals(this.game1.getSlotAt(1, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game7.getSlotAt(6, 7), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game7.getSlotAt(6, 8), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game7.getSlotAt(6, 9), MarbleSolitaireModelState.SlotState.Empty);
    this.game7.move(6, 7, 6, 9);
    assertEquals(this.game7.getSlotAt(6, 7), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game7.getSlotAt(6, 8), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game7.getSlotAt(6, 9), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveFromLeft() {
    assertEquals(this.game1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(3, 1, 3, 3);
    assertEquals(this.game1.getSlotAt(3, 1), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 2), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveFromRight() {
    assertEquals(this.game1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(3, 5, 3, 3);
    assertEquals(this.game1.getSlotAt(3, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 4), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test
  public void testMoveFromBot() {
    assertEquals(this.game1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    this.game1.move(5, 3, 3, 3);
    assertEquals(this.game1.getSlotAt(5, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(4, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Marble);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenLeft() {
    game1.move(3, 2, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenRight() {
    game1.move(3, 4, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenTop() {
    game1.move(2, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveNotTwoBetweenBot() {
    game1.move(4, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenLeft() {
    game1.move(3, 0, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenRight() {
    game1.move(3, 6, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenTop() {
    game1.move(0, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveDiagnol() {
    game1.move(5, 5, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveMoreThanTwoBetweenBottom() {
    game1.move(6, 3, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveOverEmpty() {
    game1.move(2, 3, 4, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveFromEmpty() {
    game1.move(3, 3, 5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveFromDoesntExist() {
    game1.move(0, 0, 2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToDoesntExist() {
    game1.move(2, 0, 0, 0);
  }

  @Test
  public void testIsGameOver() {
    assertFalse(this.game1.isGameOver());
    this.game1.move(1, 3, 3, 3);
    this.game1.move(2, 1, 2, 3);
    this.game1.move(3, 3, 1, 3);
    this.game1.move(0, 3, 2, 3);
    assertFalse(this.game1.isGameOver());
    this.game1.move(0, 2, 2, 2);
    this.game1.move(2, 3, 2, 1);
    this.game1.move(4, 2, 2, 2);
    this.game1.move(2, 1, 2, 3);
    this.game1.move(5, 3, 3, 3);
    assertFalse(this.game1.isGameOver());
    this.game1.move(2, 3, 4, 3);
    this.game1.move(4, 0, 4, 2);
    this.game1.move(3, 0, 3, 2);
    this.game1.move(4, 2, 2, 2);
    this.game1.move(6, 2, 4, 2);
    this.game1.move(4, 3, 4, 1);
    this.game1.move(2, 5, 2, 3);
    assertFalse(this.game1.isGameOver());
    this.game1.move(3, 5, 3, 3);
    this.game1.move(4, 5, 4, 3);
    this.game1.move(2, 3, 2, 1);
    this.game1.move(2, 0, 2, 2);
    assertFalse(this.game1.isGameOver());
    this.game1.move(4, 3, 2, 3);
    this.game1.move(2, 3, 2, 1);
    this.game1.move(6, 4, 6, 2);
    this.game1.move(0, 4, 2, 4);
    assertTrue(this.game1.isGameOver());
    assertTrue(new EnglishSolitaireModel(1).isGameOver());
  }

  @Test
  public void testGetBoardSize() {
    assertEquals(this.game1.getBoardSize(), 7);
    assertEquals(this.game2.getBoardSize(), 13);
    assertEquals(this.game3.getBoardSize(), 19);
    assertEquals(this.game5.getBoardSize(), 7);
    assertEquals(this.game6.getBoardSize(), 7);
    assertEquals(this.game8.getBoardSize(), 7);
    assertEquals(new EnglishSolitaireModel(1).getBoardSize(), 1);
  }

  @Test
  public void testGetSlotAt() {
    assertEquals(this.game1.getSlotAt(3, 3), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game1.getSlotAt(0, 0), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.game1.getSlotAt(2, 3), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game2.getSlotAt(0, 2), MarbleSolitaireModelState.SlotState.Invalid);
    assertEquals(this.game2.getSlotAt(6, 6), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game3.getSlotAt(9, 9), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game4.getSlotAt(2, 4), MarbleSolitaireModelState.SlotState.Marble);
    assertEquals(this.game4.getSlotAt(2, 5), MarbleSolitaireModelState.SlotState.Empty);
    assertEquals(this.game6.getSlotAt(0, 3), MarbleSolitaireModelState.SlotState.Empty);
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
    assertEquals(this.game1.getScore(), 32);
    this.game1.move(1, 3, 3, 3);
    this.game1.move(2, 1, 2, 3);
    assertEquals(this.game1.getScore(), 30);
    assertEquals(new EnglishSolitaireModel(1).getScore(), 0);
  }
}