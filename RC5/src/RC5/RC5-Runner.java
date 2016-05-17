package RC5;

import java.util.Arrays;

public class RC5Runner {
	public static void main(String[] args) {
		
		int i;
	       int j;
	       int b = 16;
	       int[] pt1 = new int[2];
	       int[] pt2 = new int[2];
	       int[] ct = {1,2};
	       byte[] key = new byte[b];
	       
	        
	       System.out.print("RC5-32/12/16 examples:\n");
	       for (i = 1;i < 6;i++){ // Initialize pt1 and key pseudo-randomly based on previous ct
	         pt1[0] = ct[0];
	         pt1[1] = ct[1];

	         for (j = 0;j < b;j++){
	             key[j] = (byte) (ct[0] % (255 - j));
	         }
	         /* Setup, encrypt, and decrypt */
	         
	         Transmit h = new Transmit(key, pt1, ct);
		     Receive r = new Receive(key, ct, pt2);

	         /* Print out results, checking for decryption failure */
	         System.out.printf("\n%d. key = ",i);
	         for (j = 0; j < b; j++){
	             System.out.print((key[j])+" ");
	         }
	         System.out.println("\n   Inputdata:"  + pt1[0]  + pt1[1] +   " ---> " +  "Encrypted:" + ct[0]+ct[1]);
	         System.out.println("   Decrypted:"  + pt2[0]  + pt2[1] );

	         if (pt1[0] != pt2[0] || pt1[1] != pt2[1]){
	             System.out.print("Decryption Error!");
	         }
	       }
	  }
}
