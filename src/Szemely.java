import java.util.Objects;

public abstract class Szemely {
    private final String azonosito;
    private String nev;
    private String email;

    protected Szemely(String azonosito, String nev, String email) {
        this.azonosito = nemUres(azonosito, "azonosito");
        this.nev = nemUres(nev, "nev");
        this.email = nemUres(email, "email");
    }

    private static String nemUres(String ertek, String mezo) {
        Objects.requireNonNull(ertek, mezo + " nem lehet null");
        if(ertek.isBlank()) {
            throw new IllegalArgumentException(mezo + " nem lehet ures.");
        }
        return ertek.trim();
    }

    public String getAzonosito() {
        return azonosito;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nemUres(nev, "nev");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = nemUres(email, "email");
    }

    @Override
    public boolean equals(Object objektum) {
        if(this == objektum) return true;
        if(objektum == null || getClass() != objektum.getClass()) return false;
        Szemely szemely = (Szemely) objektum;
        return azonosito.equals(szemely.azonosito);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getClass(), azonosito);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{azonosito='" + azonosito + "', nev='" + nev
                + "', email='" + email + "'}";
    }
}
