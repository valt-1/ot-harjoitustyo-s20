package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void luodunKassapaatteenRahamaaraOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void luodussaKassapaatteessaMyytyjenEdullistenMaaraOikein() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luodussaKassapaatteessaMyytyjenMaukkaidenMaaraOikein() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    // edullisten lounaiden käteisosto
    
    @Test
    public void syoEdullisestiLisaaRahaaOikeinJosKateismaksuRiittava() {
        kassa.syoEdullisesti(250);
        assertEquals(100000 + 240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaRahaaOikeinJosKateismaksuRiittava() {
        assertEquals(250-240, kassa.syoEdullisesti(250));
    }
    
    @Test
    public void syoEdullisestiKasvattaaMyytyjaJosKateismaksuRiittava() {
        kassa.syoEdullisesti(250);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiMuutaRahamaaraaJosKateismaksuEiRiita() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaKaikkiRahatJosKateismaksuEiRiita() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void syoEdullisestiEiMuutaMyytyjaJosKateismaksuEiRiita() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    // maukkaiden lounaiden käteisosto
    
    @Test
    public void syoMaukkaastiLisaaRahaaOikeinJosKateismaksuRiittava() {
        kassa.syoMaukkaasti(500);
        assertEquals(100000 + 400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaRahaaOikeinJosKateismaksuRiittava() {
        assertEquals(500 - 400, kassa.syoMaukkaasti(500));
    }

    @Test
    public void syoMaukkaastiKasvattaaMyytyjaJosKateismaksuRiittava() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiEiMuutaRahamaaraaJosKateismaksuEiRiita() {
        kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiPalauttaaKaikkiRahatJosKateismaksuEiRiita() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }

    @Test
    public void syoMaukkaastiEiMuutaMyytyjaJosKateismaksuEiRiita() {
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
}
