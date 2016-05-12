package RC5;

public class Transmit implements InterfaceE {


	private int[] s; // The expanded list of words derived from the key, of length 2(r+1), with each element being a word
	private int t; // integer 2 * (rounds + 1)
	private int[] l; // A convenience to encapsulate K as an array of word-sized values rather than byte-sized.
	private int b; // The length of the key in bytes.
	private int u; // The length of a word in bytes. 
	private int c; // Math.max(b, 1) / u;
	private int w; // The length of a word in bits (16). Typical values of this in RC5 are 16, 32, and 64. Note that a "block" is two words long(32).
	private byte[] key; // The key, considered as an array of bytes (using 0-based indexing).
	private int rounds; // The number of rounds to use when encrypting data.
	

	
	public Transmit(byte[] password, int round){
		String str = a;
		key = password;
		rounds = round;
		b = (int)password.length;
		u=16;
		c = Math.max(b, 1) / u;
		t = (int)(2 * (rounds + 1);
		s = new int[t];
		l = new int[password.length];
		GenerateKey(key, rounds);
	}
	
	public Transmit(String a, int round){
		String str = a;
		key = getKeyFromString(str);
		rounds = round;
		b=key.length();
		u=16;
		c = Math.max(b, 1) / u;
		t = (int)(2 * (rounds + 1);
		s = new int[t];
		l = new int[c];
		GenerateKey(key, rounds);
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
	
	private void GenerateKey(byte[] key, int rounds){
		int P32 = Integer.parseInt("b7e15163", System.Globalization.NumberStyles.HexNumber);
		int Q32 = Integer.parseInt("9e3779b9", System.Globalization.NumberStyles.HexNumber);
		for (int i = key.length - 1; i >= 0; i--){
			l[i/u] =(l[i/u] <<< 8) + key[i];
		}
		s[0] = P32;
		for (int i = 1; i <= t - 1; i++){
			s[i] = s[i - 1] + Q32;
		}
		int i, j;
		i = j = 0;
		int x, y;
		x = y = 0;
		int top = 3 * Math.max(t, c);
		for (int counter = 0; counter <= top; counter++){
			x = s[i] = (s[i] + x + y) <<< 3;
			y = l[j] = (l[j] + x + y) <<< (int)(x + y));
			i = (i + 1) % t;
			j = (j + 1) % c;
		}
	}	
	
		
}
