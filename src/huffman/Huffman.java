package huffman;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Huffman {
	private Lista charSet = new Lista();
	private final String sourceText;
	Map<Character, String> codeTable = new HashMap<Character, String>();
	private Node tree;
	
	public String compressionRatio(int bitsPerCharacter, String encodedText) {
		double sourceTextSize_bits = sourceText.length() * bitsPerCharacter;
		double encodedTextSize_bits = encodedText.length();
		StringBuilder sb = new StringBuilder();
		
		sb.append("Size of source text (bits): " + sourceTextSize_bits);
		sb.append("\nSize of encoded text (bits): " + encodedTextSize_bits);
		sb.append("\nCompression ratio: " + encodedTextSize_bits / sourceTextSize_bits);
		return sb.toString();
	}
	private void generateCodeTable() {
		generateCodeTableRecursive(this.tree, "");
	}
	public void printTable() {
		for(Entry<Character, String> set: this.codeTable.entrySet()) {
			System.out.println(set);
		}
	}
	//decodificar, percorre a árvore baseado no codigo binário e vai montando o texto
	public String decode(String codedText) {
		Node current = this.tree;
		StringBuilder sb = new StringBuilder();
		boolean stopRead = false;
		for(int i = 0; i < codedText.length(); i++) {
			if (stopRead == false)
			{
				current = (codedText.charAt(i) == '0') ? current.getLeftBranch(): current.getRightBranch();
				if(current.getLeftBranch() == null && current.getRightBranch() == null) {
					if(current.getLetter() == '▄') {
						stopRead = true;

						break;
					}
					
					sb.append(current.getLetter());
					current = this.tree;
				}
			}else break;
		}
		return sb.toString();
	}
	public String encode() {
		StringBuilder sb = new StringBuilder();
		int sourceTextLenght = sourceText.length();
		for(int i = 0; i < sourceTextLenght; i++) {
			String code = this.codeTable.get(sourceText.charAt(i));
			if(code != null) {
				sb.append(code);
			}else {
				System.out.println("The character "+ sourceText.charAt(i) + " is not present in the source alfabet");
			}
		}
		return sb.toString();
	}
	//codificar, salva todas as letras e seus codigos no hashmap
	private void generateCodeTableRecursive(Node current, String path) {
		if(current.getLeftBranch() == null && 
				current.getRightBranch() == null) 
		{
			this.codeTable.put(current.getLetter(), path);
		}
		if(current.getLeftBranch() != null) generateCodeTableRecursive(current.getLeftBranch(), path.concat("0"));
		if(current.getRightBranch() != null) generateCodeTableRecursive(current.getRightBranch(), path.concat("1"));
	}
	public Huffman(String text) {
		this.sourceText = text + "▄";
		generateCharSet();
		generateTree();
		generateCodeTable();
	}

	private void generateTree() {
		Node father;
		if(charSet.size() != 1) {
			while(charSet.size() > 1) {
				Node l1 = charSet.getLowestOccurence();
				Node l2 = charSet.getLowestOccurence();
				father = new Node('*', l1.getOccurences() + l2.getOccurences(), null, l1, l2);
				charSet.addNodeInAscendingOrder(father);
			}
		}else {
			Node singleChar = charSet.getLowestOccurence();
			father = new Node('*', singleChar.getOccurences(), null, singleChar, null);
			charSet.addNodeInAscendingOrder(father);
		}

		this.tree = charSet.getLowestOccurence();
	}
	
	private void generateCharSet() {
		for(int i = 0; i < sourceText.length(); i++) {
			Node elementInList = this.charSet.getElementInList(sourceText.charAt(i));
			if(elementInList == null) {
				charSet.addNodeInAscendingOrder(sourceText.charAt(i), 1);
			}else {
				elementInList.addOccurences(1);
			}
		}
	}
	public void printCharSet() {
		this.charSet.printList();
	}
}
