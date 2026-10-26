### git-project-Elle
direct.java
everything worked good

## GP-4 Done by Yari

# Bugs found and fixed
1. Index does store relative paths with the same hashes, but rerunning the code multiple times simply adds more lines to the code rather than rewriting the index. Similarly, changing a file and then restaging causes it to be added to the index as a new line rather than overriding the old line. Finally, the index always had an uncecessary newline at the end of it. This was fixed in previous commits on the peer-review branch. 
2. When creating a new tree object, a new line is unnecessarly added to the end. This was fixed in a previous commit on the peer-review branch. 
All known bugs were submitted as part of the peer verification assignment.