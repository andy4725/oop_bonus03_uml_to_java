public record Jegy(int ertek) {
    public Jegy {
        if(ertek < 1 || ertek > 10) throw new IllegalArgumentException("A jegy 1 es 10 kozott kell legyen.");
    }

    public boolean sikeres() {
        return ertek >= 5;
    }
}
