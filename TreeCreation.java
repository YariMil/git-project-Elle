import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TreeCreation {
    public static void main(String[] args) throws IOException { 
        System.out.println(createTreeFromIndex());
    }

    public static String createTreeFromIndex() throws IOException {
        ArrayList<String> workingList = buildWorkingList();

        String dir = findDeepestDirectory(workingList);
        while(!dir.equals("")) { 
            String hash = createTree(workingList, dir);
            workingList = collapse(workingList, dir, hash);
            dir = findDeepestDirectory(workingList);
        }

        return (createTree(workingList, dir));
     }



    public static String findDeepestDirectory(ArrayList<String> workingList) {
        String deepest = "";
        int maxDepth = -1;
        for(String line : workingList) { 
            String path = line.split(" ", 3)[2];
            String folder = getParentDir(path);

            if (!folder.equals("")) { 
                int depth = 0;
                for (int i = 0; i < folder.length(); i++) {
                    if (folder.charAt(i) == '/') { 
                        depth++;
                    }
                }
                if (depth > maxDepth) { 
                    maxDepth = depth;
                    deepest = folder;
                }
            }
        }
        return deepest;
     }



    public static void addSorted(ArrayList<String> list, String entry) {
        String path = entry.split(" ", 3)[2];
        int index = list.size();
            for (int i = 0; i < list.size(); i++) {
                String currentPath = list.get(i).split(" ", 3)[2];
                if (path.compareTo(currentPath) < 0) { 
                    index = i;
                    break;
                }
            }
        list.add(index, entry);
    }

    public static ArrayList<String> buildWorkingList() throws IOException { 
        ArrayList<String> workingList = new ArrayList<String>();

        BufferedReader br = new BufferedReader(new FileReader("git/index.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            String listEntry = "blob " + line;
            if (!workingList.contains(listEntry)) { 
                addSorted(workingList, listEntry);
            }
        }
        br.close();

        return workingList;
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

    public static String createTree(ArrayList<String> workingList, String dirPath) throws IOException {
        ArrayList<String> kids = new ArrayList<String>();

        for (String line : workingList) {
            String[] listParts = line.split(" ", 3);
            String listPath = listParts[2];
            if (getParentDir(listPath).equals(dirPath)) { 
                String fileName = listPath.substring(listPath.lastIndexOf('/') + 1);
                kids.add(listParts[0] + " " + listParts[1] + " " + fileName);
            }
        }

        StringBuilder content = new StringBuilder();
        for (String kid : kids) { 
            content.append(kid).append("\n");
        }

        String contentString = content.toString();
        String hash = HashAndAdd.hashContent(contentString);

        FileWriter writeToTree = new FileWriter("git/objects/" + hash + ".txt");
        writeToTree.write(contentString);
        writeToTree.close();

        return hash;
    }

    public static ArrayList<String> collapse(ArrayList<String> workingList, String directory, String hash) { //collapses into tree
        ArrayList<String> newWorkingList = new ArrayList<String>();

        for(String line : workingList) { 
            String path = line.split(" ", 3)[2];
            if (!getParentDir(path).equals(directory)) { 
                newWorkingList.add(line);
            }
        }

        addSorted(newWorkingList, "tree " + hash + " " + directory);
        return newWorkingList;
    }

}
