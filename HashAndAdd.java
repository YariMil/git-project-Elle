import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HashAndAdd {


    public static void addToIndex(String filename){
        try {
            createBlob(filename);
            String hash = new String();
            hash = hashFile(filename);
            File index = new File("git/index.txt");

            StringBuilder oldContent = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(index));

            String oneLine = br.readLine();

            while (oneLine != null) { 
                oldContent.append(oneLine).append("\n");
                oneLine = br.readLine();
            }
            br.close();


            FileWriter indexWriter = new FileWriter(index);
            indexWriter.write(oldContent.toString());
            indexWriter.write(hash + " " + filename + "\n");
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBlob(String fileName) throws IOException { 
        String hash = hashFile(fileName);
        File blob = new File("git/objects/" + hash + ".txt");
        FileWriter blobWriter = new FileWriter("git/objects/" + hash + ".txt");
        String content = new String(Files.readAllBytes(Paths.get(fileName)));
        blobWriter.write(content);//do file content;
        blobWriter.close();
        System.out.println(blob);
        System.out.println(content);
        
    }


    

    public static String hashFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        if(!Files.isRegularFile(path)){
            throw new IOException("no files" + filePath);
        }
        byte[] fileBytes = Files.readAllBytes(path);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalStateException("sha1 not avalible", e);
        }
        byte[] hash = digest.digest(fileBytes);
        System.out.println();

        return HexFormat.of().formatHex(hash);
    }

    public static String hashContent(String content) {

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalStateException("sha1 not avalible", e);
        }
        byte[] hash = digest.digest(content.getBytes());
        System.out.println();

        return HexFormat.of().formatHex(hash);
    }


    public static String getParentDir(String filePath) {
        int index = -1;
        for (int i = 0; i < filePath.length(); i++) {
            if (filePath.charAt(i) == '/') { 
                index = i;
            }
        }
        if (index == -1) { 
            return "";
        }
        return filePath.substring(0, index);
    }
    
}
