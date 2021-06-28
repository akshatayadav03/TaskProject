
package com.nimapinfotech.machinetask.model;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nimapinfotech.machinetask.R;
import com.nimapinfotech.machinetask.glide.GlideApp;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;


public class Record {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("collectedValue")
    @Expose
    private Integer collectedValue;
    @SerializedName("totalValue")
    @Expose
    private Integer totalValue;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("mainImageURL")
    @Expose
    private String mainImageURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getCollectedValue() {
        return collectedValue;
    }

    public void setCollectedValue(Integer collectedValue) {
        this.collectedValue = collectedValue;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMainImageURL() {
        return mainImageURL;
    }

    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }

    @BindingAdapter("mainImageURL")
    public static void loadImage(View view, Record data) {

        ImageView imageview = view.findViewById(R.id.imageview);
        ImageView selectedgreeting = view.findViewById(R.id.loadericon);
        selectedgreeting.setVisibility(View.VISIBLE);


        GlideApp.with(view.getContext()).load(data.mainImageURL) .listener(new RequestListener<Drawable>() {

            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                selectedgreeting.setVisibility(View.GONE);

                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                selectedgreeting.setVisibility(View.GONE);
                return false;
            }
        }).dontAnimate().
                diskCacheStrategy(DiskCacheStrategy.ALL).
                signature(new ObjectKey(data.mainImageURL==null?"":data.mainImageURL)).
                error(R.mipmap.ic_launcher).thumbnail(0.5f).into(imageview);
    }



}

