package fr.iat.cinema.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

public class ImportFilmsFromTMDB {

    String filename = "http://files.tmdb.org/p/exports/movie_ids_01_28_2019.json.gz";

        InputStream httpIS;

    {
        try {
            httpIS = new URL(filename).openStream();
            InputStream gzipIS = new GZIPInputStream(httpIS);
            InputStream bufferedIS = new BufferedInputStream(gzipIS);

            System.out.println(bufferedIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
