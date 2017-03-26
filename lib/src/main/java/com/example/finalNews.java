
package com.example;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class finalNews {

    @SerializedName("body")
    private String mBody;
    @SerializedName("css")
    private List<String> mCss;
    @SerializedName("ga_prefix")
    private String mGaPrefix;
    @SerializedName("id")
    private Long mId;
    @SerializedName("image")
    private String mImage;
    @SerializedName("image_source")
    private String mImageSource;
    @SerializedName("js")
    private List<Object> mJs;
    @SerializedName("recommenders")
    private List<Recommender> mRecommenders;
    @SerializedName("section")
    private Section mSection;
    @SerializedName("share_url")
    private String mShareUrl;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("type")
    private Long mType;

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public List<String> getCss() {
        return mCss;
    }

    public void setCss(List<String> css) {
        mCss = css;
    }

    public String getGaPrefix() {
        return mGaPrefix;
    }

    public void setGaPrefix(String ga_prefix) {
        mGaPrefix = ga_prefix;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getImageSource() {
        return mImageSource;
    }

    public void setImageSource(String image_source) {
        mImageSource = image_source;
    }

    public List<Object> getJs() {
        return mJs;
    }

    public void setJs(List<Object> js) {
        mJs = js;
    }

    public List<Recommender> getRecommenders() {
        return mRecommenders;
    }

    public void setRecommenders(List<Recommender> recommenders) {
        mRecommenders = recommenders;
    }

    public Section getSection() {
        return mSection;
    }

    public void setSection(Section section) {
        mSection = section;
    }

    public String getShareUrl() {
        return mShareUrl;
    }

    public void setShareUrl(String share_url) {
        mShareUrl = share_url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Long getType() {
        return mType;
    }

    public void setType(Long type) {
        mType = type;
    }

}
