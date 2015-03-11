import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*This class is an implementation of Trie data structure for T9 dictionary.
 * Here the nodes can only have values 2-9.Even though the words may have different 
 * characters,they can have same T9 format(eg:home,good both have 4663).
 * A simple example is given below as a tree structure.
 * Each Node has an array of size 8.It also has a container for holding strings that finish 
 * at that node.It carries 2 counters.One for number of children.Second for number of  
 * finished strings for that node.
 * In that example node 3 has number of children=2.
 * container=[good,home]
 * node 8 has number of children=1.
 * container=[host]
 * Compression can achieve better space complexity.Still to be implemented.
 */

//		 4  
//		/ \
//	   6   4
//	   /\   hi
//	  6  7
//	 /    \
//	3      8
//	good   host
//	home

public class T9_Trie {

	private int numberOfResizes = 0;

	private TrieNode root = new TrieNode();
	private final char[] char_to_num = { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6,
			6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9 };

	/*
	 * Insert new key and value in the trie.
	 */
	public void insert(char[] str) {

		insert(root, str, 0);
	}

	private void insert(TrieNode node, char[] str, int index) {

		if (index == str.length) {
			if (node.size_finished_strings == 0) {

				node.finished_strings = new String[5];

			}
			if (node.finished_strings.length == node.size_finished_strings)
				node.finished_strings = resize(node.finished_strings,
						node.size_finished_strings);
			node.finished_strings[node.size_finished_strings++] = new String(
					str);

			return;

		}

		char c = str[index];
		int number_equivalent = char_to_num[c - 'a'] - 2;

		if (node.children[number_equivalent] == null) {

			node.children[number_equivalent] = new TrieNode();
			node.number_of_children++;

		}

		insert(node.children[number_equivalent], str, index + 1);

	}

	/*
	 * Search for a key in the trie.
	 */
	public String[] search(char[] search_str) {

		return search(root, search_str, 0);

	}

	private String[] search(TrieNode node, char[] search_str, int index) {
		if (node == null)
			return null;
		if (index == search_str.length) {

			if (node.size_finished_strings == 0)
				return null;

			return node.finished_strings;
		}

		char c = search_str[index];
		int children_index = c - '0' - 2;

		return search(node.children[children_index], search_str, index + 1);

	}

	public void compress() {

		// To be implemented.
	}

	/*
	 * Instead of using the Collections classes resize yourself.
	 */
	private String[] resize(String[] old, int old_size) {
		numberOfResizes++;
		String[] new_arr = new String[old_size + 5];
		for (int i = 0; i < old_size; i++)
			new_arr[i] = old[i];
		old = null;
		return new_arr;
	}

	public static void main(String[] args) throws Exception {

		T9_Trie t = new T9_Trie();
		BufferedReader br = new BufferedReader(new FileReader(new File(
				"ewords.txt")));
		// int number_of_words = Integer.parseInt(br.readLine());
		String word = null;
		while ((word = br.readLine()) != null) {

			t.insert(word.toCharArray());
		}
		// t.insert("GOOD".toCharArray());
		System.out.println("Number opf resizes " + t.numberOfResizes);
		br.close();

		br = new BufferedReader(new InputStreamReader(System.in));
		String search = null;
		while ((search = br.readLine()) != null) {
			String[] possibilities = t.search(search.toCharArray());
			for (String poss : possibilities)
				if (poss != null)
					System.out.print(poss + " ");
			System.out.println();
			;

		}

	}

}

class TrieNode {
	TrieNode[] children = new TrieNode[8];
	int size_finished_strings = 0;
	int number_of_children = 0;
	String[] finished_strings = null;

}
