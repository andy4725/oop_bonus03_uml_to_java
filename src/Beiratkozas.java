import java.util.Objects;
import java.util.Optional;

public final class Beiratkozas {
    private final Hallgato hallgato;
    private final Kurzus kurzus;
    private final Felev felev;
    private Jegy jegy;

    Beiratkozas(Hallgato hallgato, Kurzus kurzus, Felev felev) {
        this.hallgato = Objects.requireNonNull(hallgato);
        this.kurzus = Objects.requireNonNull(kurzus);
        this.felev = Objects.requireNonNull(felev);
    }

    public Hallgato getHallgato() { return hallgato; }
    public Kurzus getKurzus() { return kurzus; }
    public Felev getFelev() { return felev; }
    public Optional<Jegy> getJegy() { return Optional.ofNullable(jegy); }

    public void jegyetRogzit(Jegy jegy) {
        this.jegy = Objects.requireNonNull(jegy);
    }

    @Override
    public String toString() {
        return "Beiratkozas{hallgato=" + hallgato.getAzonosito()
                + ", kurzus=" + kurzus.getKod() + ", felev=" + felev
                + ", jegy=" + getJegy().map(Jegy::ertek).orElse(null) + "}";
    }
}