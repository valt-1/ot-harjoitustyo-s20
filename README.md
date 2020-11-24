# Ohjelmistotekniikka, harjoitustyö

## Dokumentaatio

[Vaatimusmäärittely](dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

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
