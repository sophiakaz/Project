package RC5;
import java.util.Arrays;

public class Transmit implements Interface {
	public static final int w = 32;
    public static final int r = 12;
    public static final int b = 16;
    public static final int c = 4;
    public static final int t = 26;
     
    public int[] S = new int[t]; // expanded key table
    public int P = (int)0xb7e15163; // magic constants for w=32bits
    public int Q = (int)0x9e3779b9;
    
    public Transmit(byte[] key, int[] pt, int[] ct){
    	setupKey(key);
    	encrypt(pt,ct);
    }
    
    public void encrypt(int[] pt, int[] ct){ // input=pt/output=ct
        int i;
        int A = pt[0] + S[0];
        int B = pt[1] + S[1];
        for (i = 1; i <= r; i++){
            A = (((A ^ B) << (B & (w - 1))) | ((A ^ B)>>>(w - (B & (w - 1))))) + S[2 * i];
            B = (((B ^ A) << (A & (w - 1))) | ((B ^ A)>>>(w - (A & (w - 1))))) + S[2 * i + 1];
        }
        ct[0] = A;
        ct[1] = B;
    }
    
    public void decrypt(int[] ct, int[] pt){ // input=ct/output=pt
        int i;
        int B = ct[1];
        int A = ct[0];
        for (i = r; i > 0; i--){
            B = (((B - S[2 * i + 1])>>>(A & (w - 1))) | ((B - S[2 * i + 1]) << (w - (A & (w - 1))))) ^ A;
            A = (((A - S[2 * i])>>>(B & (w - 1))) | ((A - S[2 * i]) << (w - (B & (w - 1))))) ^ B;
        }
        pt[1] = B - S[1];
        pt[0] = A - S[0];
    }
    
    public void setupKey(byte[] K){ // secret input key K[0...b-1]
        int i;
        int j;
        int k;
        int u = w / 8;
        int A;
        int B;
        int[] L = new int[c];
        /* Initialize L, then S, then mix key into S */
        for (i = b - 1,L[c - 1] = 0; i != -1; i--){
            L[i / u] = (L[i / u] << 8) + K[i];
        }
        for (S[0] = P,i = 1; i < t; i++) {
            S[i] = S[i - 1] + Q;
        }
        for (A = B = i = j = k = 0; k < 3 * t; k++,i = (i + 1) % t,j = (j + 1) % c) {// 3*t > 3*c
            A = S[i] = (((S[i] + (A + B)) << (3 & (w - 1))) | ((S[i] + (A + B))>>>(w - (3 & (w - 1)))));
            B = L[j] = (((L[j] + (A + B)) << ((A + B) & (w - 1))) | ((L[j] + (A + B))>>>(w - ((A + B) & (w - 1)))));
        }
    }
    
}
