package bg.uni.sofia.fmi.corejava.stringsrearchtree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {
	private BlockingQueue<Product> sharedQueue;
	private List<String> listWithTextFiles;

	public Producer(BlockingQueue<Product> queue, List<String> listWithTextFiles) {
		this.sharedQueue = queue;
		this.listWithTextFiles = listWithTextFiles;
	}

	@Override
	public void run() {
		synchronized (this.listWithTextFiles) {
			while (!this.listWithTextFiles.isEmpty()) {
				String filePath;

				filePath = this.listWithTextFiles.get(0);
				this.listWithTextFiles.remove(0);

				try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
					String line;
					int lineNumber = 0;
					while ((line = br.readLine()) != null) {
						Product product = new Product(line, filePath, lineNumber);
						this.sharedQueue.put(product);
						lineNumber++;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}