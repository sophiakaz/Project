package RC5;

public interface Interface{
  public String encrypt(String plainText);
  public String decrypt(String cipherText);
  public String generateKey(String password);
}
