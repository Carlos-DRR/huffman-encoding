package huffman;

public class Main {
	public static void main(String[] args){
		CompressionFileHandler fl = new CompressionFileHandler();
		fl.readFile("C:/Users/carlo/Desktop/teste.txt");
		fl.compress("C:/Users/carlo/Desktop/teste.out");
		fl.decompress("C:/Users/carlo/Desktop/teste.out", "C:/Users/carlo/Desktop/testeDescompresso.txt");
	}

}
