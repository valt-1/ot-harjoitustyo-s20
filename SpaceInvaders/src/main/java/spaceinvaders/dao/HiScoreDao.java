package spaceinvaders.dao;

public interface HiScoreDao {
    
    int getHiScore();
    void saveScore(int score) throws Exception;
    
}
