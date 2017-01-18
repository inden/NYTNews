package com.in.den.android.popnews.data;

        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Movie {


    @SerializedName("results")
    @Expose
    private List<Result> results = null;



    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Result {

        @SerializedName("display_title")
        @Expose
        private String displayTitle;
        @SerializedName("byline")
        @Expose
        private String byline;
        @SerializedName("headline")
        @Expose
        private String headline;
        @SerializedName("summary_short")
        @Expose
        private String summaryShort;
        @SerializedName("opening_date")
        @Expose
        private Object openingDate;
        @SerializedName("link")
        @Expose
        private Link link;
        @SerializedName("multimedia")
        @Expose
        private Multimedia multimedia;

        public String getDisplayTitle() {
            return displayTitle;
        }

        public void setDisplayTitle(String displayTitle) {
            this.displayTitle = displayTitle;
        }

        public String getByline() {
            return byline;
        }

        public void setByline(String byline) {
            this.byline = byline;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getSummaryShort() {
            return summaryShort;
        }

        public void setSummaryShort(String summaryShort) {
            this.summaryShort = summaryShort;
        }

        public Object getOpeningDate() {
            return openingDate;
        }

        public void setOpeningDate(Object openingDate) {
            this.openingDate = openingDate;
        }

        public Link getLink() {
            return link;
        }

        public void setLink(Link link) {
            this.link = link;
        }

        public Multimedia getMultimedia() {
            return multimedia;
        }

        public void setMultimedia(Multimedia multimedia) {
            this.multimedia = multimedia;
        }

    }

    public class Link {

        @SerializedName("url")
        @Expose
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Multimedia {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("src")
        @Expose
        private String src;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

    }




}