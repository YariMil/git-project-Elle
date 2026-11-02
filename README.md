### git-project-Elle
direct.java
everything worked good

## GP-4 Done by Yari

# Bugs found and fixed
1. Index does store relative paths with the same hashes, but rerunning the code multiple times simply adds more lines to the code rather than rewriting the index. Similarly, changing a file and then restaging causes it to be added to the index as a new line rather than overriding the old line. Finally, the index always had an uncecessary newline at the end of it. This was fixed in previous commits on the peer-review branch. 
2. When creating a new tree object, a new line is unnecessarly added to the end. This was fixed in a previous commit on the peer-review branch. 
All known bugs were submitted as part of the peer verification assignment.

# Commit(String author, String message)
Commit takes in a String representing the author and a String representing the commit message. It first creates trees from the index using createTreeFromIndex and takes the returned root tree hash. The method then opens the git/HEAD file and reads it for the parent commit. If the HEAD file is empty, the parent line is omitted. The method then writes the new commit into a StringBuilder, including the root tree hash, the current date, the author, the message, and the parent commit (if any). The StringBuilder content is written into a new file in git/objects with the hash of the StringBuilder contents as the name. That StringBuilder hash is then written into the HEAD file.