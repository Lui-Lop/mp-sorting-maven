package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *            The types of values that are sorted.
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
   *                   The order in which elements in the array should be ordered
   *                   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    sorter(values, 0, values.length);
  } // sort(T[])

  /**
   * Puts values based on a pivot into subarrays,
   * one for less than pivot, one for equal to pivot, one for greater than pivot.
   *
   * @param values
   *               original array values for partition are from
   * @param lb
   *               lowerbound of subarray partition will be done on, inclusive
   * @param ub
   *               upperbound of subarray partition will be done on, exclusive
   * @return
   *         an array of integer that are used as bounds for the next partition
   */
  public int[] partition(T[] values, int lb, int ub) {
    int[] bounds = new int[2];

    T pivot = values[lb];
    int less = lb;
    int equal = lb;
    int greater = ub - 1;

    while (equal <= greater) {
      if (order.compare(values[equal], pivot) > 0) {
        T temp = values[equal];
        values[equal] = values[greater];
        values[greater--] = temp;
      } else if (order.compare(values[equal], pivot) < 0) {
        T temp = values[equal];
        values[equal++] = values[less];
        values[less++] = temp;
      } else if (order.compare(values[equal], pivot) == 0) {
        equal++;
      } // check current element to pivot, to swap and increment appropriately
    } // loop until entire array is sorted based on pivot

    bounds[0] = less;
    bounds[1] = equal;
    //sets indices in array to bounds we need for next call of partition
    return bounds;
  } // partition

  /**
   * Sorts the portion of the array in place using Quicksort
   * to help sort(T[]).
   *
   * @param values
   *               origional array where portion is being taken from
   * @param lb
   *               lower bound, inclusive
   * @param ub
   *               upper bound, exclusive
   */
  public void sorter(T[] values, int lb, int ub) {
    if (ub == lb) {
      return;
    }  // bounds are the same, empty subarray

    int[] bounds = new int[2];
    bounds = partition(values, lb, ub);
    int less = bounds[0];
    //sets a bound for next call to sorter
    int equal = bounds[1];
    //sets a boundn for next call to sorter

    if (less > 1) {
      sorter(values, lb, less);
    } // if subarray bigger than one element, sort
    if (equal < ub) {
      sorter(values, equal, ub);
    } // if subarray bigger than one element, sort
  } // sorter(T[], int, int)
} // class Quicksorter
