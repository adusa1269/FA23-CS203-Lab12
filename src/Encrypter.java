import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Encrypter 
{

    private int shift;
    private String encrypted;

    public Encrypter() 
    {
        this.shift = 1;
        this.encrypted = "";
    }

    public Encrypter(int s) 
    {
        this.shift = s;
        this.encrypted = "";
    }

    
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception 
    {
        int d;
    	String input = readFile(inputFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) 
        {
        	if(Character.isLetter(c))
            {
        		int n = (int)c+shift;
        		if(n > 90 && Character.isUpperCase(c))
                {
        			d = n-90;
        			n = 65 + d -1;
        		}
                else if(n > 122 && Character.isLowerCase(c))
                {
        			d = n-122;
        			n = 97 + d - 1;
        		}
                encrypted.append((char)n);
        	}else
            {
        		encrypted.append(c);
        	}
        }
        writeFile(encrypted.toString(), encryptedFilePath);
    }

    
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception 
    {
        int d;
    	String input = readFile(messageFilePath);
    	StringBuilder encrypted = new StringBuilder();

        for (char c : input.toCharArray()) 
        {
        	if(Character.isLetter(c))
            {
        		int n = (int)c-shift;
        		if(n < 65 && Character.isUpperCase(c))
                {
        			d = 65-n;
        			n = 90 - d + 1;
        		}else if(n < 97 && Character.isLowerCase(c))
                {
        			d = 97-n;
        			n = 122 - d + 1;
        		}
                encrypted.append((char)n);
        	}else
            {
        		encrypted.append(c);
        	}
        }
        writeFile(encrypted.toString(), decryptedFilePath);
    }

    
    private static String readFile(String filePath) throws Exception 
    {
        String message = "";
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
        {
        	message = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        return message;
    }

    
    private static void writeFile(String data, String filePath) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) 
        {
            writer.write(data);
            System.out.println("String successfully written to the file: " + filePath);
        } catch (IOException e) 
        {
            System.err.println("Error writing string to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() 
    {
        return encrypted;
    }
}