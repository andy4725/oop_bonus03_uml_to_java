import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Oktato extends Szemely {
    private final List<Kurzus> kurzusok = new ArrayList<>();

    public Oktato(String azonosito, String nev, String email) {
        super(azonosito, nev, email);
    }

    void hozzaad(Kurzus kurzus) {
        kurzusok.add(kurzus);
    }

    public List<Kurzus> getKurzusok() {
        return Collections.unmodifiableList(kurzusok);
    }

    public int getTanitottKreditekOsszege() {
        return kurzusok.stream().mapToInt(Kurzus::getKredit).sum();
    }

    @Override
    public String toString() {
        return super.toString() + ", kurzusok=" + kurzusok.size();
    }
}