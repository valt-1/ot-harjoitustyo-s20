package spaceinvaders.dao;

/**
 * DAO-rajapinta piste-ennätyksen tallentamiseen ja hakemiseen.
 */
public interface HiScoreDao {
    
    /**
     * Hakee tallennetun piste-ennätyksen.
     *
     * @return      aiemmin tallennettu ennätys
     */
    int getHiScore();

    /**
     * Tallentaa piste-ennätyksen.
     *
     * @param score     tallennettava pistemäärä
     *
     * @throws Exception
     */
    void saveScore(int score) throws Exception;
    
}
