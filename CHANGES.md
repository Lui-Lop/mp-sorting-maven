Created new helper method to do quicksort: sorter(T[], int, int)
Updated sort(T[]) in Quicksorter.java to call helper method sorter(T[], int, int)
Deleted subSort(T[], int, int) and calls to it, as well as Arrays import in Quicksorter.java
Created  new helper to handle partition and return new bounds for subarrays, partition(T[], int, int) in Quicksorter.java