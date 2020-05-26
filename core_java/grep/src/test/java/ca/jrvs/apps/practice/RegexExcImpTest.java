package ca.jrvs.apps.practice;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexExcImpTest {

  @Test
  public void testmatchJpeg() {
    RegexExcImp rx = new RegexExcImp();
    assertEquals(rx.matchJpeg("me.jpcg"),false);
  }

  @Test
  public void testmatchIp() {
    RegexExcImp rx = new RegexExcImp();
    assertEquals(rx.matchIp("999.999.999.999"),true);
  }

  @Test
  public void testisEmptyLine() {
    RegexExcImp rx = new RegexExcImp();
    assertEquals(rx.isEmptyLine(" "),true);
  }
}