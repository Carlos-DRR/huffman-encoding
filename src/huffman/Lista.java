package huffman;

public class Lista {
	private Node head;

	public Lista() {
		this.head = null;
	}

	public boolean empty() {
		return head ==  null;
	}
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	public int size() {
		Node current = this.head;
		int sum = 0;
		while(current!= null) {
			current = current.getNext();
			sum +=1;
		}
		return sum;
	}
	public void printList() {
		Node current = this.head;
		while(current!= null) {
			System.out.println(current.getLetter() + " : " + current.getOccurences());
			current = current.getNext();
		}
	}
	public Node getElementInList(Character letter) {
		Node current = this.head;
		while(current!= null && current.getLetter() != letter) {
			current = current.getNext();
		}
		if(current == null) return null;
		else return current;
	}
	/*
	public boolean isInList(Character letter) {
		Node current = this.head;
		while(current!= null && current.getLetter() != letter) {
			current = current.getNext();
		}
		if(current == null) return false;
		else return true;
	}*/
	public void addNodeInAscendingOrder(Character ch, Integer occ) {
		Node newNode = new Node(ch, occ, null, null, null);
		addNodeInAscendingOrder(newNode);
	}
	
	public void addNodeInAscendingOrder(Node newNode) {
		if(empty()) {
			this.head = newNode;
		}else{
			Node current = this.head;
			Node previous = this.head;
			while(current!= null && current.getOccurences() < newNode.getOccurences()) {
				previous = current;
				current = current.getNext();
			}
			if(current == null) {
				previous.setNext(newNode);
			}else {
				if(current == this.head) {
					newNode.setNext(this.head);
					this.head = newNode;
				}else {
					previous.setNext(newNode);
					newNode.setNext(current);
				}

			}
			current = previous = null;
		}
	}
	public Node getLowestOccurence() {
		Node lowest = this.head;
		this.head = this.head.getNext();
		return lowest;
	}
}
