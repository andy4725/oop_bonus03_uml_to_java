public record Felev(int ev, int szam) implements Comparable<Felev> {
    public Felev {
        if(ev < 1900) throw new IllegalArgumentException("Ervenytelen ev");
        if(szam < 1 || szam > 2) throw new IllegalArgumentException("Ervenytelen felev");
    }

    @Override 
    public int compareTo(Felev masik) {
        int evSorrend = Integer.compare(ev, masik.ev);
        return evSorrend != 0 ? evSorrend : Integer.compare(szam, masik.szam);
    }
}
