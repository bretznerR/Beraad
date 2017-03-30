package com.iut.beraad.beraad;

import java.util.Date;

public class NbrJourEntre2Date
{

    public static long differenceDate(Date date1, Date date2)
    {
        //long UNE_HEURE = 60 * 60 * 1000L;
        //return ((date2.getTime() - date1.getTime() + UNE_HEURE) / (UNE_HEURE * 24));
        return ((date2.getTime() - date1.getTime())/(1000*60*60*24));
    }


}