# Käyttöohje

Lataa tiedosto _SpaceInvaders.jar_

## Konfigurointi

Sovellus olettaa että sen käynnistyshakemistossa on tiedosto _config.properties_. Kyseisessä konfiguraatiotiedostossa määritellään nimi tiedostollle, johon hiscore tallennetaan, sekä pelihahmojen liikenopeudet. Konfiguraatiotiedoston sisältö on muotoa
```
hiScoreFile=hiscore.txt
alienSpeed=0.2
gunSpeed=1.0
shotSpeed=3.0
```

## Käynnistäminen

Käynnistä ohjelma komennolla
```
java -jar SpaceInvaders.jar
```

## Pelin aloittaminen

Sovellus käynnistyy aloitusnäkymään.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje1.png" width=400>

Pelin voi aloittaa klikkaamalla _Start game_ tai painamalla välilyöntiä tai enteriä.

## Pelaaminen

Tarkoituksena on ampua kaikki avaruusoliot ennen kuin ne pääsevät ruudun alalaitaan. Pelaaja joutuu myös väistelemään avaruusolioiden ammuksia, joista osuman saadessaan pelaaja menettää yhden elämän.

Pelinäkymä. Pelaaja näkee pistetilanteen ja jäljellä olevat elämät ikkunan ylälaidassa.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje2.png" width=400>

Pelaaja voi liikkua oikealla ja vasemmalla nuolinäppäimellä ja ampua painamalla välilyöntiä.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje3.png" width=400>

## Pelin lopettaminen

Pelin voi lopettaa kesken klikkaamalla _Quit game_, jolloin palataan aloitusnäkymään.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje4.png" width=400>

## Pelin päättyminen

Peli päättyy, kun elämät loppuvat, avaruusoliot saavuttavat peliruudun alalaidan, tai kun kaikki avaruusoliot on ammuttu. Myös pelin päätyttyä voi siirtyä takaisin aloitusnäkymään klikkaamalla _Quit game_.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje5.png" width=400>
<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje6.png" width=400>
<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje7.png" width=400>

Sovelluksen voi sulkea aloitusnäkymässä klikkaamalla _Quit_.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/kayttoohje8.png" width=400>
