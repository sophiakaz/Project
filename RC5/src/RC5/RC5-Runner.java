package RC5;

import java.util.Arrays;

public class RC5Runner {
	public static void main(String[] args) {
		
		Transmit test = new Transmit();
		System.out.println(test.generateKey("pass"));
		System.out.println(test.encrypt("encrypt this"));
		
		Receive demo = new Receive();
		System.out.println(demo.generateKey("pass"));
		System.out.println(demo.decrypt("ëë55^^BBxxãã"));
		
	}

}
