package com.teamtreehouse.smellslikebakin;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface {
    // Tag fragment to avoid using findFragmentById
    public static final String LIST_FRAGMENT = "list_fragment";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use support.v4/getSupportFragmentManager() if AppCompActivity.
        // Use android.app.Fragment/getFragmentManager() for Activity class.

        // Id of fragment if directly added or id of placeholder if added dynamically
        ListFragment savedFragment = (ListFragment) getSupportFragmentManager().findFragmentByTag(LIST_FRAGMENT);
        if (savedFragment == null) {
            ListFragment fragment = new ListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.placeholder, fragment, LIST_FRAGMENT);
            fragmentTransaction.commit(); // Commit to make changes permanent.
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment fragment = new ViewPagerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeholder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null); // null b/c we are going back one transaction at a time
        fragmentTransaction.commit(); // Commit to make changes permanent.
        Toast.makeText(this, Recipes.names[index], Toast.LENGTH_SHORT).show();
    }
}
