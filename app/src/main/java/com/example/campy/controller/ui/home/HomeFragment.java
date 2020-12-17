package com.example.campy.controller.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campy.model.Food;
import com.example.campy.R;
import com.example.campy.controller.RecyclerViewAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    List<Food> lstFood;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        lstFood = new ArrayList<>();
        lstFood.add(new Food("Hamburgers","Categorie Food","Description Food",R.drawable.thewildrobot));
        lstFood.add(new Food("Pizza zouza","Categorie Food","Description Food",R.drawable.pizza));
        lstFood.add(new Food("Pepperoni pizza ","Categorie Food","Description Food",R.drawable.pepperoni));
        lstFood.add(new Food("Kg's Chinese","Categorie Food","Description Food",R.drawable.chinese));
        lstFood.add(new Food("Pizza italinou","Categorie Food","Description Food",R.drawable.piz));
        lstFood.add(new Food("Pizza crnou","Categorie Food","Description Food",R.drawable.pizza));
        lstFood.add(new Food("Plat Poser","Categorie Food","Description Food",R.drawable.plat));
        lstFood.add(new Food("Hamburgers","Categorie Food","Description Food",R.drawable.thewildrobot));
        lstFood.add(new Food("Sandwich « makloub »","Categorie Food","Description Food",R.drawable.makloub));
        lstFood.add(new Food("Fricassé","Categorie Food","Description Food",R.drawable.fricass));
        lstFood.add(new Food("Crepes","Categorie Food","Description Food",R.drawable.crepes));
        lstFood.add(new Food("Baget Farci Fast Food","Categorie Food","Description Food",R.drawable.baget));
        lstFood.add(new Food("Hamburgers","Categorie Food","Description Food",R.drawable.thewildrobot));
        lstFood.add(new Food("Paninis halal","Categorie Food","Description Food",R.drawable.paninis));
        lstFood.add(new Food(" Libanais hou hou ","Categorie Food","Description Food",R.drawable.libanais));


        RecyclerView myrv =root.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(getContext(), lstFood);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(myAdapter);
        return root;
    }

}