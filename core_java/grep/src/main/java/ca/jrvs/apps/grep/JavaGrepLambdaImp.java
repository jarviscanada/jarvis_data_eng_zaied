package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    if (args.length != 3) {
      JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
      javaGrepLambdaImp.setRegex(args[0]);
      javaGrepLambdaImp.setRootPath(args[1]);
      javaGrepLambdaImp.setOutFile(args[2]);

      try {
        javaGrepLambdaImp.process();
      } catch (Exception ex) {
        javaGrepLambdaImp.logger.error(ex.getMessage(), ex);
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
