package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      //creating JavaGrepLambdaImp instead of JavaGrepImp
      //JavaGrepLambdaImp inherits all methods except two override methods
      JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
      javaGrepLambdaImp.setRegex(args[0]);
      javaGrepLambdaImp.setRootPath(args[1]);
      javaGrepLambdaImp.setOutFile(args[2]);

      try {
        //calling parent method
        //but it will override method (in this class)
        javaGrepLambdaImp.process();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<String> readLines(File inputFile) throws IOException {
    List<String> lines = new ArrayList<String>();
    lines = Files.lines(inputFile.toPath()).collect(Collectors.toList());
    return lines;
  }

  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<File> listFiles(String rootDir) throws IOException {
    try {
      List<File> filePaths = new ArrayList<File>();
      filePaths = Files.walk(Paths.get(getRootPath())).map(Path::toFile)
          .collect(Collectors.toList());
      return filePaths;
    } catch (IOException ex) {
      throw new IOException("Could not open file");
    }
  }
}
