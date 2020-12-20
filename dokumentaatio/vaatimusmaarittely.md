# Vaatimusmäärittely

## Space Invaders

Sovellus on peli, jossa ruudun yläreunasta laskeutuu joukko vihamielisiä avaruusolioita ja ruudun alareunassa on pelaajan ohjaama lasertykki. Pelaajan on tarkoitus tuhota avaruusoliot ampumalla ennen kuin ne pääsevät ruudun alalaitaan. 

Viholliset liikkuvat rivistönä edestakaisin ruudun vasemman ja oikean reunan välillä, ja aina jommankumman reunan saavuttaessaan vihollisjoukko laskeutuu alaspäin. Lisäksi viholliset laukovat ammuksia, joita pelaaja joutuu väistelemään. Jos avaruusolion ammus osuu lasertykkiin, pelaaja menettää yhden elämän. Jos pelaajan ammus osuu avaruusolioon, olio katoaa ruudulta ja pelaaja saa pisteitä. 

## Toiminnallisuus

### Sovelluksen käynnistyessä
- käyttäjä näkee lyhyen ohjeen peliin
- käyttäjä voi aloittaa pelin

### Pelin aikana
- käyttäjä voi liikuttaa lasertykkiä vasemmalle tai oikealle
- käyttäjä voi ampua ylöspäin
    - jos osuu avaruusolioon, olio katoaa ruudulta ja pelaaja saa pisteitä
- avaruusoliot liikkuvat
- avaruusoliot ampuvat alaspäin
- käyttäjä näkee pelin aikana kertyneet pisteet
- käyttäjä näkee hiscoren
- käyttäjä näkee jäljellä olevat elämät
    - jos avaruusolion ammus osuu lasertykkiin, pelaaja menettää elämän
- käyttäjä voi lopettaa pelin kesken
- käyttäjä voi pelata kunnes avaruusoliot saavuttavat ruudun alalaidan, elämät loppuvat tai kaikki avaruusoliot on tuhottu

### Pelin päätyttyä
- pelianimaatio pysähtyy ja käyttäjä näkee pelin aikana kertyneet pisteet sekä ilmoituksen pelin päättymisestä
- jos hiscore ylitettiin, tallennetaan uusi hiscore
- käyttäjä voi palata aloitusnäkymään


## Jatkokehitysideoita
- hiscoren näyttäminen aloitusnäkymässä
- suojakilvet jotka pysättävät ammukset mutta tuhoutuvat vähitellen osumista
- vihollisten yläpuolelle ilmestyy välillä ufo, jonka ampumisesta saa lisäpisteitä
- vihollisten liike nopeutuu niiden määrän pienentyessä
- käyttäjätunnuksen luominen, kirjautuminen ja käyttäjäkohtainen hiscore
- käyttäjä voi valita pelin vaikeustason
- jos pelaaja saa kaikki avaruusoliot ammuttua, ilmestyy ruudun yläreunaan uudet oliot ja peli jatkuu vaikeampana
