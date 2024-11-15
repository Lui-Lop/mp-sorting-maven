package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Your Name
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *               The type of values in the array.
   * @param sorted
   *               The sorted array.
   * @param perm
   *               The permuted sorted array.
   * @param sorter
   *               The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
        () -> String.format("sort(%s) yields %s rather than %s",
            Arrays.toString(tmp),
            Arrays.toString(perm),
            Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test
  public void permutedIntegersTest() {
    int SIZE = 100;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers




  @Test
  public void disOrderedString() {
    if (null == intSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "charlie", "bravo", "delta", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter); 
  } // disOrderedString

  @Test
  public void emptyString() {
    if (null == intSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "", "bravo", "", "tango"};
    String[] expected = { "", "", "bravo", "foxtrot", "tango"};
    assertSorts(expected, original, stringSorter);
  } // emptyString

  @Test
  public void emptyStrArray() {
    if (null == intSorter) {
      return;
    } // if
    String[] original = {};
    String[] expected = {};
    assertSorts(expected, original, stringSorter);
  } // emptyStrArray

  @Test
  public void negativeInt() {
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = {3, 0, -3, 1, -1};
    Integer[] expected = { -3, -1, 0, 1, 3};
    assertSorts(expected, original, intSorter);
  }

  @Test
  public void negativeZero() {
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = {-0, -1, -3, -4, 5};
    Integer[] expected = {-4, -3, -1, -0, 5};
    assertSorts(expected, original, intSorter);
  }

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test
  public void permutedDoublingIntegersTest() {
    int SIZE = 1000;
    if (null == intSorter) {
      return;
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i * 2;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedDoublingIntegers
} // class TestSorter
