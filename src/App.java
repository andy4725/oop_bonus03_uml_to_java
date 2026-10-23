public class App {
    public static void main(String[] args) {
        Oktato oktato = new Oktato("O1", "Kovács Anna", "anna@example.com");
        Kurzus kurzus = new Kurzus("JAVA101", "Java programozás", 5, 30, oktato);
        Felev felev = new Felev(2026, 1);

        Hallgato hallgato = new Hallgato("H1", "Nagy Béla", "bela@example.com");
        Beiratkozas beiratkozas = kurzus.beiratkozik(hallgato, felev);
        beiratkozas.jegyetRogzit(new Jegy(5));

        System.out.println(hallgato);
        System.out.println("Súlyozott átlag: " + hallgato.getSulyozottAtlag());
        System.out.println("Megszerzett kreditek: " + hallgato.getOsszesMegszerzettKredit());
        System.out.println("Kurzus átmenési aránya: " + kurzus.getAtmenesiArany());
    }
}