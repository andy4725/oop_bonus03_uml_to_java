import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Hallgato extends Szemely {
    private final List<Beiratkozas> beiratkozasok = new ArrayList<>();

    public Hallgato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    void hozzaad(Beiratkozas beiratkozas) {
        beiratkozasok.add(beiratkozas);
    }

    public List<Beiratkozas> gBeiratkozasok() {
        return Collections.unmodifiableList(beiratkozasok);
    }

    public double getSulyozottAtlag() {
        double sulyozottOsszeg = 0;
        int kreditek = 0;

        for(Beiratkozas beiratkozas: beiratkozasok) {
            if(beiratkozas.getJegy().isPresent()) {
                int kredit = beiratkozas.getKurzus().getKredit();
                sulyozottOsszeg += beiratkozas.getJegy().get().ertek() * kredit;
                kreditek += kredit;
            }
        }

        return kreditek == 0 ? 0.0 : sulyozottOsszeg / kreditek;
    }

    public int getOsszesMegszerzettKredit() {
        return beiratkozasok.stream()
                .filter(b -> b.getJegy().map(Jegy::sikeres).orElse(false))
                .mapToInt(b -> b.getKurzus().getKredit())
                .sum();
    }

    @Override
    public String toString() {
        return super.toString() + ", beiratkozasok=" + beiratkozasok.size();
    }
}