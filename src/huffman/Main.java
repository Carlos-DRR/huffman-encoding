package huffman;


public class Main {

	public static void main(String[] args){

		String srcText = "Oi meu nome é Carlos Dimitri, e esse texto pode ser codificado por Huffman";
		System.out.println(srcText);
		
		Huffman hf = new Huffman(srcText);
		String encodedText = hf.encode();
		System.out.println("Huffman encoded text: " + encodedText);
		String decodedText = hf.decode(encodedText);
		System.out.println("Huffman decoded text: " + decodedText);
		System.out.println("Compression ratio: " + hf.compressionRatio(8, encodedText));
	}

}
