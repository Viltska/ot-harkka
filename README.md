# Ohjelmistotekniikka Kevät 2020 - Minesweeper
**Ville Manninen**

[Työajankirjanpito](https://github.com/Viltska/ot-harkka/blob/master/dokumentit/tyoaika.md)

## Harjoitustyö

[Määrittelydokumentti](https://github.com/Viltska/ot-harkka/blob/master/dokumentit/maarittely.md)

[Minesweeper](https://github.com/Viltska/ot-minesweeper/tree/master/minesweeper)

### Suorittaminen ja testaaminen
Ohjelman hakemistossa jossa sijaitsee POM.xml  voidaan ohjelmaa suorittaa ja testata komentoriviltä.

- Ohjelman suorittaminen tapahtuu komennolla:
<code> mvn compile exec:java -Dexec.mainClass=fi.villemanninen.App </code>
- Ohjelman testaaminen komentoriviltä:
 <code> mvn test </code>
- Jacoco testiraportti: 
<code> mvn jacoco:report </code>
- Checkstyle raportti:
<code> mvn checkstyle:checkstyle: </code>
- Raportit ilmestyvät kansioon /target, ne voidaan poistaa komennolla <code> mvn clean </code>

## Tehtavat

### Viikko 1

[gitlog.txt](https://github.com/Viltska/ot-harkka/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/Viltska/ot-harkka/blob/master/laskarit/viikko1/komentorivi.txt)

### Viikko 2
[Maksukortti](https://github.com/Viltska/ot-harkka/tree/master/laskarit/viikko2/Maksukortti)

[Testit](https://github.com/Viltska/ot-harkka/tree/master/laskarit/viikko2/Maksukortti/src/test/java)

[Unicafe testikansio](https://github.com/Viltska/ot-minesweeper/tree/master/laskarit/viikko2/Unicafe/src/test/java/com/mycompany/unicafe)

[Unicafe testikattavuus](https://github.com/Viltska/ot-minesweeper/blob/master/laskarit/viikko2/kassapaate.png)
