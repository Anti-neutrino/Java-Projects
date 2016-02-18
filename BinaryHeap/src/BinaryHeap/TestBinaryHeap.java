package BinaryHeap;

public class TestBinaryHeap {
	public static void main(String[] args) {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>();
		heap.insert(1);
		heap.insert(23);
		heap.insert(-1);
		heap.extractMax();
		heap.extractMax();
		System.out.print(heap.peek());
	}
}