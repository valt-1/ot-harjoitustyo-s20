# Space Invaders

Sovellus on versio Space Invaders -pelistä, ja sen on tehty harjoitustyönä Helsingin yliopiston Tietojenkäsittelytieteen osaston kurssilla Ohjelmistotekniikka.

Pelin toiminnalisuus selviää [vaatimusmäärittelystä](dokumentaatio/vaatimusmaarittely.md). Ohjeet sovelluksen käyttöön [täällä](dokumentaatio/kayttoohje.md).

## Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](dokumentaatio/testaus.md)

[Työaikakirjanpito](dokumentaatio/tuntikirjanpito.md)

## Releaset

[Viikko 6](https://github.com/behindthegroove/ot-harjoitustyo-s20/releases/tag/viikko6)

[Viikko 5](https://github.com/behindthegroove/ot-harjoitustyo-s20/releases/tag/viikko5)

## Komentorivitoiminnot

Komennot suoritetaan hakemistossa _SpaceInvaders_.

Suoritettavan jar-tiedoston generointi:
```
mvn package
```
Suoritettava jar-tiedosto löytyy hakemistosta _target_ nimellä _SpaceInvaders-1.0-SNAPSHOT.jar_

### Testaus

Testien suorittaminen:

```
mvn test
```

Testikattavuusraportin luominen:

```
mvn jacoco:report
```

Testikattavuusraportin tarkastelu: avaa selaimella tiedosto _target/site/jacoco/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](SpaceInvaders/checkstyle.xml) määriteltyjen tarkastusten suorittaminen:
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raportin tarkastelu: avaa selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDocin generointi:
```
mvn javadoc:javadoc
```
JavaDoc generoidaan hakemistoon _target/site/apidocs/_
