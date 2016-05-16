package RC5;

import java.util.Arrays;

public class Receive {
	
	private int[] s; // The expanded list of words derived from the key, of length 2(r+1), with each element being a word
	private int t; // integer 2 * (rounds + 1)
	private int[] L; // A convenience to encapsulate K as an array of word-sized values rather than byte-sized.
	private int b; // The length of the key in bytes.
	private int u; // The length of a word in bytes. 
	private int c; // Math.max(b, 1) / u;
	private int w; // The length of a word in bits (16). Typical values of this in RC5 are 16, 32, and 64. Note that a "block" is two words long(32).
	private int rounds; // The number of rounds to use when encrypting data.

	
	public Receive(){
		rounds=12;
		w=16;
		u=w/8;
		//c = Math.max(b, 1) / u;
		t = (int)(2 * (rounds + 1));
		s = new int[t];
	}

	public String decrypt(String cipherText){
		byte[] ctbytes = cipherText.getBytes();
		int[] ct = new int[(ctbytes.length)/2];   //pt is plaintext
		int[] pt = new int[ct.length];			//ct is ciphertext
		
		for (int a = ctbytes.length-1; a!=-1; a--)
		{
			ct[a/u] = leftRotate(ct[a/u],8) + ctbytes[a];
		}
		
		for (int loop = ct.length - 1; loop > 0; loop=loop-2)
		{
		int A = 0;
		int B = 0;
		for (int i=rounds; i>=1; i--){
				
				B = (((B - s[2 * i + 1])>>> A)) ^ A;
				A = (((A - s[2 *i]) >>> B)) ^ B;
			}
			pt[loop] = B - s[loop];
			pt[loop-1] = A - s[loop-1];
		}
		
		byte[] ptbytes = new byte[ctbytes.length];
		for (int a = ptbytes.length-1; a!=-1; a--)
		{
			ptbytes[a] = (byte)(pt[a/u] - (pt[a/u]>>>8));	//REVERSED
		}
		String decrypted = new String(ptbytes);
		return decrypted;
				
	}	
	
	public String generateKey(String password){
		int i, j;
		int A, B;
		byte[] key = password.getBytes();
		b = key.length;
		int Pw = 0xb7e1;
		int Qw = 0x9e37; 
		c = (int)Math.max(1,Math.ceil(8*b/w));
		L = new int[c];
		L[c-1] = 0;
		for (int z = b-1; z!=-1; z--)
		{
			L[z/u] = leftRotate(L[z/u],8) + key[z];
		}
		s[0] = Pw;
		for (int a = 1; a <= t-1; a++)
		{
			s[a] = s[a-1] + Qw;
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
		return Arrays.toString(key) + "\n" + Arrays.toString(L);
	}
	

	private int leftRotate(int x, int offset) {
		int t1, t2;
		t1 = x >> (16 - offset);
		t2 = x << offset;
		return (t1 | t2);
	}

	private int rightRotate(int x, int offset) {
		int t1, t2;
		t1 = x << (16 - offset);
		t2 = x >> offset;
		return (t1 | t2);
	}
	
}

