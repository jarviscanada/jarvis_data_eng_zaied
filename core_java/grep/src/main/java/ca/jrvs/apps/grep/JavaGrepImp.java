package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }
    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }
  }

  /**
   * Top level search workflow
   *
   * @throws IOException
   */
  @Override
  public void process() throws IOException {
    List<String> fileNames = listFiles(rootPath);
    List<String> containedLines = new ArrayList<String>();
    for (int i = 0; i < fileNames.size(); i++) {
      File file = new File(fileNames.get(i));
      List<String> lines = readLines(file);
      for (int line_idx = 0; line_idx < lines.size(); line_idx++) {
        if (containsPattern(lines.get(line_idx))) {
          containedLines.add(lines.get(line_idx));
        }
      }
    }
    writeToFile(containedLines);
  }

  public List<String> listfilesHelper(File[] fileList) {
    List<String> fileNames = new ArrayList<String>();
    for (File file : fileList) {
      if (file.isDirectory()) {
        fileNames = listfilesHelper(file.listFiles());
      } else {
        fileNames.add(file.getAbsolutePath());
      }
    }
    return fileNames;
  }

  /**
   * Read a file and return all the lines
   * <p>
   * Explain FileReader, BufferedReader, and character encoding
   *
   * @param inputFile to be read
   * @return lines
   * @throws IllegalArgumentException if a given inputFile is not a file
   */
  public List<String> readLines(File inputFile)
      throws IOException {
    try {
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      List<String> lines = new ArrayList<String>();
      while (true) {
        String line = br.readLine();
        if (line == null) {
          break;
        }
        lines.add(line);

      }
      br.close();
      return lines;
    } catch (IOException io) {
      throw new IOException("Could not open file");
    }
  }

  /**
   * Traverse a given directory and return all files
   *
   * @param rootDir input directory
   * @return files under the rootDir
   */
  @Override
  public List<String> listFiles(String rootDir) {
    //System.out.println(rootDir);
    File[] files = new File(rootDir).listFiles();
    List<String> fileNames = new ArrayList<String>();
    fileNames = listfilesHelper(files);
    return fileNames;
  }

  /**
   * check if a line contains the regex pattern (passed by user)
   *
   * @param line input string
   * @return true if there is a match
   */
  @Override
  public boolean containsPattern(String line) {
    Pattern pattern = Pattern.compile(getRegex());
    Matcher matcher = pattern.matcher(line);
    return matcher.find();
  }

  /**
   * Write Lines to a file
   * <p>
   * Explore: FileOutputStream, OutputStreamWriter and BufferedWriter
   *
   * @param lines
   * @throws IOException if write failed
   * @parma lines matched line
   */
  @Override
  public void writeToFile(List<String> lines) throws IOException {
    String outputFileDir = getOutFile();
    BufferedWriter bw = null;
    try {
      File outputFile = new File(outputFileDir);
      if (!outputFile.exists()) {
        outputFile.createNewFile();
      }
      FileWriter fw = new FileWriter(outputFile);
      bw = new BufferedWriter(fw);
      for (String line : lines) {
        bw.append(line);
      }
    } catch (IOException ioe) {
      throw new IOException("Could not open file");
    } finally {
      try {
        if (bw != null) {
          bw.close();
        }
      } catch (IOException io) {
        throw new IOException("Could not write in file");
      }
    }
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return this.regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return this.outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
