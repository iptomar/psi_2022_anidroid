package com.psi.anidroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Anime {
    @SerializedName("idAnime")
    @Expose
    private Integer idAnime;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("quantEpisodios")
    @Expose
    private String quantEpisodios;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("sinopse")
    @Expose
    private String sinopse;
    @SerializedName("trailer")
    @Expose
    private String trailer;
    @SerializedName("autor")
    @Expose
    private String autor;
    @SerializedName("estudio")
    @Expose
    private String estudio;
    @SerializedName("data")
    @Expose
    private String data;
    @SerializedName("links")
    @Expose
    private String links;
    @SerializedName("fotografia")
    @Expose
    private String fotografia;
    @SerializedName("linkFoto")
    @Expose
    private String linkFoto;
    @SerializedName("listaDeReviews")
    @Expose
    private final List<Object> listaDeReviews = null;
    @SerializedName("listaDeFavoritos")
    @Expose
    private final List<Object> listaDeFavoritos = null;
    @SerializedName("listaDeCategorias")
    @Expose
    private final List<Object> listaDeCategorias = null;

    public Integer getIdAnime() {
        return idAnime;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantEpisodios() {
        return quantEpisodios;
    }

    public Double getRating() {
        return rating;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getTrailer(){
        return trailer;
    }

    public String getAutor() {
        return autor;
    }

    public String getEstudio() {
        return estudio;
    }

    public String getData() {
        return data;
    }

    public String getLinks() {
        return links;
    }

    public String getFotografia() {
        return fotografia;
    }

    public List<Object> getListaDeReviews() {
        return listaDeReviews;
    }

    public List<Object> getListaDeFavoritos() {
        return listaDeFavoritos;
    }

    public List<Object> getListaDeCategorias() {
        return listaDeCategorias;
    }

    public String getLinkFoto() {
        return linkFoto;
    }
}
