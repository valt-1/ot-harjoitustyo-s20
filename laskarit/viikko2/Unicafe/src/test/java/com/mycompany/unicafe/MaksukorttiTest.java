package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    int ALKUSALDO = 1000;

    @Before
    public void setUp() {
        kortti = new Maksukortti(ALKUSALDO);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(ALKUSALDO, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2500);
        assertEquals(ALKUSALDO+2500, kortti.saldo());
    }
    
    @Test
    public void otaRahaaVahentaaOikeinJosRahaaTarpeeksi() {
        kortti.otaRahaa(ALKUSALDO/2);
        int euroa = ALKUSALDO/2 / 100;
        int senttia = ALKUSALDO/2 % 100;
        assertEquals("saldo: " + euroa + "." + senttia, kortti.toString());
    }
    
    @Test
    public void otaRahaaEiMuutaSaldoaJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(ALKUSALDO+100);
        assertEquals(ALKUSALDO, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueJosRahaaTarpeeksi() {
        assertTrue(kortti.otaRahaa(ALKUSALDO/2));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseJosRahaaEiTarpeeksi() {
        assertFalse(kortti.otaRahaa(ALKUSALDO+100));
    }
}
