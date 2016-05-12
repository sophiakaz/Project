package RC5;

public class Transmit implements InterfaceE {

	public Transmit(){
	
	}
	
		
	public String encrypt(String a){
		
	}
	
	private byte[] GetStringFromKey(byte[] keybites){
		char[] keychar = new char[keybites.length];
		for (int i = 0; i < keybites.length; i++){
			keychar[i] = (char)keybites[i];
		}
		String str= "";
		for (int j = 0; j<keychar.length; j++)
		{
			str += keychar[i];
		}
}
