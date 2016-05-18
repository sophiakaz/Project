package RC5;

import java.util.Arrays;

public class RC5-Runner {
	public static void main(String[] args) {
		
		   int i;
	       int j;
	       int b = 16;
	       int[] pt1 = new int[2];
	       int[] pt2 = new int[2];
	       int[] ct = {0b0011,0b0101}; //ct[0]=3, ct[1]=5
	       byte[] key = new byte[b];
	       String pwd = new String("password12345678");
	       
	       key = pwd.getBytes();

	       System.out.print("RC5 with w/r/b = 32/12/16 tests:\n");	       

	       for (i = 1;i < 6;i++){ // Initialize pt1 based on current ct values
	         pt1[0] = ct[0];
	         pt1[1] = ct[1];

	         /* Setup, encrypt, and decrypt */
	         Transmit h = new Transmit(key, pt1, ct);
		     Receive x = new Receive(key, ct, pt2);

	         /* Print out results, checking for decryption failure */
	         System.out.printf("\n%d. Key = ",i);
	   	     System.out.println(Arrays.toString(key ));
	         System.out.println("   Inputdata: "  + pt1[0]  + pt1[1] +   " ---> " +  "Encrypted: " + ct[0]+ct[1]);
	         System.out.println("   Decrypted: "  + pt2[0]  + pt2[1] );

	         if (pt1[0] != pt2[0] || pt1[1] != pt2[1]){
	             System.out.print("Decryption Error!");
	         }
	       }
	  }
}
