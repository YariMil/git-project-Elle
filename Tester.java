import java.io.IOException;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) throws IOException { 

        Git.initiate();
        HashAndAdd.addToIndex("jabba.txt");

        ArrayList<String> workingList = new ArrayList<>();
        workingList.add("blob 0a4d55a8d778e5022fab701977c5d840bbc486d0 myProgram/docs/Hello.txt");
        workingList.add("blob 483b5e082cf5502b303ba3dd4f3469a49fd3421f myProgram/docs/World.txt");
        workingList.add("blob 4377a91cdfd44db9a9bbf056849c7da0fc6cc7be myProgram/README.md");

        System.out.println(HashAndAdd.createTree(workingList, "myProgram"));
        System.out.println(HashAndAdd.createTree(workingList, "myProgram/docs"));
        System.out.println(HashAndAdd.createTree(workingList, "myProgram/docs"));
        

    }
}
