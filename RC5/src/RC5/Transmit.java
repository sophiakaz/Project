package RC5;

public class Transmit  {


	private int[] s; // The expanded list of words derived from the key, of length 2(r+1), with each element being a word
	private int t; // integer 2 * (rounds + 1)
	private int[] L; // A convenience to encapsulate K as an array of word-sized values rather than byte-sized.
	private int b; // The length of the key in bytes.
	private int u; // The length of a word in bytes. 
	private int c; // Math.max(b, 1) / u;
	private int w; // The length of a word in bits (16). Typical values of this in RC5 are 16, 32, and 64. Note that a "block" is two words long(32).
	private int rounds; // The number of rounds to use when encrypting data.
	//private byte[] key; // The key, considered as an array of bytes (using 0-based indexing).

	
	public Transmit(String plainText, String password){
		rounds=8;
		w=16;
		u=w/8;
		//c = Math.max(b, 1) / u;
		t = (int)(2 * (rounds + 1));
		s = new int[t];
		RC5_setup(password);
		encrypt(plainText);
		
	}
	
	public void encrypt(String plainText){
		int[] pt = new int[2];
		byte[] bytes = plainText.getBytes();
		for (int a = bytes.length-1; a!=-1; a--)
		{
			pt[a/u] = (pt[a/u]<<8) + bytes[a];
		}
		
		int A = pt[0] + s[0];
		int B = pt[1] + s[1];
		for(int i =1; i<=rounds; i++){
			A = leftRotate(A ^ B, (int)B) + s[2 * i];
			B = leftRotate(B ^ A, (int)A) + s[2 * i + 1];
		}
		int[] ct = new int[2];
		ct[0] = A;
		ct[1] = B;
	}

	
	private void RC5_setup(String password){
		int i, j;
		int A, B;
		byte[] key = password.getBytes();
		int P16 = 0xb7e1; //47073
		int Q16 = 0x9e37; //40503
		c = (int)Math.max(1,Math.ceil(8*(key.length)/w));
		L = new int[c];
		L[c-1] = 0;
		for (int z = key.length-1; z!=-1; z--)
		{
			L[z/u] = (L[z/u]<<8) + key[z];
		}
		
		s[0] = P16;
		for (int a = 1; a <= t-1; a++)
		{
			s[a] = s[a-1] + Q16;
		}
		A=B=i=j=0;
		int max = 3 * (Math.max(t, c));
		for(int k = 0; k < max; k++)
		{
			A = s[i] = leftRotate((s[i] + A + B), 3);
			B = L[j] = leftRotate((L[j] + A + B), (A + B));
			i = (i + 1) % t;
			j = (j + 1) % c;
		}
	}
	

	private int leftRotate(int x, int offset) {
		int t1, t2;
		t1 = x >> (16 - offset);
		t2 = x << offset;
		return (t1 | t2);
	}

	private int RightRotate(int x, int offset) {
		int t1, t2;
		t1 = x << (16 - offset);
		t2 = x >> offset;
		return (t1 | t2);
	}
	
}
