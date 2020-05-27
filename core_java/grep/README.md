 # ***Java Text Search App***
 
 * ## ***Introduction***
 We often need to search for a pattern in a text file in various tasks. It is a cumbersome job to do this manually. 
 Automating the procedure will spare us some manual labour and save our precious time. In this app,
 we aim to automate this task. Formally, this app will traverse a directory tree rooted from the root given 
 by user and will search for a pattern given by user and output the matched lines in a file. We can do 
 the same manually in grep using following command:
 ````
 grep search_string file_path
 ````
We will automate this procedure with this Java app where user will give the pattern, root path and output file path.

* ## ***USAGE***:

To execute the app we need to call the app with following arguments:

````
JavaGrep regex rootPath outFile
````

Here is one sample usage:

````
*.IllegalArgumentException.* /grep/src /tmp/grep.out 
````

The app will traverse the /grep/src and it's subdirectories and reads all the text files within.
Then it will output those lines containing the regex pattern \*.IllegalArgumentException.\*

* ## ***Methods, Classes and Interfaces***

Our app consists of following parts:

  * *`JaveGrep`*: This is the interface which our class will implement. It declares
 following variables and methods:
     * *`regex`*: user provided regex pattern string
     * *`rootPath`*: user provided root directory. The directory from which the search will start.
     * *`outFile`*: user provided output file path. The app will output the matched lines in this 
     file
     * *`listFiles`*: This method will recursively traverse the root and it's subdirectories and 
     output a list of files
     * *`readLines`*: This method will read a file line by line and output a list of string
     * *`containsPattern`*: This method will return true if the line contains the provided
     `regex` pattern.
     * *`writeTofile`*: This method will write the matched lines in provided `outFile`
     * *`process`*: This method will call the `listFiles` method to get the file list, then read those
     files using the `readLines` method and if the lines contain the regex pattern write them 
     in provided `outFile`
     * *`Main`*: This method will set the private fields utilizing the CLI arguments and call
     `process` method. This is the starting point of execution for our app
     * `getters` and `setters` for the private fields
 
 * *`JavaGrepImp`*: This is our class which implements the JavaGrep interface. It implements all
 the declared methods.
 
 * `JavaGrepLambdaImp`: This is another class which is a subclass of `JavaGrepImp` class. It
 inherited all the parent class methods and override the `listFiles` and `readLines` method to
 use `Stream API` and `lambda expression` 
      
We have used `self4j` for logging purpose and handled all the required exceptions.

* ## ***`process` method pseudocode***:
````
matchedlines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)                
````

* ## ***Performance Issues***:

There are a couple of performance issues in this app:

* *Memory Usage issue*: In `readLines` method we are reading the whole file and storing all the
text. If we encounter a large enough file, we could encounter an `outOfMemory` exception.
Even for smaller files, storing all the lines results in huge memory consumption.

* *Time consumption issue*: Timewise the app can be improved as well. We recursively traverse 
the subdirectories to list all files using `listFiles`. This might take a huge time if 
directory tree is substantially large. Except the `listFile` method, all the other methods
can be loosely termed of `O(n)` complexity where `n` means the input size. The methods are 
"looking at" every input at least once.

* *Directory depth issue*: As we are recursively traversing the directories, we are maintaining
an implicit stack. If the directory tree is large enough we might encounter a `stackOverFlow`
exception.

## ***Improvements***:

* As this is an MVP (Most Viable Product) soluion, we have implemented a solution which can be
safely called a "brute-force" implementation. We will try to write a more time optimized and
space optimized implementation.  As an example, "somehow" sorting the lines and perform a 
better search than a linear search might give us a performance boost time-wise.

* We have already used the `Stream API` to rewrite some methods. However, that won't give us
any boost in space-wise. But if we can change the interface, to use `Stream API` in rest of 
the methods appropriately, we might be able to get a performance boost. `Streams` do not load
the whole data source at once, rather loads the data need-basis, performs some processing
and then discards it. By using `Streams` and piping necessary functions we might be able to
reduce memory consumption.

* Introducing a recursive depth parameter to ensure that we do not encounter `stackOverFlow`
exception during directory traversal. 

