package com.example.wunna.firebase;

public class Artist {


   private String ArtistTd;
   private String ArtistName;
  private   String ArtistGenere;
    public Artist(){

    }

    public Artist(String artistTd, String artistName, String artistGenere) {
        ArtistTd = artistTd;
        ArtistName = artistName;
        ArtistGenere = artistGenere;
    }



    public String getArtistTd() {
        return ArtistTd;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public String getArtistGenere() {
        return ArtistGenere;
    }
}
