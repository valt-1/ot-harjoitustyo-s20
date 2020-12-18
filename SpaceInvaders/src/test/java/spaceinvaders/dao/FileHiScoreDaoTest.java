package spaceinvaders.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

public class FileHiScoreDaoTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    File hiScoreFile;
    HiScoreDao dao;

    @Before
    public void setUp() throws IOException {
        hiScoreFile = temporaryFolder.newFile("testfile.txt");

        try (FileWriter file = new FileWriter(hiScoreFile)) {
            file.write("130");
        }

        dao = new FileHiScoreDao(hiScoreFile.getAbsolutePath());
    }

    @Test
    public void hiScoreReadCorrectly() {
        assertEquals(130, dao.getHiScore());
    }

    @Test
    public void hiScoreSavedCorrectly() throws Exception {
        dao.saveScore(420);
        Scanner reader = new Scanner(hiScoreFile);
        int hiScore = 0;
        while (reader.hasNextLine()) {
            hiScore = Integer.valueOf(reader.nextLine());
        }
        assertEquals(dao.getHiScore(), 420);
        assertEquals(420, hiScore);
    }

    @Test
    public void hiScoreZeroIfNoFile() throws IOException {
        String file = hiScoreFile.getAbsolutePath();
        hiScoreFile.delete();
        dao = new FileHiScoreDao(file);
        assertEquals(0, dao.getHiScore());
    }

    @After
    public void tearDown() {
        hiScoreFile.delete();
    }
}
