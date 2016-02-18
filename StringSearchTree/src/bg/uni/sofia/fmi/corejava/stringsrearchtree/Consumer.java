package bg.uni.sofia.fmi.corejava.stringsrearchtree;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
	private BlockingQueue<Product> sharedQueue;
	private String searchString;

	public Consumer(BlockingQueue<Product> queue, String searchString) {
		this.sharedQueue = queue;
		this.searchString = searchString;
	}

	@Override
	public void run() {
		while (true) {
			try {
				consume(this.sharedQueue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void consume(Product product) {
		if (product != null) {
			if (product.getLine().toLowerCase().contains(searchString.toLowerCase())) {
				System.out.println("String found in file: " + product.getFileName() + " on line: "
						+ product.getLineNumber() + ". Line: " + product.getLine());
			}
		}
	}
}