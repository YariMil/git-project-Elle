import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Git{
        public static void main(String[] args) {
            initiate();

    }
    public static void initiate(){
    

            int counter = 0;
            
            File git = new File("git/");
            if (!git.exists()) {
                git.mkdir();
                System.out.println("make git directory");
                
            }else{
                counter+=1;
            }
            

            File objectsDir = new File("git/objects/");
            if (!objectsDir.exists()) {
                objectsDir.mkdir();
                System.out.println("make object directory");

            }else{
                counter+=1;
            }

            File index = new File("git/index.txt");
            try {
                if(!index.createNewFile()){
                    counter+=1;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
            File HEAD = new File("git/HEAD.txt");
            try {
                if(!HEAD.createNewFile()){
                    counter+=1;
            }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            if(counter == 4){
                System.out.println("Git Repository Already Exists");

            }
            else{
                System.out.println("Git Repository Created\n" + //
                                        "");
            }


    }


}