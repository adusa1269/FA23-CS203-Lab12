import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
        int diff;
    	String input = readFile(inputFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
        	if(Character.isLetter(c)){
        		int num = (int)c+shift;
        		if(num > 90 && Character.isUpperCase(c)){
        			diff = num-90;
        			num = 65 + diff -1;
        		}else if(num > 122 && Character.isLowerCase(c)){
        			diff = num-122;
        			num = 97 + diff - 1;
        		}
                encrypted.append((char)num);
        	}else{
        		encrypted.append(c);
        	}
        }
        writeFile(encrypted.toString(), encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
        int diff;
    	String input = readFile(messageFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) {
        	if(Character.isLetter(c)){
        		int num = (int)c-shift;
        		if(num < 65 && Character.isUpperCase(c)){
        			diff = 65-num;
        			num = 90 - diff + 1;
        		}else if(num < 97 && Character.isLowerCase(c)){
        			diff = 97-num;
        			num = 122 - diff + 1;
        		}
                encrypted.append((char)num);
        	}else{
        		encrypted.append(c);
        	}
        }
        writeFile(encrypted.toString(), decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))){
        	message = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
            System.out.println("String successfully written to the file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing string to file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}