package edu.grinnell.csc207.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
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
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; vals.length,
   *     order.compare(vals[i-1], vals[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    int rightArrStart = values.length / 2;
    
    T[] left = Arrays.copyOfRange(values, 0, rightArrStart);
    T[] right = Arrays.copyOfRange(values, rightArrStart, values.length);

    if (left.length > 1) {
      sort(left);
    }
    if (right.length > 1) {
      sort(right);
    }

    int leftInd = 0;
    int rightInd = 0;

    for (int i = 0; i < values.length; i ++) {
      if (leftInd >= left.length) {
        values[i] = right[rightInd];
        rightInd++;
      } else if (rightInd >= right.length) {
        values[i] = left[leftInd];
        leftInd++;
      } else if (order.compare(left[leftInd], right[rightInd]) > 0) {
        values[i] = right[rightInd];
        rightInd++;
      } else if (order.compare(left[leftInd], right[rightInd]) < 0) {
        values[i] = left[leftInd];
        leftInd++;
      } else if (order.compare(left[leftInd], right[rightInd]) == 0) {
        values[i] = left[leftInd];
        leftInd++;
      }
    }

    // for (int i = 0; i < values.length; i++) {
    //   if (leftInd >= left.length) {
    //     values[i] = right[rightInd];
    //     rightInd++;
    //   } else if (rightInd >= right.length) {
    //     values[i] = left[leftInd];
    //     leftInd++;
    //   } else if (order.compare(left[leftInd], right[rightInd]) < 0) {
    //     values[i] = left[leftInd];
    //     leftInd++;
    //   } else if (order.compare(right[rightInd], left[leftInd]) > 0) {
    //     values[i] = right[rightInd];
    //     rightInd++;
    //   } else if (order.compare(right[rightInd], left[leftInd]) == 0) {
    //     values[i] = left[leftInd];
    //     leftInd++;
    //   }
    // } 
  } // sort(T[])
} // class MergeSorter
