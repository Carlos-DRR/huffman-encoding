package huffman;

public class Conversion {
	private static int formatSize(int size) {
		while(size%8 != 0) {
			size +=1;
		}
		return size;
	}
	public static String convertByteArrayToString(byte[] byteArray) {
		StringBuilder sb = new StringBuilder();
		String a;
		for(byte b: byteArray) {
			a = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replaceAll("\\s", "0");
			sb.append(a);
		}
		return sb.toString();
	}
	public static String fillZeros(String bitString, int firstIndex, int size) {
		for(int i = firstIndex; i < size - 1; i++) {
			bitString += '0';
		}
		return bitString;
	}
	public static byte[] convertBitStringToByteString(String bitString) {
		int size = formatSize(bitString.length());
		byte [] codeValues = new byte[size/8];
		byte value = 0;
		int count = 0;
		int index = 0;

		bitString = fillZeros(bitString, bitString.length() - 1, size);
		
		for(int i = 0; i< bitString.length(); i++) {
			count += 1;
			value = (byte) (value* 2 + (bitString.charAt(i) == '0' ? 0:1)); 
			if(count == 8) {
				codeValues[index] = value ;
				value = 0;
				count = 0;
				index+=1;
			}
		}
		return codeValues;
	}
}
