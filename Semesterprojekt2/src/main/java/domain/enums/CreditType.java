package domain.enums;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum CreditType {
    Billedkunstner,
    BilledOgLydRedigering,
    Casting,
    Colourgrading,
    Dirigenter,
    Drone,
    Dukkefører,
    Dukkeskaber,
    Fortæller,
    Fotograf,
    Forlæg,
    GrafiskDesigner,
    Indtaler,
    Kapelmester,
    Klipper,
    Programkoncept,
    Konsulent,
    Kor,
    Koreografi,
    LydEllerTonemester,
    Ledredigering,
    Lys,
    Medvirkende,
    Musiker,
    MusikalskArrangement,
    OrkesterEllerBand,
    Oversætter,
    Producent,
    Producer,
    ProduktionskoordinatorEllerProduktionsleder,
    Programansvarlig,
    RedaktionEllerTilrettelæggelse,
    Redaktør,
    Rekvisitør,
    Scenograf,
    ScripterEllerProducerassistent,
    SpecialEffects,
    Sponsor,
    TegnefilmEllerAnimation,
    Tekster,
    TekstOgMusik,
    UhonoreretEkstraordinærIndsats;


    public static ArrayList<CreditType> getEnum() {
        EnumSet<CreditType> all = EnumSet.allOf(CreditType.class);
        ArrayList<CreditType> list = new ArrayList<>(all.size());
        for (CreditType ct :
                all) {
            list.add(ct);
        }
        return list;
    }
}
