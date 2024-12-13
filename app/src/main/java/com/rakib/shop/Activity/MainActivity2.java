package com.rakib.shop.Activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rakib.shop.Adapter.BestDealAdapter;
import com.rakib.shop.Adapter.CategoryAdapter;
import com.rakib.shop.Domain.CategoryDomain;
import com.rakib.shop.Domain.ItemsDomain;
import com.rakib.shop.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private RecyclerView.Adapter catAdapter , bestDealAdapter;
    private RecyclerView recyclerViewCat, recyclerViewBestDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initRecyclerViewCat();
        initLocation();
        initRecyclerViewBestDeal();
    }

    private void initLocation() {
        String[] items = new String[] {"LogAngels, USA", "NewYork, USA"};
        final Spinner locationSpin = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpin.setAdapter(adapter);
    }

    private void initRecyclerViewCat() {
        ArrayList<CategoryDomain> items = new ArrayList<>();
        items.add(new CategoryDomain("cat1","vegetable"));
        items.add(new CategoryDomain("cat2","fruits"));
        items.add(new CategoryDomain("cat3","dairy"));
        items.add(new CategoryDomain("cat4","drinks"));
        items.add(new CategoryDomain("cat5","Grain"));

        recyclerViewCat = findViewById(R.id.catView);
        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

//        catAdapter = new CategoryAdapter(items);
//        recyclerViewCat.setAdapter(catAdapter);

        catAdapter = new CategoryAdapter(items);
        recyclerViewCat.setAdapter(catAdapter);
    }

    private void initRecyclerViewBestDeal() {
        recyclerViewBestDeal = findViewById(R.id.bestView);
        recyclerViewBestDeal.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        bestDealAdapter = new BestDealAdapter(getData());
        recyclerViewBestDeal.setAdapter(bestDealAdapter);
    }

    public ArrayList<ItemsDomain> getData(){
        ArrayList<ItemsDomain> items = new ArrayList<>();
        items.add(new ItemsDomain("orange","Fresh Orange",6.2,"With its vibrant orange hue and a burst refreshing"+
                "citrus flavour, the juicy orange is a natural source of"+
                "vitamin C, invigorating your senses and"+
                "supporting your immune system. A delightful snack"+
                "that combines health and taste.",4.2));
        items.add(new ItemsDomain("apple","Fresh Apple",6.5,"Crisp and succulent, apples are nature's candy. Each bite offer a symphony of sweetness and a satisfying crunch. Packed with fibre and antioxidants, apples make for a wholesome snack, keeping your energized throughout the day.",4.8));
        items.add(new ItemsDomain("watermelon","Fresh Watermelon",5.1,"Quench your thirst and satisfy your sweet tooth with the hydrating goodness of watermelon. Bursting with juiciness and vibrant color, this summer favourite is a natural way to stay cool and refreshed. Enjoy the taste of summer with every juicy bite.",4.9));
        items.add(new ItemsDomain("berry","Fresh Berry",6,"Natures little jewels, cherries are a burst a sweet indulgence, packed with antioxidants, this tiny treats not only satisfy your sweet cravings but also contribute to overall well-being. pop a handful for a guilt -free snack that delights your taser buds.",4.5));
        items.add(new ItemsDomain("pineaplle","Fresh Pineapple",10,"Transport yourself to the tropics with the exotic taste of pineapple. Juicy and tantalizingly sweer, this goldent is not only a treat for your taser buds but also a rich source of vitamins and enzymes, promoting digestive health and adding a splash of sunshine to your day.",3));
        items.add(new ItemsDomain("strawberry","Fresh Strawberry",12,"Delight in the sweetness of strawberries, each berry a burst of flavour and nutrition. Weather enjoyed on their own or added to your favourite dishes, these red gems are a guilt-free pleasure, offering a dose of vitamin C, antioxidants and a touch of natural sweetness.",4));
        return items;
    }
}