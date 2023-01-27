package Zad1;

public class RouteCipherTest {
    public static void main(String[] args) {
        RouteCipher cipher1 = new RouteCipher(5);
        System.out.printf("Encrypted text of cipher1 is: %s%n", cipher1.encrypt("abort the Mission, you have been spotted"));
        RouteCipher cipher2 = new RouteCipher(-5);
        System.out.printf("Encrypted text of cipher2 is: %s%n", cipher2.encrypt("abort the Mission, you have been spotted"));
        System.out.printf("Decrypted text of cipher1 is: %s%n", cipher1.decrypt(cipher1.encrypt("abort the Mission, you have been spotted")));
        System.out.printf("Decrypted text of cipher2 is: %s%n", cipher2.decrypt(cipher2.encrypt("abort the Mission, you have been spotted")));
        RouteCipher cipher3 = new RouteCipher(4);
        System.out.printf("Decrypted text of cipher3 is: %s%n", cipher3.decrypt("TIEIXTXXEAHSIHSPNTLT"));
        RouteCipher cipher4 = new RouteCipher(0);
        System.out.println("Cipher4:\n" + cipher4);
    }
}
