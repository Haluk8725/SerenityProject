package eu9.bookit;

import org.junit.jupiter.api.Test;
import utilities.ConfigReader;

import static org.jsoup.nodes.Entities.EscapeMode.base;

public class BookitEnvTest {
@Test
    public void test1(){
    System.out.println(ConfigReader.getProperty("base.url"));
    System.out.println(ConfigReader.getProperty("teacher_email"));
    System.out.println(ConfigReader.getProperty("teacher_password"));
}
}
