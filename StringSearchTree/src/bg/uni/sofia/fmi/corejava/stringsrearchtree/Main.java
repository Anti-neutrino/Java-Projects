package bg.uni.sofia.fmi.corejava.stringsrearchtree;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	private static BlockingQueue<Product> sharedQueue = new LinkedBlockingQueue<>(Constants.STORE_CAPACITY);

	private static List<Thread> createConsumer(String searchedString) {
		List<Thread> consumers = new ArrayList<>();

		for (int i = 0; i < Constants.CONSUMERS_COUNT; i++) {
			Thread thread = new Thread(new Consumer(sharedQueue, searchedString));
			thread.setDaemon(true);
			consumers.add(thread);
		}

		return consumers;
	}

	private static List<Thread> createProducer(List<String> listWithFiles) {
		List<Thread> producers = new ArrayList<>();

		for (int i = 0; i < Constants.PRODUCERS_COUNT; i++) {
			Thread thread = new Thread(new Producer(sharedQueue, listWithFiles));
			producers.add(thread);
		}

		return producers;
	}

	private static void findTextFiles(String searchedString, List<String> listWithFiles) {
		List<Thread> producers = createProducer(listWithFiles);
		List<Thread> consumers = createConsumer(searchedString);

		for (int i = 0; i < producers.size(); i++) {
			producers.get(i).start();
		}

		for (int i = 0; i < consumers.size(); i++) {
			consumers.get(i).start();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		String dirPath = "C:\\Solutions\\JavaSolutions";
		Path dirTree = Paths.get(dirPath);
		String searchedString = "barabanche";
		List<String> listWithTextFiles = FindAllTextFiles.findTextFiles(dirTree.toFile());
		findTextFiles(searchedString, listWithTextFiles);
	}
}