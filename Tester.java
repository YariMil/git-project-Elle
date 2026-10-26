import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
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
        ArrayList<String> workingList = TreeCreation.buildWorkingList();
        for (String s : workingList) {
            System.out.println(s);
        }
        System.out.println(TreeCreation.createTreeFromIndex());
        // System.out.println(TreeCreation.buildWorkingList());
        // System.out.println(TreeCreation.createTreeFromIndex());

    }
}
