# Ohjelmistotekniikka, harjoitustyö

Harjoitustyön aiheena on versio Space Invaders -pelistä, tarkempi toiminnalisuus selviää [vaatimusmäärittelystä](dokumentaatio/vaatimusmaarittely.md). Pelaaja voi liikkua nuolinäppäimillä ja ampua painamalla välilyöntiä.

## Dokumentaatio

[Käyttöohje](dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/behindthegroove/ot-harjoitustyo-s20/releases/tag/viikko5)

## Komentorivitoiminnot

Komennot suoritetaan hakemistossa _SpaceInvaders_.

Ohjelman suorittaminen:

```
mvn compile exec:java -Dexec.mainClass=spaceinvaders.ui.Main
```

Testien suorittaminen:

```
mvn test
```

Testikattavuusraportin luominen:

```
mvn jacoco:report
```

Testikattavuusraportin tarkastelu: avaa selaimella tiedosto _target/site/jacoco/index.html_

Checkstyle:
```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raportin tarkastelu: avaa selaimella tiedosto _target/site/checkstyle.html_
