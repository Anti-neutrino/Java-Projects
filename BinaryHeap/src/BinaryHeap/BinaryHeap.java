package BinaryHeap;

import java.util.ArrayList;

public class BinaryHeap<E extends Comparable<E>> {
	private ArrayList<E> list;

	private void heapifyDown(Integer i) {
		Integer left = 2 * i + 1;
		Integer rigth = 2 * i + 2;
		Integer largest = i;

		if (left < list.size() && list.get(left).compareTo(list.get(largest)) > 0) {
			largest = left;
		}
		if (rigth < list.size() && list.get(rigth).compareTo(list.get(largest)) > 0) {
			largest = rigth;
		}

		if (largest != i) {
			E old = list.get(i);
			list.set(i, list.get(largest));
			list.set(largest, old);
			heapifyDown(largest);
		}
	}

	private void heapifyUp(Integer i) {
		Integer parent = (i - 1) / 2;
		while (i > 0 && list.get(i).compareTo(list.get(parent)) > 0) {
			E old = list.get(i);
			list.set(i, list.get(parent));
			list.set(parent, old);

			i = parent;
			parent = (i - 1) / 2;
		}
	}

	public BinaryHeap() {
		this.list = new ArrayList<>();
	}

	public E extractMax() {
		E element = list.get(0);
		list.set(0, list.get(list.size() - 1));
		list.remove(list.size() - 1);

		if (list.size() > 0) {
			heapifyDown(0);
		}

		return element;
	}

	public E peek() {
		return this.list.get(0);
	}

	public Integer count() {
		return this.list.size();
	}

	public void insert(E element) {
		list.add(element);
		heapifyUp(list.size() - 1);
	}
}