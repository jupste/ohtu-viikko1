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
    static int testSize=10;
    @Before
    public void setUp() {
        varasto = new Varasto(testSize);
    }
    @Test
    public void eiOtaVirheellistaTilavuutta(){
        varasto= new Varasto(-100);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
        varasto= new Varasto(-100, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriOsaaLaittaaAlkusaldon(){
        varasto= new Varasto(testSize, testSize);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        varasto= new Varasto(testSize, -testSize);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        varasto= new Varasto(testSize, 2*testSize);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void eiVoiLisataNegatiivista(){
        varasto.lisaaVarastoon(-100);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void ylimaarainenLisaysYli(){
        varasto.lisaaVarastoon(10000000);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void eiVoiOttaaNegatiivista(){
        varasto.otaVarastosta(-100);
        assertEquals(0,varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void eiVoiOttaaLiikaa(){
        varasto.lisaaVarastoon(10);
        double otos= varasto.otaVarastosta(20000);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
        assertEquals(10, otos, vertailuTarkkuus);
    }
    @Test
    public void toStringToimii(){
        assertEquals(varasto.toString(),"saldo = 0.0, vielä tilaa 10.0");
    }
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}