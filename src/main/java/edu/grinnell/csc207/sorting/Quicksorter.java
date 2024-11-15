package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Luis Lopez
 */

public class Quicksorter<T> implements Sorter<T> {
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
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort subarray and write back to main array.
   *
   * @param values
   *    an array to sort subarray from
   * @param lb
   *    lower bound of the subarray
   * @param ub
   *    upper bound of subarray
   *
   * @post
   *    subarray is sorted
   */
  public void sortSub(T[] values, int lb, int ub) {
    T[] subArr = Arrays.copyOfRange(values, lb, ub);
    sort(subArr);
    for (int i = 0; i < subArr.length; i++) {
      values[lb] = subArr[i];
      lb++;
    } // loop over subarray to copy to original array
  } //sortSub

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length == 0) {
      return;
    } // if array passed is empty, just return

    T pivot = values[0];
    int less = 0;
    int equal = 0;
    int greater = values.length - 1;

    while (equal <= greater) {
      if (order.compare(values[equal], pivot) > 0) {
        T temp = values[equal];
        values[equal] = values[greater];
        values[greater] = temp;
        greater--;
      } else if (order.compare(values[equal], pivot) < 0) {
        T temp = values[equal];
        values[equal] = values[less];
        values[less] = temp;
        less++;
        equal++;
      } else if (order.compare(values[equal], pivot) == 0) {
        equal++;
      } // check current element to pivot, to swap and increment appropriately
    } // loop until entire array is sorted based on pivot

    if (less > 1) {
      sortSub(values, 0, less);
    } // if subarray bigger than one element, sort
    if (equal < values.length) {
      sortSub(values, equal, values.length);
    } // if subarray bigger than one element, sort
  } // sort(T[])
} // class Quicksorter
