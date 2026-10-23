import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TanulmanyiModellTest {
    private final Felev oszi = new Felev(2026, 1);

    private Oktato oktato() {
        return new Oktato("O1", "Oktató", "oktato@example.hu");
    }

    private Hallgato hallgato(String id) {
        return new Hallgato(id, "Hallgató " + id, id + "@example.hu");
    }

    private Kurzus kurzus(Oktato oktato, String kod, int kredit, int ferohely) {
        return new Kurzus(kod, kod, kredit, ferohely, oktato);
    }

    @Test
    void teltKurzusraNemLehetBeiratkozni() {
        Kurzus kurzus = kurzus(oktato(), "JAVA", 5, 1);
        kurzus.beiratkozik(hallgato("H1"), oszi);

        assertThrows(IllegalStateException.class,
                () -> kurzus.beiratkozik(hallgato("H2"), oszi));
    }

    @Test
    void ugyanarraAKurzusraEgyFelevbenCsakEgyszerLehetBeiratkozni() {
        Kurzus kurzus = kurzus(oktato(), "JAVA", 5, 3);
        Hallgato hallgato = hallgato("H1");
        kurzus.beiratkozik(hallgato, oszi);

        assertThrows(IllegalStateException.class,
                () -> kurzus.beiratkozik(hallgato, oszi));
    }

    @Test
    void masFelevbenIsmetBeLehetIratkozni() {
        Kurzus kurzus = kurzus(oktato(), "JAVA", 5, 3);
        Hallgato hallgato = hallgato("H1");
        kurzus.beiratkozik(hallgato, oszi);

        assertDoesNotThrow(() -> kurzus.beiratkozik(hallgato, new Felev(2027, 1)));
    }

    @Test
    void ervenytelenJegyetElutasit() {
        assertThrows(IllegalArgumentException.class, () -> new Jegy(0));
        assertThrows(IllegalArgumentException.class, () -> new Jegy(11));
    }

    @Test
    void sulyozottAtlagKredittelSulyoz() {
        Oktato oktato = oktato();
        Hallgato hallgato = hallgato("H1");
        Beiratkozas elso = kurzus(oktato, "A", 3, 10).beiratkozik(hallgato, oszi);
        Beiratkozas masodik = kurzus(oktato, "B", 6, 10).beiratkozik(hallgato, oszi);
        elso.jegyetRogzit(new Jegy(10));
        masodik.jegyetRogzit(new Jegy(5));

        assertEquals(20.0 / 3.0, hallgato.getSulyozottAtlag(), 0.0001);
    }

    @Test
    void csakElegsegesJegyAdKreditet() {
        Oktato oktato = oktato();
        Hallgato hallgato = hallgato("H1");
        Beiratkozas sikeres = kurzus(oktato, "A", 4, 10).beiratkozik(hallgato, oszi);
        Beiratkozas sikertelen = kurzus(oktato, "B", 3, 10).beiratkozik(hallgato, oszi);
        sikeres.jegyetRogzit(new Jegy(5));
        sikertelen.jegyetRogzit(new Jegy(4));

        assertEquals(4, hallgato.getOsszesMegszerzettKredit());
    }

    @Test
    void atmenesiAranyCsakAzOsztalyzottBeiratkozasokatVesziFigyelembe() {
        Kurzus kurzus = kurzus(oktato(), "A", 4, 10);
        Beiratkozas sikeres = kurzus.beiratkozik(hallgato("H1"), oszi);
        Beiratkozas sikertelen = kurzus.beiratkozik(hallgato("H2"), oszi);
        kurzus.beiratkozik(hallgato("H3"), oszi);
        sikeres.jegyetRogzit(new Jegy(5));
        sikertelen.jegyetRogzit(new Jegy(3));

        assertEquals(0.5, kurzus.getAtmenesiArany(), 0.0001);
    }

    @Test
    void szemelyekAzonositoAlapjanEgyenloek() {
        Hallgato egyik = new Hallgato("H1", "Első név", "a@example.hu");
        Hallgato masik = new Hallgato("H1", "Másik név", "b@example.hu");

        assertEquals(egyik, masik);
        assertEquals(egyik.hashCode(), masik.hashCode());
    }
}