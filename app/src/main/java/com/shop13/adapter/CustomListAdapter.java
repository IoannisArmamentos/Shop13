package com.shop13.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.analytics.HitBuilders;
import com.shop13.R;
import com.shop13.app.AppController;
import com.shop13.model.Product;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Product> productItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Product> productItems) {
        this.activity = activity;
        this.productItems = productItems;
    }

    @Override
    public int getCount() {
        return productItems.size();
    }

    @Override
    public Object getItem(int location) {
        return productItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        Button button = (Button) convertView.findViewById(R.id.button);

        // getting pruduct data for the row
        final Product m = productItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        //simple check gia to ean dn vrethikan proionta me vasi tin timi oste na vgalei "den vrethikan proionta"
        //ean uparxei timi
        if (m.getSiteUrl() == null) {

            name.setText(m.getName());
            button.setVisibility(View.INVISIBLE);

        } else {
            button.setVisibility(View.VISIBLE);
            name.setClickable(true);
            name.setMovementMethod(LinkMovementMethod.getInstance());
            String text = "<a href='" + m.getSiteUrl() + "'>" + m.getName() + "</a>";
            name.setText(Html.fromHtml(text));

            // price
            String euro = "\u20ac";
            String[] parts = String.valueOf(m.getPrice() + euro).split("\\.");
            //String text = "<span>" + parts[0] + "</span><sub>" + parts[1] + "</sub>";
            //String text = "<font size=200 color=#cc0029>" + parts[0] + "</font> <font color=#ffcc00>" + parts[1] + "</font>";
            //price.setText(String.valueOf(m.getPrice()) + " " + euro);

		    /*final SpannableString text = new SpannableString(parts[0]);
            final SpannableString text1 = new SpannableString(parts[1]);
		    text.setSpan(new RelativeSizeSpan(2.0f), 0, 1,
			Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		    text1.setSpan(new RelativeSizeSpan(0.5f), 2, 5,
			Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
            Spannable mainPrice = new SpannableString(parts[0]);

            mainPrice.setSpan(new RelativeSizeSpan(3.5f), 0, mainPrice.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            price.setText(mainPrice);
            Spannable subPrice = new SpannableString("." + parts[1]);

            subPrice.setSpan(new RelativeSizeSpan(1.2f), 0, subPrice.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            price.append(subPrice);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppController.tracker().send(new HitBuilders.EventBuilder()
                            .setCategory("Click")
                            .setAction("AGORA")
                            .setLabel(m.getName())
                            .build());
                    Uri uri = Uri.parse(m.getBuyUrl()); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return convertView;
    }

}