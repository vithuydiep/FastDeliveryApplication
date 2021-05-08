package com.example.TVK.View;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.TVK.Model.User.Customer;
import com.example.TVK.R;
import com.example.TVK.View.Fragment.AddNewDriver;
import com.example.TVK.View.Fragment.Category;
import com.example.TVK.View.Fragment.DetailInforCustomer;
import com.example.TVK.View.Fragment.HomeAdmin;
import com.example.TVK.View.Fragment.ListCustomer;
import com.example.TVK.View.Fragment.Notification;

public class MainAdminActivity extends AppCompatActivity implements IMainAdminActivity {
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        bottomNavigation = (MeowBottomNavigation) findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_account));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_order));
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_notifications));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_menu));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new HomeAdmin();
                        break;
                    case 2:
                        fragment = new ListCustomer();
                        break;
                    case 3:
                        fragment =new AddNewDriver();
                        break;
                    case 4:
                        fragment = new Notification();
                        break;
                    case 5 :
                        fragment = new Category();
                        break;
                    default:
                        fragment = new HomeAdmin();
                        break;
                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.setCount(3,"10");
        bottomNavigation.show(1,true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(),"YouClicked"+ item.getId(), Toast.LENGTH_LONG).show();
            }
        });
        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "You Reselected" +item.getId(),Toast.LENGTH_LONG).show();
            }
        });



    }


    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
    public void gotoDetailFragment(Customer customer)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DetailInforCustomer detailInforCustomer = new DetailInforCustomer();
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_customer", customer);
        detailInforCustomer.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_layout,detailInforCustomer);
        fragmentTransaction.addToBackStack(DetailInforCustomer.TAG);
        fragmentTransaction.commit();
    }




}
