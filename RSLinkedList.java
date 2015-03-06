/*
 * Linked List implementations with some non-trivial 
 * operations like reverse(),sort().
 * Sorting uses excellent megesort algorithm given by Simon Tatham
 * The intended use is for use in Hackathons.
 * Provides constructor which taken an array.
 * Also provides constructor which taken an int 
 * using which elements can be inserted one by one.
 * 
 * 
 * @author  Raghuveer Sagar
 */



public class RSLinkedList {

	Node head = null;

	public RSLinkedList(Node n) {
		head = n;
	}

	public void print() {
		Node tmp = head;

		while (tmp.next != null) {
			System.out.print(tmp.data + "->");
			tmp = tmp.next;
		}
		System.out.println(tmp.data);

	}

	public void insert(int[] arr) {
		for (int a : arr)
			insert(a);
	}

	public void insert(int x) {

		if (head == null)
			head = new Node(x, null);

		else {
			Node temp = head;
			while (temp.next != null)
				temp = temp.next;

			temp.next = new Node(x, null);
		}

	}

	
	//Hare and the tortoise algorithm
	public int getMiddle() {
		Node it1 = head;
		Node it2 = head;

		while (it2.next != null) {

			it2 = it2.next;
			if (it2.next == null)
				break;
			it2 = it2.next;
			it1 = it1.next;

		}

		return it1.data;

	}

	public RSLinkedList reverse() {
		Node tmp = head;
		Node rev = reverse(tmp);
		return new RSLinkedList(rev);

	}

	public Node reverse(Node n) {
		if (n.next == null)
			return n;

		Node r = reverse(n.next);
		n.next.next = n;
		n.next = null;
		return r;
	}

	public RSLinkedList sort() {

		Node tmp = head;
		return new RSLinkedList(sort(tmp));
	}

	private Node sort(Node list) {

		Node p = null;
		Node q = null;
		int p_size = 0;
		int q_size = 0;
		int k = 1;
		Node tail = null;
		Node e = null;
		int merges = 0;
		while (true) {
			p = list;
			tail = null;

			if (merges == 1)
				return list;
			merges = 0;
			p_size = 0;
			q_size = 0;
			while (p != null) {
				merges++;
				q = p;
				q_size = k;
				p_size = 0;
				for (int i = 0; i < k; i++) {
					q = q.next;
					p_size++;
				}

				while (p_size > 0 || (q_size > 0 && q != null)) {

					if (p_size == 0) {
						e = q;
						q = q.next;
						q_size--;
					} else if (q_size == 0) {
						e = p;
						p_size--;
						p = p.next;
					} else {

						if (p.data < q.data) {
							e = p;
							p_size--;
							p = p.next;

						}

						else {
							e = q;
							q = q.next;
							q_size--;

						}

					}
					if (tail == null) {
						list = e;
					}

					else {
						e.next = null;
						tail.next = e;
					}

					tail = e;
				}

				p = q;

			}
			k = 2 * k;

		}

	}

	private class Node {
		int data;
		Node next;

		Node(int d, Node n) {
			data = d;
			next = n;

		}

	}
}