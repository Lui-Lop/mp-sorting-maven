package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *            The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Luis Lopez
 */

public class SelectionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *                   The order in which elements in the array should be ordered
   *                   after sorting.
   */
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Searches through an array to find the smallest element.
   *
   * @param values
   *               array to be searched
   * @param i
   *               index of i onwards is unsorted and has to be searched
   * @return
   *         return integer that is index of smallest element
   */
  public int indexOfSmallest(T[] values, int i) {
    T temp = values[i];
    int lowestInd = i;
    for (int j = i; j < values.length; j++) {
      if (order.compare(temp, values[j]) > 0) {
        temp = values[j];
        lowestInd = j;
      } // if current element is less than temp, change temp to current element
    } // loop over entire unsorted part of array to find smallest
    return lowestInd;
  } // indexOfSmallest(T[], int)

  /**
   * Sort an array in place using selection sort.
   *
   * @param values
   *               an array to sort.
   *
   * @post
   *       The array has been sorted according to some order (often
   *       one given to the constructor).
   * @post
   *       For all i, 0 &lt; i &lt; values.length,
   *       order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 0; i < values.length; i++) {
      int lowestInd = indexOfSmallest(values, i);
      T temp = values[lowestInd];
      values[lowestInd] = values[i];
      values[i] = temp;
    } // loop over entire aray to sort
  } // sort(T[])
} // class SelectionSorter
