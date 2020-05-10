# Arkkitehtuurikuvaus

## Rakenne

Ohjelmalla on seuraava rakenne:

<img src="https://github.com/Viltska/ot-minesweeper/blob/master/dokumentit/kaaviot/rakenne.jpg" width="200">

Pakkaus fi.villemannienn sisältää App luokan joka sisältää ohjelman main metodin, joka käynnisttää Gui luokan JavaFX Applikaation.

## Käyttöliittymä

Ohjelma avautuu omaan ikkunaan, ikkunalla on kaksi näkymää. Menu-näkymä jossa ohjelma esitellään sekä käyttäjä voi antaa oman nimensä.
Peli näkymä jossa käyttäjä voi pelata peliä ja seurata pelitilannetta. Näkymiä vaihdellaan käyttäen ohjelman nimettyjä nappeja.

Molemmat näkymät ovat oma Scene-olionsa jotka rakennetaan GUI luokassa, GUI luokka ylläpitää ja tekee muutoksia Scene olioihin sekä päivittää Game-luokkaa käyttäjän syötteiden mukaan.

## Sovelluslogiikka

Ohjelman pakkauskaavio on seuraavanlainen: 

<img src="https://github.com/Viltska/ot-minesweeper/blob/master/dokumentit/kaaviot/pakkauskaavio.jpg" width="300">

## Päätoiminnallisuudet

Muutamia esimerkkejä ohjelman toiminnasta.

### Uusi peli

### Ruudun klikkaaminen
  
