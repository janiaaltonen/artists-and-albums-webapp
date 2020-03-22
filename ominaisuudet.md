### Web-sovelluksen ominaisuudet

Ominaisuudet on listattu kirjoitettuna listana, eikä tässä vaiheessa ole aikataulullisista syistä esim. UML-mallinnettu Java luokkia. 

##### UI
* Sovelluksen ulkoasua on hieman tyylitelty .css -tyylitiedoston avulla. Tyylittely on lähinnä fontin muokkaaminen ja taulukon "hienompi" ja selkeämpi näkymä.

* Sovellus näyttää artists -sivulla (/artists) tai "etusivulla" kaikkien tietokannasta (Artist -taulusta) löytyvien artistien järjestysnumeron ja nimen. Lisäksi näytetään jokaisen artistin albumimäärä.
    * Artistit näytetään taulukkona, jonka sarakkeina on järjestysnumero, nimi, albumien määrä ja poista rasti.
    * Artists -sivulla voidaan lisätä uusi artisti taulukkoon 
        * klikkaamalla "add new artist to list" -tekstiä avautuu nappula ja laatikko, johon kirjoitetaan lisättävän artistin nimi. Nappulaa painamalla lisätään uusi artisti.
        * Nappulan ja tekstikentän näyttäminen/ piilottaminen on tehty javascript -funktion avulla. 
        * Nappulan painaminen lähettää POST -pyynnön parametrina artistin nimi, jonka kautta uusi artisti lisätään.
        * Virheenkäsittely tilanteessa, jossa yritetään lisätä olemassa oleva artisti toistamiseen, ei ole toteutettu.
    * Artistin nimeä klikkaamalla päästään uudelle sivulle, jossa näytetään klikatun artistin kaikki albumit
        * Linkki on toteutettu vain niille artisteille, joilta löytyy albumeita.
    * X-nappia painamalla saadaan poistettua haluttu artisti 
        * Poistaminen on toteutettu vain niille artisteille, joilla ei ole yhtään albumia
        * Syynä aikataulu ja tarkemmin se, että tietokannan rakennetta vierasavainten osalta olisi pitänyt muuttaa tai toteuttaa useampi Delete -operaatio tietokantaan ennen kuin artisi, jolla on albumeita olisi voitu poistaa.

* Albums -sivulla näytetään klikatun artistin kaikki albumit.
    * Albumit on vastaava taulukko kuin artistit, mutta taulukossa näytetään albumin järjestysnumero, nimi ja kappaleiden määrä
    * Sivulla ei ole mitään toiminnallisuuksia
  
* Käyttäjän näkemä html -sivu muodostetaan .jsp -sivujen avulla. Esimerkiksi artistit -taulukko muodostetaan sen avulla 
tai ehto siitä, milä artistilla linkki albumeihin on olemassa ja millä ei ja minkä artistin voi poistaa taulukosta ja mitä ei.

  
##### Java -luokat

* Tietokannan taulujen yksittäinen rivi saadaan muutettua koodin käyttämäksi olioksi model -packagen luokissa Artist.java, Album.java ja Track.java
* CRUD -operaatiot tietokantaan tehdään db-packagessa ArtistDao.java, AlbumDao.java ja TrackDao.java luokissa.
    * Koska em. luokat tarvitsevat samanlaisen yhteyden tietokantaan ja tarvittujen resursien sulkemisen, on sitä varten toteutettu MySQLConnector.java -luokka, jota käytetään yhteyden avaamiseen ja resurssien sulkemiseen.
    * em. luokille on toteutettu myös rajapinnat ArtistList.java, Album.java ja TrackList.java
    * Esim. ArtistDao.java -luokan avulla voidaan hakea tietokannasta kaikkien artistien tiedot, kaikkien artistien tiedot ja albumien määrä, hakea yksittäinen artisti id:n perusteella, lisätä artisti nimen perusteella ja poistaa artisti id:n perusteella
* Käyttäjän selaimen kautta lähettämät pyynnöt GET, POST ja DELETE käsitellään servlet-packagessa, joka sisältää ArtistListServlet.java ja AlbumListServlet.java -luokat.
    * Yksittäinen servlet käsittelee tiettyyn polkuun tulleet pyynnöt esim. ArtistListServlet.java -luokkaan ohjautuu pyynnöt (/artists), jotka ohjautuvat luokan sisällä tietylle metodille pyynnön tyypin esim. GET perusteella
    * servlet käsittelee pyynnön, jonka jälkeen se lähettää käyttäjälle vastauksena .jsp -sivun tai JSON-objektin. 
    
##### Muuta 

* ArtistDao.java -luokalle on toteutettu muutamia yksikkötestejä, joiden perusteella huomasin ja ehtisin korjata ainakin pari bugia
* Esim. Artist.java -luokka sisältää olion attribuutin "number", joka näyttää kyseisen olion järjestysnumeron ArtistDao.java -luokan olion listassa.
* Sovelluksen kehittämisessä hyödynnettiin versionhallintaa, tosin pull requestien ja mergejen osalta siitä saattoi tulla aika sekavaa. 
* Ideana oli lisätä paljon muitakin ominaisuuksia, esim. artistien näyttäminen laskevassa järjestyksessä nimen suhteen tai artistin hakeminen nimen perusteella,
mutta aikataulut, kuten hyvin usein muutenkin, eivät tätä sallineet. Toki sovellusta voi jatkokehittää ja parantaa edelleen omalla ajalla jos vain intoa riittää.
* Kaikin puolin kuitenkin erittäin mielenkiintoista tekemistä.