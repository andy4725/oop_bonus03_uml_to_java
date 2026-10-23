# Tanulmányi modell

Egyszerű Java-alkalmazás egy egyetemi tanulmányi rendszer alapfogalmainak modellezésére. Oktatókat, hallgatókat és kurzusokat kezel, beiratkozásokat rögzít, és jegyekből statisztikákat számol.

## Követelmények

- Java 17 vagy újabb

## Futtatás

A projekt nem használ build rendszert. A forrásokat a projekt gyökérkönyvtárából fordítsd és futtasd:

```sh
mkdir -p bin
javac -d bin src/*.java
java -cp bin App
```

Az `App` osztály egy rövid példát futtat: létrehoz egy oktatót, kurzust, félévet és hallgatót, beíratja a hallgatót, rögzít egy jegyet, majd kiírja a hallgató súlyozott átlagát, megszerzett kreditjeit és a kurzus átmenési arányát.

## Tesztek futtatása

A JUnit Platform Console Standalone jar a `lib` könyvtárban található. A tesztek fordítása és futtatása:

```sh
mkdir -p bin
javac -d bin src/*.java
javac -cp "bin:lib/junit-platform-console-standalone-6.1.3.jar" -d bin test/*.java
java -jar lib/junit-platform-console-standalone-6.1.3.jar execute --class-path bin --scan-class-path
```

Windows alatt a `javac -cp` parancsban az osztályútvonal-elválasztó kettőspont (`:`) helyett pontosvesszőt (`;`) használj.

## Modell áttekintése

- `Szemely` az `Oktato` és a `Hallgato` közös absztrakt ősosztálya.
- `Oktato` a hozzá tartozó kurzusokat tartja nyilván.
- `Kurzus` tárolja a kódot, nevet, kreditet, maximális létszámot és az oktatót. A kurzus kezeli a beiratkozásokat, és biztosítja, hogy egy hallgató egy félévben ne iratkozhasson be ugyanarra a kurzusra, illetve a férőhely ne lépje túl a megadott maximumot.
- `Beiratkozas` összekapcsol egy hallgatót, kurzust és félévet; ehhez rögzíthető jegy.
- `Felev` évből és félévszámból álló érték, ahol a félévszám 1 vagy 2 lehet.
- `Jegy` 1–10 közötti értéket fogad el; az 5-ös vagy jobb jegy sikeres.

A hallgató súlyozott átlaga a jegyek kreditértékkel súlyozott átlaga. Megszerzett kredit csak sikeres jegyű beiratkozás után jár. A kurzus átmenési aránya csak az osztályzott beiratkozásokat veszi figyelembe.

## Projektstruktúra

```text
src/    Java-forrásfájlok
test/   JUnit 5 tesztek
lib/    JUnit Platform Console Standalone
bin/    Fordítási kimenet
```
