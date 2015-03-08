/*Class contains merge and quicksort
 * Mostly need these in hackathons.
 * 
 * 
 * 
 * @author Raghuveer Sagar
 * 
 */

public class Sorts {

	public static int[] mergesort(int[] x) {

		int[] y = new Sorts().merge_sort(0, x.length - 1, x);
		return y;

	}
	
	public static int[] quicksort(int[] x) {
		
		
		return new int[]{-99};
	
	}
	

	int[] merge_sort(int start, int end, int[] arr) {

		if (start == end)
			return new int[] { arr[start] };

		int mid = (start + end) / 2;
		int m[] = merge_sort(start, mid, arr);
		int n[] = merge_sort(mid + 1, end, arr);

		return merge(m, n, mid - start + 1, end - mid);

	}

	int[] merge(int[] m, int[] n, int m_size, int n_size) {
		int[] ans = new int[m_size + n_size];
		int i = 0, j = 0, k = 0;

		while (i < m_size && j < n_size) {

			if (m[i] < n[j]) {
				ans[k++] = m[i++];

			}

			else {
				ans[k++] = n[j++];

			}
		}

		while (i < m_size) {
			ans[k++] = m[i++];

		}

		while (j < n_size) {
			ans[k++] = n[j++];
		}

		return ans;

	}

}
