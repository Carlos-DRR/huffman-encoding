package huffman;

public class Node {
	private Character letter;
	private Integer occurences;
	private Node leftBranch;
	private Node rightBranch;
	private Node next;

	public Node(Character letter, Integer occurences, Node next, Node leftBranch, Node rightBranch) {
		this.letter = letter;
		this.occurences = occurences;
		this.next = next;
		this.leftBranch = leftBranch;
		this.rightBranch = rightBranch;
	}
	
	public Node getLeftBranch() {
		return leftBranch;
	}


	public void setLeftBranch(Node leftBranch) {
		this.leftBranch = leftBranch;
	}


	public Node getRightBranch() {
		return rightBranch;
	}


	public void setRightBranch(Node rightBranch) {
		this.rightBranch = rightBranch;
	}


	public Character getLetter() {
		return letter;
	}
	public void setLetter(Character letter) {
		this.letter = letter;
	}
	public Integer getOccurences() {
		return occurences;
	}
	public void addOccurences(Integer occurences) {
		this.occurences += occurences;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
	
}
