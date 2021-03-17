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

Egy tensorflow sample tutoriál segítségével létrehoztam egy alkalmazást, amely felismer 5 fajta növényt. 

Tovább folytattam az alap alkalmazások elkészítését. A BirdDetection_First appban a cameraX-et és mlkit object detection használok. A TFHub-ról letöltöttem egy tflite fájlt, ami a madarakról tartalmaz neurális hálozatot. Az alkalmazás indításakor egy hibába ütköztem, mivel nem volt képes megnyitni a .tflite fájlt.

## 4.hét - 03.03. - 03.09.

A konzultációs alkalmon segítséget kaptam a fájl megnyitására, így sikerült megváltoztatni a kódot és működésre bírni az alkalmazást. A teszteléshez youtube videókat indítottam el. A tesztelést néhol megnehezíti, hogy a monitort mutatom a kamerának, így nem a legpontossabb és leggyorsabb az érzékelése. Továbbá a madár fajának latin nevével dolgozik, így sokszor rá kell keressni, hogy valóban az az a madár amít kiír. A következő célkítüzésem, hogy normális strukturába rendezzem a programot, úgy hogy az általam kitüzőtt funkciókra bővíthető legyen.

Első lépésnek szétszedtem az Analisyrt egy külön osztályba. MVVM-et választottam architekrurára, így még mielőtt tovább haladnék ennek nézek részletesen utána.

A hét folyamán sok oldalt és videót néztem az MVVM-ről, így megértettem alapszinten a működését. Első sorban a galéria elkészítése során szeretném használni. A kamera képre nem szeretnék rajzolni semmit, csak a tesztelés miatt rajzoltam ki eddig.

Elkészítettem a project_birdtable-t. A Basic Activity alapot választottam, így kaptam egy mainactivty-t és 2 fragmentet. Az OptionMenu-t egyből commenteztem, mivel egyenlőre erre nem lesz szükségem. Továbbiakban átneveztem a fragmenteket és layoutokat. Megvalósítottam a CameraFragment-nek egy FAB-ot, hogy a 2 fragment között továbbra is működjön a váltás.

Az előző example projektem segítségével megvalósítottam a madár érzékelést. Elhelyeztem egy TextView-ot, amire mindig kiírja, hogy melyik madarat találta meg. A továbbiakban szeretném elkészíteni a kép készítését. Ehhez először egy gombot fogok elhelyezni, hogy teszteljem, utána fogom hozzákötni a madár érzékelés action-t.

## 5.hét - 03.10. - 03.16.

Leimplementáltam a PhotoCaptureCOntroller-t, melynek segítségével a program képes kamerakép elmentésére.

## 6.hét - 03.17. - 03.23.

A hét első napján elkezdtem kijavítani a megbeszélt problémákat.
* Létrehoztam egy UI package-t, amibe refactoráltam a Fragmenteket és továbbá oda fogom létrehozni a VM-eket is.
* Szétválasztottam az Analyzer-t a TFModel-től egy külön osztályba. Ezt úgy hoztam létre, hogy bármely tflite-ot képes legyen feldolgozni.
* Szétválasztottam a PhotoCaptureController-t a file path elérési metódustól. Ezt a MainActivity-be helyeztem el, ahol egy companion object-ként bármelyik osztály elére. (Egyenlőre nem vagyok benne biztos, hogy ennek itt van a megfelelő helye, de itt működik)