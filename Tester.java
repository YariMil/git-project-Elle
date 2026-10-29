import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) throws IOException {

        Git.initiate();
        HashAndAdd.addToIndex("jabba.txt");
        HashAndAdd.addToIndex("hello.txt");
        HashAndAdd.addToIndex("myProgram/docs/Hello.txt");
        HashAndAdd.addToIndex("myProgram/docs/World.txt");
        HashAndAdd.addToIndex("myProgram/README.md");
        HashAndAdd.addToIndex("myProgram/World.txt");
        // HashAndAdd.addToIndex("myProgram/docs/Check.txt");
        // ArrayList<String> workingList = TreeCreation.buildWorkingList();
        // for (String s : workingList) {
        //     System.out.println(s);
        // }
        // System.out.println(TreeCreation.createTreeFromIndex());
        // System.out.println(TreeCreation.buildWorkingList());
        // System.out.println(TreeCreation.createTreeFromIndex());
        Git.commit("Yari", "Test commit 1");
    // Adding a new file and staging
    try {
        BufferedWriter bw = new BufferedWriter(new FileWriter("myProgram/Oh.txt"));
        bw.write("This is amazing");
        bw.close();
        HashAndAdd.addToIndex("myProgram/Oh.txt");
        Git.commit("Yari", "This is test commit 2");
    } catch (Exception e) {

    }
    }
}
