import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;

public class Git {
    public static void initiate() {

        int counter = 0;

        File git = new File("git/");
        if (!git.exists()) {
            git.mkdir();
            System.out.println("make git directory");

        } else {
            counter += 1;
        }

        File objectsDir = new File("git/objects/");
        if (!objectsDir.exists()) {
            objectsDir.mkdir();
            System.out.println("make object directory");

        } else {
            counter += 1;
        }

        File index = new File("git/index");
        try {
            if (!index.createNewFile()) {
                counter += 1;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        File HEAD = new File("git/HEAD");
        try {
            if (!HEAD.createNewFile()) {
                counter += 1;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (counter == 4) {
            System.out.println("Git Repository Already Exists");

        } else {
            System.out.println("Git Repository Created\n" + //
                    "");
        }

    }

    public static void commit(String author, String message) {
        try {
            String rootTreeHash = TreeCreation.createTreeFromIndex();
            StringBuilder commitContent = new StringBuilder("tree: " + rootTreeHash + "\n");
            BufferedReader fr = new BufferedReader(new FileReader("git/HEAD"));
            String parentCommit = fr.readLine();
            if (parentCommit != null) {
                commitContent.append("parent: " + parentCommit + "\n");
            }
            fr.close();
            commitContent.append("author: " + author + "\n");
            commitContent.append("date: " + LocalDate.now() + "\n");
            commitContent.append("message: " + message);
            String commitHash = HashAndAdd.hashContent(commitContent.toString());
            BufferedWriter bw = new BufferedWriter(new FileWriter("git/objects/" + commitHash));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("git/HEAD"));
            bw.write(commitContent.toString());
            bw2.write(commitHash);
            bw.close();
            bw2.close();
            System.out.println(commitHash);
        } catch (Exception e) {
            System.out.println("Error in commit: " + e.getMessage());
        }

    }

}