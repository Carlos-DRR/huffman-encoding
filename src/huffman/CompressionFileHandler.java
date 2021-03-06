package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class CompressionFileHandler {
	private File file;
	private String sourceData;
	private String sourceFilePath;
	private String encodedData;
	private Huffman hf;
	
	public CompressionFileHandler() {

	}
	
	public void readFile(String path) {
		this.sourceFilePath = path;
        File file = new File(this.sourceFilePath);
        this.file = file;
	    try {
	        Scanner sc = new Scanner(this.file);
	        while (sc.hasNextLine()) {
	        	this.sourceData = sc.nextLine();
	        }
	        sc.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("Erro de arquivo n?o encontrado");
	        e.printStackTrace();
	      }
	}
	
	private void saveCompressedFile(String path) {
        try {
            FileOutputStream fout
                    = new FileOutputStream(path);
            fout.write(Conversion.convertBitStringToByteString(this.encodedData));
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
       
	}
	
	public void compress(String path) {
		this.hf = new Huffman(this.sourceData);
		this.encodedData = this.hf.encode();
		saveCompressedFile(path);
	}
	
	public String decompress(String compressedPath, String uncompressedOutPutPath) {
        File file = new File(compressedPath);
        String finalData="erro";
        try {
			byte[] bytes = Files.readAllBytes(file.toPath());
			finalData = this.hf.decode(Conversion.convertByteArrayToString(bytes));
			
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
        this.writeUncompressedFile(finalData, uncompressedOutPutPath);
        return finalData;
	}
	public void writeUncompressedFile(String uncompressedText, String path) {
	    try {
	        FileWriter myWriter = new FileWriter(path);
	        myWriter.write(uncompressedText);
	        myWriter.close();
	        System.out.println("Arquivo escrito com sucesso");
	      } catch (IOException e) {
	        System.out.println("Erro ocorreu");
	        e.printStackTrace();
	      }
	}
}
