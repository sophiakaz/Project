package RC5;

import java.util.Arrays;

public class RC5-Runner {
	public static void main(String[] args) {
		//Receive test = new Receive("testing testing", 5);

		byte[] bytetest = new byte[] { (byte) 0xe0, (byte) 0x4f, (byte) 0xd0,
				(byte) 0x20, (byte) 0xea };
		
		//System.out.println(Arrays.toString(bytetest));
		System.out.println((int)(bytetest[0] + bytetest[1]) + " " + (int)(bytetest[2] + bytetest[3]));

		Transmit demo = new Transmit(bytetest, 2);
		System.out.println(demo.encode((int)(bytetest[0] + bytetest[1]),(int)(bytetest[2] + bytetest[3])));
		
		Receive demo2 = new Receive(bytetest, 2);
		System.out.println(demo2.decrypt(-15464, -1620917327));
		
		System.out.println(demo.encode(-514935701, 514935702));
		

	}

}
