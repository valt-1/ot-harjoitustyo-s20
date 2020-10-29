package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
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
    public void syoEdullisestiLisaaMyytyjaJosKateismaksuRiittava() {
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
    public void syoMaukkaastiLisaaMyytyjaJosKateismaksuRiittava() {
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
    
    // edullisten lounaiden korttiosto
    
    @Test
    public void syoEdullisestiPalauttaaTrueJosKortinSaldoRiittaa() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoEdullisestiLisaaMyytyjaJosKortinSaldoRiittaa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiLisaaMyytyjaJosKortinSaldoEiRiita() {
        for (int i = 0; i < 5; i++) {
            kassa.syoEdullisesti(kortti);
        }
        assertEquals(4, kassa.edullisiaLounaitaMyyty());
        
    }
    
    @Test
    public void syoEdullisestiPalauttaaFalseJosKortinSaldoEiRiita() {
        for (int i = 0; i < 4; i++) {
            kassa.syoEdullisesti(kortti);
        }
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoEdullisestiKortillaEiMuutaKassanRahamaaraa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    // maukkaiden lounaiden korttiosto
    
    @Test
    public void syoMaukkaastiPalauttaaTrueJosKortinSaldoRiittaa() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoMaukkaastiLisaaMyytyjaJosKortinSaldoRiittaa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiLisaaMyytyjaJosKortinSaldoEiRiita() {
        for (int i = 0; i < 3; i++) {
            kassa.syoMaukkaasti(kortti);
        }
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());

    }
    
    @Test
    public void syoMaukkaastiPalauttaaFalseJosKortinSaldoEiRiita() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoMaukkaastiKortillaEiMuutaKassanRahamaaraa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    // kortin lataaminen
    
    @Test
    public void lataaRahaaKortilleKasvattaaKassanRahamaaraaOikein() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(100000 + 1000, kassa.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKortilleEiLataaNegatiivistaSummaa() {
        kassa.lataaRahaaKortille(kortti, -1000);
        assertEquals(1000, kortti.saldo());
    }
}
