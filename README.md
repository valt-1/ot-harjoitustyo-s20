# Ohjelmistotekniikka, harjoitustyö

Harjoitustyön aiheena on versio Space Invaders -pelistä, tarkempi toiminnalisuus selviää [vaatimusmäärittelystä](dokumentaatio/vaatimusmaarittely.md). Pelaaja voi liikkua nuolinäppäimillä ja ampua painamalla välilyöntiä, tarkemmat ohjeet sovelluksen käyttöön [täällä](dokumentaatio/kayttoohje.md).

## Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

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
