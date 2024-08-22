package test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class AutoITTest {

    @Test
    public void testRunAutoItScript() {
        String autoitPath = "C:\\Users\\anpud\\Downloads\\autoit-v3.3.16.1\\install\\AutoIt3.exe";
        String scriptPath = "C:\\Users\\anpud\\Desktop\\test.au3";

        ProcessBuilder processBuilder = new ProcessBuilder(autoitPath, scriptPath);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            Assertions.assertEquals(0, exitCode, "The script did not execute successfully.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
