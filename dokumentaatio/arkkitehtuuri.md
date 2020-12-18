# Arkkitehtuurikuvaus

## Rakenne
![Luokka- ja pakkauskaavio](https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/luokkajapakkauskaavio.png)

## Käyttöliittymä

Käyttöliittymä on eriytetty sovelluslogiikasta ja löytyy omasta luokastaan _SpaceInvadersUi_. Käyttöliittymä koostuu kahdesta näkymästä: aloitusnäkymästä ja pelinäkymästä. Pelinäkymässä käyttöliittymä kutsuu käyttäjän antamia näppäimistösyötteitä vastaavia sovelluslogiikan metodeja. Pelinäkymä hyödyntää _AnimationTimer_-oliota, joka käskee sovelluslogiikkaa päivittämään pelitilanteen ja pyytää aina sovelluslogiikalta näytölle piirrettävien pelihahmot uusilla sijainneilla päivitettyinä.

## Sovelluslogiikka

Pelin logiikasta vastaa luokka _Game_.

## Tietojen pysyväistallennus

Sovellus tallentaa pelin parhaat pisteet tiedostoon. Tallentaminen on toteutettu Data Access Object -suunnittelumallia noudattaen: tallennuksesta huolehtii luokka _FileHiScoreDao_, joka on piilotettu rajapinnan _HiScoreDao_ taakse. Sovelluslogiikka käyttää vain rajapintaa HiScoreDao, joten tallennukselle on helppo tehdä tarvittaessa muitakin toteutuksia.

## Toiminnallisuudet
![Sekvenssikaavio](https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/sekvenssikaavio1.png)
