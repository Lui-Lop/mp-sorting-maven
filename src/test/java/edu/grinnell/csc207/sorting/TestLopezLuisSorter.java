package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our InsertionSorter.
 */
public class TestLopezLuisSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new LopezLuisSort<String>((x,y) -> x.compareTo(y));
    intSorter = new LopezLuisSort<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestInsertionSorter