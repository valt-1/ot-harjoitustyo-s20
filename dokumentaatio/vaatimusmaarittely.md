# Vaatimusmäärittely

## Space Invaders

Sovellus on peli, jossa ruudun yläreunasta laskeutuu joukko vihamielisiä avaruusolioita ja ruudun alareunassa on pelaajan ohjaama lasertykki. Pelaajan on tarkoitus tuhota avaruusoliot ampumalla ennen kuin ne pääsevät ruudun alalaitaan. 

Viholliset liikkuvat rivistönä edestakaisin ruudun vasemman ja oikean reunan välillä, ja aina jommankumman reunan saavuttaessaan vihollisjoukko laskeutuu alaspäin. Lisäksi viholliset laukovat ammuksia, joita pelaaja joutuu väistelemään. Jos avaruusolion ammus osuu lasertykkiin, pelaaja menettää yhden elämän. Jos pelaajan ammus osuu avaruusolioon, olio katoaa ruudulta ja pelaaja saa pisteitä. 

## Toiminnallisuus

### Sovelluksen käynnistyessä
- käyttäjä näkee hiscoren ja lyhyen ohjeen peliin
- käyttäjä voi aloittaa peliin __tehty__

### Pelin aikana
- käyttäjä voi liikuttaa lasertykkiä vasemmalle tai oikealle __tehty__
- käyttäjä voi ampua ylöspäin __tehty__
    - jos osuu avaruusolioon, olio katoaa ruudulta ja pelaaja saa pisteitä __tehty__
- avaruusoliot liikkuvat __tehty__
- avaruusoliot ampuvat
- käyttäjä näkee pelin aikana kertyneet pisteet __tehty__
- käyttäjä näkee hiscoren __tehty__
- käyttäjä näkee jäljellä olevat elämät
    - jos avaruusolion ammus osuu lasertykkiin, pelaaja menettää elämän
- käyttäjä voi lopettaa pelin kesken __tehty__
- käyttäjä voi pelata kunnes avaruusoliot saavuttavat ruudun alalaidan, elämät loppuvat tai kaikki avaruusoliot on tuhottu

### Pelin päätyttyä
- käyttäjä näkee pelin aikana kertyneet pisteet ja hiscoren
- jos hiscore ylitettiin, tallennetaan uusi hiscore __tehty__
- käyttäjä voi aloittaa uuden pelin


## Jatkokehitysideoita
- suojakilvet jotka pysättävät ammukset mutta tuhoutuvat vähitellen osumista
- vihollisten yläpuolelle ilmestyy välillä ufo, jonka ampumisesta saa lisäpisteitä
- vihollisten liike nopeutuu niiden määrän pienentyessä
- käyttäjätunnuksen luominen, kirjautuminen ja käyttäjäkohtainen hiscore
- käyttäjä voi valita pelin vaikeustason
- jos pelaaja saa kaikki avaruusoliot ammuttua, ilmestyy ruudun yläreunaan uudet oliot ja peli jatkuu vaikeampana
