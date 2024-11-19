package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *            The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Luis Lopez
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Merges two sorted subarrays of an original array to sort original.
   *
   * @param values
   *               an array to be sorted
   * @param left
   *               sorted left subarray of values
   * @param right
   *               sorted right subarray of values
   * @post
   *       merge two subarrays of values back into values
   */
  public void merge(T[] values, T[] left, T[] right) {
    int leftInd = 0;
    int rightInd = 0;

    for (int i = 0; i < values.length; i++) {
      if (leftInd >= left.length) {
        values[i] = right[rightInd++];
      } else if (rightInd >= right.length) {
        values[i] = left[leftInd++];
      } else if (order.compare(left[leftInd], right[rightInd]) > 0) {
        values[i] = right[rightInd++];
      } else if (order.compare(left[leftInd], right[rightInd]) < 0) {
        values[i] = left[leftInd++];
      } else if (order.compare(left[leftInd], right[rightInd]) == 0) {
        values[i] = left[leftInd++];
      } // checks if element in array is less than to copy to original array
        // or if only one array is left copies from there
    } // loop over entire original array
  } // merge(T[], T[], T[])

  /**
   * Sort an array in place using merge sort.
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
    int rightArrStart = values.length / 2;

    T[] left = Arrays.copyOfRange(values, 0, rightArrStart);
    T[] right = Arrays.copyOfRange(values, rightArrStart, values.length);

    if (left.length > 1) {
      sort(left);
    } // if subarray is bigger than one element, sort
    if (right.length > 1) {
      sort(right);
    } // if subarray is bigger than one element, sort

    merge(values, left, right);
  } // sort(T[])
} // class MergeSorter
