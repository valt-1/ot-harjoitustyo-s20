# Testausdokumentti

Sovellusta on testattu yksikkö- ja integraatiotasolla JUnit-testein. Järjestelmätasolla testaus on tehty manuaalisesti.

## Yksikkö- ja integraatiotestaus

Integraatiotestiluokassa _GameTest_ testataan pelilogiikan toimintaa. Integraatiotestit käyttävät rajapinnan _HiScoreDao_ toteuttavaa _StubDao_-oliota.

Pelihahmoja kuvaaville luokille on tehty joitakin yksikkötestejä.

DAO-luokan _FileHiScoreDao_ testaus on toteutettu tilapäisellä tiedostolla, joka luodaan käyttämällä JUnitin _TemporaryFolderia_.

### Testikattavuus

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/testikattavuus.png">

## Järjestelmätestaus

Sovellusta on järjestelmätestattu manuaalisesti käymällä läpi määrittelydokumentissa luetellut toiminnallisuudet.

Testausta on suoritettu tilanteissa, joissa sovelluksen käyttämä tallennustiedosto on ollut olemassa, ja myös siten että tiedostoa ei ole ollut valmiina. Sovellusta on testattu myös oletuskonfiguraatioista poikkeavilla asetuksilla, jolloin tiedoston _config.properties_ sisältöä on muokattu ennen testaamista. Testausta on suoritettu myös tilanteissa, joissa konfiguraatiotiedosto puuttuu tai sen sisältö on virheellinen.
