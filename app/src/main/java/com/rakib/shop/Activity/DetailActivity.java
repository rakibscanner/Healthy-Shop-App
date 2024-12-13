package com.rakib.shop.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rakib.shop.Adapter.SimilarAdapter;
import com.rakib.shop.Domain.ItemsDomain;
import com.rakib.shop.R;

public class DetailActivity extends AppCompatActivity {

    private ItemsDomain object;
    private ImageView backBtn, itemImg;
    private TextView priceKgTxt, titleTxt, descriptionTxt, ratingTxt;
    private RatingBar ratingBar;
    private TextView weightTxt, plusBtn, minusBtn, totalTxt;
    private int weight=1;
    private RecyclerView.Adapter similarAdapter;
    private RecyclerView recyclerViewSimilar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getBundles();
        initView();
        setVariable();
        initSimilarList();
    }

    private void initSimilarList() {
        recyclerViewSimilar = findViewById(R.id.similarView);
        recyclerViewSimilar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        similarAdapter = new SimilarAdapter(new MainActivity2().getData());
        recyclerViewSimilar.setAdapter(similarAdapter);

    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
        int drawableResourceId = getResources().getIdentifier(object.getImgPath(), "drawable", DetailActivity.this.getPackageName());

        Glide.with(DetailActivity.this)
                .load(drawableResourceId)
                .into(itemImg);

        priceKgTxt.setText(object.getPrice()+"$/kg");
        titleTxt.setText(object.getTitle());
        descriptionTxt.setText(object.getDescription());
        ratingTxt.setText("("+object.getRate()+")");
        ratingBar.setRating((float) object.getRate());
        weightTxt.setText(String.valueOf(weight));

        plusBtn.setOnClickListener(v -> {
            weight=weight+1;
            weightTxt.setText(weight+" kg");
            totalTxt.setText((weight*object.getPrice())+"$");
        });

        minusBtn.setOnClickListener(v -> {
            if(weight>1){
                weight=weight-1;
                weightTxt.setText(weight+" kg");
                totalTxt.setText((weight*object.getPrice())+"$");
            }
        });




    }

    private void initView() {
        backBtn = findViewById(R.id.backBtn);
        itemImg = findViewById(R.id.img);
        priceKgTxt = findViewById(R.id.priceKgTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        ratingBar = findViewById(R.id.ratingBar);
        weightTxt = findViewById(R.id.weightTxt);
        ratingTxt = findViewById(R.id.ratingTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        totalTxt = findViewById(R.id.totalTxt);


    }

    private void getBundles() {
        object = (ItemsDomain) getIntent().getSerializableExtra("object");
    }
}