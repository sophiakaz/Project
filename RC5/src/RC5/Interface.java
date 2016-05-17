package RC5;

public interface Interface{
  public void setupKey(byte[] K);
	public void encrypt(int[] pt, int[] ct);
	public void decrypt(int[] ct, int[] pt);
}
