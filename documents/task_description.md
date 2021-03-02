# Madáretető Tensor Flow alapú automatikus felismeréssel és fotózással

## Téma kiírása

A téma célja az Android MLKit és a TensorFLow megismerése egy példaalkalmazáson keresztül.

Az alkalmazás célja, hogy egy (a fejlesztés során mobiltelefonnal szimulált) webkamerás Android egységgel felszerelt madáretető készítsünk, amely felismeri, ha madár szállt rá, azonosítja, és fotókat készít róla.

# Specifikáció

    Az alkalmazás egy madáretetőbe szerelt kamerás egységet valósít meg android eszközre. A program célja, hogy a kamera segítségével felismerje és azonosítsa a madáretetőre szállt madarakat. Az érzékelés pillanatában a rendszer egy képet készít a madárról, majd elmenti dátummal ellátva, hogy melyik madárfajt érzékelte. A detektálások az alkalmazáson belül visszakövethetők és megtekinthetőek az érzékelés dátuma, a kép, amit a rendszer készített és a madár fajtája.
    Az applikácó megnyítása után megjelenik a kamera képünk, amely már képes érzékelni a madarakat. A képernyőn továbbá elhelyezkedik egy gomb, amely a galériát nyitja meg. Itt listázodik ki az összes madár érzékelése névvel, képpel és dátummal. A listából lehet törölni az elemeket. 

    A kamera és a galéria fragment-ként épül fel. A galérai megjelenése recyclerview lesz, melyek közül az elemek megnyithatóak és törölhetőek.