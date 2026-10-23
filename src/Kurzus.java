import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;

public final class Kurzus {
    private final String kod;
    private final String nev;
    private final int kredit;
    private final int maximalisLetszam;
    private final Oktato oktato;
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Kurzus(String kod, String nev, int kredit, int maximalisLetszam, Oktato oktato) {
        this.kod = nemUres(kod, "kod");
        this.nev = nemUres(nev, "nev");
        if (kredit <= 0) throw new IllegalArgumentException("A kredit legyen pozitív");
        if (maximalisLetszam <= 0) {
            throw new IllegalArgumentException("A maximális létszám legyen pozitív");
        }
        this.kredit = kredit;
        this.maximalisLetszam = maximalisLetszam;
        this.oktato = Objects.requireNonNull(oktato, "oktato");
        oktato.hozzaad(this);
    }

    private static String nemUres(String ertek, String mezo) {
        Objects.requireNonNull(ertek, mezo + " nem lehet null");
        if (ertek.isBlank()) throw new IllegalArgumentException(mezo + " nem lehet üres");
        return ertek.trim();
    }

    public String getKod() { return kod; }
    public String getNev() { return nev; }
    public int getKredit() { return kredit; }
    public int getMaximalisLetszam() { return maximalisLetszam; }
    public Oktato getOktato() { return oktato; }

    public List<Beiratkozas> getBeiratkozasok() {
        return Collections.unmodifiableList(beiratkozasok);
    }

    /** A kurzus kezeli a létszám- és ismételt beiratkozás-ellenőrzést. */
    public Beiratkozas beiratkozik(Hallgato hallgato, Felev felev) {
        Objects.requireNonNull(hallgato, "hallgato");
        Objects.requireNonNull(felev, "felev");

        boolean marBeiratkozott = beiratkozasok.stream().anyMatch(b ->
                b.getHallgato().equals(hallgato) && b.getFelev().equals(felev));
        if (marBeiratkozott) {
            throw new IllegalStateException("A hallgató erre a kurzusra már beiratkozott ebben a félévben");
        }
        if (beiratkozasok.size() >= maximalisLetszam) {
            throw new IllegalStateException("A kurzus betelt");
        }

        Beiratkozas beiratkozas = new Beiratkozas(hallgato, this, felev);
        beiratkozasok.add(beiratkozas);
        hallgato.hozzaad(beiratkozas);
        return beiratkozas;
    }

    public double getAtmenesiArany() {
        long osztalyzott = beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .count();
        if (osztalyzott == 0) return 0.0;

        long sikeres = beiratkozasok.stream()
                .filter(b -> b.getJegy().map(Jegy::sikeres).orElse(false))
                .count();
        return (double) sikeres / osztalyzott;
    }

    public List<Hallgato> getLegjobbHaromHallgato() {
        return beiratkozasok.stream()
                .filter(b -> b.getJegy().isPresent())
                .sorted(Comparator.comparingInt(
                        (Beiratkozas b) -> b.getJegy().orElseThrow().ertek()
                ).reversed())
                .limit(3)
                .map(Beiratkozas::getHallgato)
                .toList();
    }

    @Override
    public String toString() {
        return "Kurzus{kod='" + kod + "', nev='" + nev + "', kredit=" + kredit
                + ", maximalisLetszam=" + maximalisLetszam + ", oktato="
                + oktato.getAzonosito() + ", beiratkozottak=" + beiratkozasok.size() + "}";
    }
}