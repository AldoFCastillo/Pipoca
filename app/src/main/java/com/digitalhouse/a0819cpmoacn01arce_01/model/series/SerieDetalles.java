package com.digitalhouse.a0819cpmoacn01arce_01.model.series;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SerieDetalles {

    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("created_by")
    private List<CreatedBy> createdBy = null;
    @SerializedName("episode_run_time")
    private List<Integer> episodeRunTime = null;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("genres")
    private List<Genres> genres;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("id")
    private String id;
    @SerializedName("in_production")
    private Boolean inProduction;
    @SerializedName("languages")
    private List<String> languages = null;
    @SerializedName("last_air_date")
    private String lastAirDate;
    /*@SerializedName("last_episode_to_air")
    private LastEpisodeToAir lastEpisodeToAir;*/
    @SerializedName("name")
    private String name;
    /*@SerializedName("next_episode_to_air")
    private NextEpisodeToAir nextEpisodeToAir;*/
    @SerializedName("networks")
    private List<Network> networks = null;
    @SerializedName("number_of_episodes")
    private String numberOfEpisodes;
    @SerializedName("number_of_seasons")
    private String numberOfSeasons;
    @SerializedName("origin_country")
    private List<String> originCountry = null;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("poster_path")
    private String posterPath;
   /* @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies = null;*/
    @SerializedName("seasons")
    private List<Season> seasons = null;
    @SerializedName("status")
    private String status;
    @SerializedName("type")
    private String type;
    @SerializedName("vote_average")
    private String voteAverage;
    @SerializedName("vote_count")
    private String voteCount;

    public SerieDetalles() {
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public List<CreatedBy> getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(List<CreatedBy> createdBy) {
        this.createdBy = createdBy;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public String getBackdropPath() {
        backdropPath= "http://image.tmdb.org/t/p/original" +backdropPath;
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getEpisodeRunTime() {
        Integer duracion = 0;
        if (episodeRunTime.size()!=0)
         duracion = episodeRunTime.get(0);
        return duracion;
    }

    public void setEpisodeRunTime(List<Integer> episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getInProduction() {
        return inProduction;
    }

    public void setInProduction(Boolean inProduction) {
        this.inProduction = inProduction;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getLastAirDate() {
        return lastAirDate;
    }

    public void setLastAirDate(String lastAirDate) {
        this.lastAirDate = lastAirDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(String numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(String numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        posterPath= "http://image.tmdb.org/t/p/original" +posterPath;
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }
}


