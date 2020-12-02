package spaceinvaders.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHiScoreDao implements HiScoreDao {
    
    private int hiScore;
    private String file;

    public FileHiScoreDao(String file) throws IOException {
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                hiScore = Integer.parseInt(reader.nextLine());
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
            hiScore = 0;
        }
    }

    @Override
    public int getHiScore() {
        return hiScore;
    }

    @Override
    public void saveScore(int score) throws IOException {
        try (FileWriter writer = new FileWriter(new File(file))) {
            hiScore = score;
            writer.write(String.valueOf(hiScore));
        }
    }
    
}
