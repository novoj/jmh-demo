package net.novoj;

/**
 * ShellSort implementation. Source: https://www.baeldung.com/java-shell-sort
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class ShellSort {

	public static void shellSort(int[] arrayToSort) {
		int n = arrayToSort.length;

		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				int key = arrayToSort[i];
				int j = i;
				while (j >= gap && arrayToSort[j - gap] > key) {
					arrayToSort[j] = arrayToSort[j - gap];
					j -= gap;
				}
				arrayToSort[j] = key;
			}
		}
	}

}
