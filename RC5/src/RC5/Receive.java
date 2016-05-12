package RC5;

public class Receive implements InterfaceE {


	private int[] s; // The expanded list of words derived from the key, of length 2(r+1), with each element being a word
	private int t; // length of s
	private int[] l; // A convenience to encapsulate K as an array of word-sized values rather than byte-sized.
	private int b; // The length of the key in bytes.
	private int u; // The length of a word in bytes. 
	private int c; // 
	private int w; // The length of a word in bits. Typical values of this in RC5 are 16, 32, and 64. Note that a "block" is two words long.
	private byte[] key; // The key, considered as an array of bytes (using 0-based indexing).
	private int rounds; // The number of rounds to use when encrypting data.
	

	public Receive(String a, int round){
		String str = a;
		key = getKeyFromString(str);
		rounds = round;
		b=key.length();
		t = (int)(2 * (rounds + 1));
		c = Math.max(b, 1) / u;

	}
	
	public String encrypt(String a){
		
	}


	private byte[] GetKeyFromString(String str){
		char[] keychar = str.toCharArray();
		byte[] keybytes = new byte[keychar.length];
		for (int i = 0; i < keychar.length; i++){
			keybytes[i] = (byte)keychar[i];
		}
		return keybytes;
	}
		
		
	}
