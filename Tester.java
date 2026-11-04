import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) throws IOException {

        GitWrapper.init();
        GitWrapper.add("myProgram/myProgram/jabba.txt");
        GitWrapper.add("myProgram/myProgram/hello.txt");
        GitWrapper.add("myProgram/myProgram/docs/Hello.txt");
        GitWrapper.add("myProgram/myProgram/docs/World.txt");
        GitWrapper.add("myProgram/myProgram/README.md");
        GitWrapper.add("myProgram/myProgram/World.txt");
        // HashAndAdd.addToIndex("myProgram/docs/Check.txt");
        // ArrayList<String> workingList = TreeCreation.buildWorkingList();
        // for (String s : workingList) {
        // System.out.println(s);
        // }
        // System.out.println(TreeCreation.createTreeFromIndex());
        // System.out.println(TreeCreation.buildWorkingList());
        // System.out.println(TreeCreation.createTreeFromIndex());
        GitWrapper.commit("Yari", "Test commit 1");
        // Adding a new file and staging
        System.out.println("== EDGE CASE CHECKS ==");
        try {
            // GitWrapper.add("myProgram/ThisIsWrong.txt");
            GitWrapper.add("myProgram/myProgram/docs");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("myProgram/myProgram/Oh.txt"));
            bw.write("This is amazing");
            GitWrapper.add("myProgram/myProgram/Oh.txt");
            GitWrapper.commit("Yari", "Wow!");
            bw.write(". Actually yes it is amazing.");
            GitWrapper.add("myProgram/myProgram/Oh.txt");
            GitWrapper.commit("Yari", "Wow!");
            bw.close();
            GitWrapper.checkout("12f5a9d66d601191351503d542a5ebacc30b0b45");
            GitWrapper.checkout("dadc3173b126cbc610d6d70179c08844e2d405b9");
            GitWrapper.checkout("12f5a9d66d601191351503d542a5ebacc30b0b45");
            GitWrapper.checkout("dadc3173b126cbc610d6d70179c08844e2d405b9");
        } catch (Exception e) {

        }
    }
}
