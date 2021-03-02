# Haladási napló

## 2.hét - 2021.02.17. - 02.23.

Az első konzultációs alkalmon átbeszéltük a feladat kiírást és megbeszéltük a kiindulási alapokat. Kaptam pár ötletet, hogy mit lehet kihozni a projektből.

A hét folyamán gondolkodtam ötleteken:
 * Taxi kamerákat kihasználva detektálni az úthibákat (kátyú, tábla rongálva, festés lekopott) és ezeket feljegyezni a térképen. Önkormányzatoknak hasznos információ lehet, hogy hol kell javítani. Felhasználók számára is hasznos lehet, hogy hol kell vigyázni.
  * Növény betegségének detektálása. A kamera érzékeli a növényt és a program megállapítaná, hogy a növénynek szüksége lenne vízre vagy több napfényre esetleg túl van locsólva.

Elkészítettem egy github-os repo-t, amelyben a munkámat fogom végezni, illetve megterveztem a menetrend elejét. Megírtam a specifikáció alapját, amelyet később véglegesíteni kell.

Elkezdtem megismerkedni a CameraX API-val. A google developers oldalon egy remek dokumentáció van, ahol https://codelabs.developers.google.com/codelabs/camerax-getting-started#6 ezen a linkek elkészítettem a kezdő alkalmazást. Továbbiakban az említett oldalon mindent elolvastam a CameraX-el kapcsolatban.

## 3.hét - 2021.02.24. - 03.02.

Átdolgoztam az **ütemtervet** és belevettem plusz feladatokat. A 8.hét végére szeretném elkészíteni a végleges BirdTable alkalamzást a beszámolóval együtt. Ezután szeretnék plusz dolgokat kipróbálni és mélyebben bele ásni magam a témába. Ezeket elneveztem mellék kutatásoknak.
 * **IP kamera használata a BirdTable appba (élő közvetítés):** A nagypapámtól kaptam egy IP kamerát, amit szeretnék felhasználni a projektben. Jelenleg nem tudom, hogy milyen lehetőségeim vannak, de szeretnék megvalósítani egy elő közvetítést a madarak detektálására.
 * **Saját lego "futószalag" érzékelés:** Kiszeretném próbálni az általam készített képek felhasználásával az érzékelés mükődését. Egyenlőre a terv vele a különböző színű legok kiszűrése.
 * **Növény betegség detektálás**: Ezzel foglalkoznék utoljára. Ha nem marad rá idő akkor nyári projektnek tovább vinném. Jelenleg nem találtam megfelelő adatbázist. Szeretném felkeresni egy távoli ismerőst, aki mélyebben foglalkozik a nővénnyekkel, hogy egyáltalán megvalósítható-e a feladat. Ezután szeretnék felkeresni kerteket és kertészeteket, hátha van nekik privát képeik, amiket megosztanának velem.

 A **specifikációt** véglegesítettem, amennyire csak tudtam. Ha további funkciókat szeretnék implementálni, akkor bővíteni fogom.