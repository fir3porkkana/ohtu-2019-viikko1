package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;

    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void viallinenVarastoOnKelvoton() {
        varasto = new Varasto(-30000);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldollinenJaViallinenVarastoOnKelvoton() {
        varasto = new Varasto(-30000, 3);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkusaldoAsettuuOikein() {
        varasto = new Varasto(30, 10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylivuotavaAlkusaldoAsettuuOikein() {
        varasto = new Varasto(30, 3000);
        System.out.println("saldo testissä :" + varasto.getSaldo());
        assertEquals(30, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoAsettuuOikein() {
        varasto = new Varasto(30, -3000);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-3);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylivuotavaLisaysMaksimoiSaldon() {
        varasto.lisaaVarastoon(varasto.getTilavuus() + 1);

        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisaaTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenOttaminenPalauttaaNollan() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-3);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ylivuotavaOttaminenTyhjentaa() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(8000);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimiiOikein() {
        varasto.lisaaVarastoon(10);
        String toString = "saldo = 10.0, vielä tilaa 0.0";

        assertEquals(toString, varasto.toString());
    }

}