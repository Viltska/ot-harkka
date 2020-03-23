/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ville Manninen
 */
public class KassapaateTest {

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void luodaanOikeinTest() {
        Kassapaate kassapaate = new Kassapaate();
        int rahaa = kassapaate.kassassaRahaa();
        int myyty = kassapaate.maukkaitaLounaitaMyyty() + kassapaate.edullisiaLounaitaMyyty();

        assertEquals(1000, rahaa / 100);
        assertEquals(0, myyty);
    }

    @Test
    public void kateisOstoTest() {
        Kassapaate kassapaate = new Kassapaate();

        // Edullisesti
        kassapaate.syoEdullisesti(1000);
        int vaihtoRaha = kassapaate.syoEdullisesti(230);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(230, vaihtoRaha);

        // Maukkaasti
        kassapaate.syoMaukkaasti(1000);
        int vaihtoRaha2 = kassapaate.syoMaukkaasti(390);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100640, kassapaate.kassassaRahaa());
        assertEquals(390, vaihtoRaha2);

    }

    @Test
    public void korttiOstoTest() {
        Kassapaate kassapaate = new Kassapaate();

        Maksukortti maksukortti = new Maksukortti(240);
        assertEquals(true, kassapaate.syoEdullisesti(maksukortti));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, maksukortti.saldo());

        maksukortti.lataaRahaa(100);
        assertEquals(false, kassapaate.syoEdullisesti(maksukortti));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100, maksukortti.saldo());

        Maksukortti maksukortti2 = new Maksukortti(400);
        assertEquals(true, kassapaate.syoMaukkaasti(maksukortti2));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0, maksukortti2.saldo());

        maksukortti2.lataaRahaa(100);
        assertEquals(false, kassapaate.syoMaukkaasti(maksukortti2));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100, maksukortti2.saldo());

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void lataaRahaaKortilleTest() {
        Kassapaate kassapaate = new Kassapaate();
        Maksukortti maksukortti = new Maksukortti(0);

        kassapaate.lataaRahaaKortille(maksukortti, 100);
        kassapaate.lataaRahaaKortille(maksukortti, -10);

        assertEquals(100, maksukortti.saldo());

    }
}
