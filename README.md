# Ohjelmistotekniikka Kevät 2020 - Minesweeper

Corona teemaa käyttävä miinaharavapeli joka toimii omassa ikkunassaan, ohjelma käyttää Java 11 versiota ja pohjautuu JavaFX kirjastoon. Ohjelman pitäisi toimia Windows, Mac sekä Linux koneilla.


## Dokumentaatio

[Käyttöohje](https://github.com/Viltska/ot-minesweeper/blob/master/dokumentit/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Viltska/ot-harkka/blob/master/dokumentit/maarittely.md)

[Testausdokumentti](https://github.com/Viltska/ot-minesweeper/blob/master/dokumentit/testaus.md)

[Työajankirjanpito](https://github.com/Viltska/ot-minesweeper/blob/master/dokumentit/tyoaika.md)

## Releaset

[Viikko 6](https://github.com/Viltska/ot-minesweeper/releases/tag/Release)

[Loppupalautus]


## Komentorivitoiminnot
Ohjelman hakemistossa jossa sijaitsee POM.xml  voidaan ohjelmaa suorittaa ja testata komentoriviltä.

- Raportit ja tiedostot ilmestyvät projektin sisälle kansioon nimeltään /target, ne voidaan poistaa komennolla <code> mvn clean </code>

### Lähdekoodiohjelman suorittaminen

Kun ohjelma halutaan suorittaa ilman jar tiedostoa

- Ohjelman suorittaminen tapahtuu komennolla:
<code> mvn compile exec:java -Dexec.mainClass=fi.villemanninen.App </code>

### JUnit 

- Ohjelman testaaminen komentoriviltä:
 <code> mvn test </code>
 
### JavaDoc
 
 - Ohjelman JavaDoc luominen:
 <code> mvn javadoc:javadoc </code>
 
### Pakkaaminen

- Ohjelman pakkaaminen jar tiedostoksi:
<code> mvn package </code>

### Jacoco

- Jacoco testiraportti: 
<code> mvn jacoco:report </code>

### Checkstyle

- Checkstyle raportti:
<code> mvn checkstyle:checkstyle </code>



## Laskarit

### Viikko 1

[gitlog.txt](https://github.com/Viltska/ot-harkka/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/Viltska/ot-harkka/blob/master/laskarit/viikko1/komentorivi.txt)

### Viikko 2
[Maksukortti](https://github.com/Viltska/ot-harkka/tree/master/laskarit/viikko2/Maksukortti)

[Testit](https://github.com/Viltska/ot-harkka/tree/master/laskarit/viikko2/Maksukortti/src/test/java)

[Unicafe testikansio](https://github.com/Viltska/ot-minesweeper/tree/master/laskarit/viikko2/Unicafe/src/test/java/com/mycompany/unicafe)

[Unicafe testikattavuus](https://github.com/Viltska/ot-minesweeper/blob/master/laskarit/viikko2/kassapaate.png)
