# Arkkitehtuurikuvaus

## Rakenne

Ohjelman luokka- ja pakkausrakenne on seuraavanlainen:

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/luokkajapakkauskaavio.png">

## Käyttöliittymä

Käyttöliittymä on eriytetty sovelluslogiikasta ja löytyy omasta luokastaan _SpaceInvadersUi_. Käyttöliittymä koostuu kahdesta näkymästä: aloitusnäkymästä ja pelinäkymästä. Pelinäkymässä käyttöliittymä kutsuu käyttäjän antamia näppäimistösyötteitä vastaavia sovelluslogiikan metodeja. Pelinäkymä hyödyntää _AnimationTimer_-oliota, joka käskee sovelluslogiikkaa päivittämään pelitilanteen ja pyytää aina sovelluslogiikalta näytölle piirrettävien pelihahmot uusilla sijainneilla päivitettyinä.

## Sovelluslogiikka

Pelin logiikasta vastaa luokka _Game_, joka hyödyntää pelihahmoja kuvaavia luokkia _LaserGun_, _Alien_ ja _Shot_. _Game_-luokka tarjoaa metodit käyttöliittymän toiminnoille, joita ovat lasertykin liikuttaminen ja ampuminen. Lisäksi _Game_-luokalla on käyttöliittymässä näytettävän animaation päivittämisen mahdollistava _update_-metodi.

Sovelluslogiikka huolehtii siitä, etteivät pelihahmot ohjaudu peliruudun ulkopuolelle ja siitä, että käyttöliittymän pelinäkymässä piirrettäväksi välitetään vain hahmot, jotka ovat edelleen pelissä.

## Tietojen pysyväistallennus

Sovellus tallentaa pelin parhaat pisteet tiedostoon, jonka nimi määritellään sovelluksen juuressa sijaitsevassa konfiguraatiotiedostossa _config.properties_. Tallentaminen on toteutettu Data Access Object -suunnittelumallia noudattaen: tallennuksesta huolehtii luokka _FileHiScoreDao_, joka on piilotettu rajapinnan _HiScoreDao_ taakse. Sovelluslogiikka käyttää vain rajapintaa HiScoreDao, joten tallennukselle on helppo tehdä tarvittaessa muitakin toteutuksia.

## Päätoiminnallisuudet

### Pelin aloittaminen

Kun käyttäjä klikkaa aloitusnäkymässä painiketta _start_, luodaan uusi peli eli luokan _Game_ olio. _Game_ luo tarvitsemansa pelihahmot luokista _LaserGun_ ja _Alien_. Käyttöliittymän näkymä päivitetään asettamalla näkymäksi pelinäkymä eli _gameScene_.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/sekvenssikaavio1.png">


### Lasertykin liikuttelu

Käyttäjän painaessa oikeaa nuolinäppäintä, kutsuu käyttöliittymä pelilogiikan _Game_ metodia _moveGunRight_. Sovelluslogiikka selvittää, onko lasertykin sijainti saavuttanut peliruudun rajan kutsumalla _LaserGunin_ metodia _getLocationX_ ja vertaamalla sen palauttamaa arvoa ruudun reunoja vastaaviin arvoihin. Tapauksessa, jossa reunaa ei vielä ole saavutettu, kutsuu pelilogiikka _LaserGunin_ metodia _moveRight_. Liikkuminen vasemmalle toimii samalla logiikalla.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/sekvenssikaavio2.png">

### Ampuminen

Käyttäjän panaessa välilyöntinäppäintä, kutsuu käyttöliittymä pelilogiikan _Game_ metodia _shoot_. _Game_ hakee lasertykin sijainnin kutsumalla _LaserGunin_ metodia _getLocationX_ ja luo oikeasta sijainnista lähtevän ammuksen välittämällä _LaserGunin_ palauttaman arvon _Shot_-luokan konstruktorille.

<img src="https://raw.githubusercontent.com/behindthegroove/ot-harjoitustyo-s20/master/dokumentaatio/kuvat/sekvenssikaavio3.png">

## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymän rakentava koodi jäi rakenteeltaan turhan sekavaksi, ja sitä kannattaisi jakaa useampiin metodeihin.
