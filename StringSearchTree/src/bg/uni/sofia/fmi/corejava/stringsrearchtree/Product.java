package bg.uni.sofia.fmi.corejava.stringsrearchtree;

public class Product {
	private String line;
	private String fileName;
	private long lineNumber;

	public Product(String line, String fileName, long lineNumber) {
		this.line = line;
		this.fileName = fileName;
		this.lineNumber = lineNumber;
	}

	public String getLine() {
		return this.line;
	}

	public long getLineNumber() {
		return this.lineNumber;
	}

	public String getFileName() {
		return this.fileName;
	}
}