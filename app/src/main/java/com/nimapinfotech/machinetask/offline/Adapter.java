package com.nimapinfotech.machinetask.offline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.nimapinfotech.machinetask.R;
import java.util.List;
import androidx.viewpager.widget.PagerAdapter;
public class Adapter extends PagerAdapter {
    List<ImagesResponse> imagesResponseList;
    Context context;
    LayoutInflater mLayoutInflater;
    public Adapter(Context context, List<ImagesResponse> imagesResponseList) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imagesResponseList = imagesResponseList;
    }
    @Override
    public int getCount() {
        return (null != imagesResponseList ? imagesResponseList.size() : 0);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.images_list, container, false);

        ImagesResponse getImage = imagesResponseList.get(position);

        ImageView imageView = itemView.findViewById(R.id.image);
        Glide.with(context)
                .load(getImage.getImage())
                .into(imageView);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


}


