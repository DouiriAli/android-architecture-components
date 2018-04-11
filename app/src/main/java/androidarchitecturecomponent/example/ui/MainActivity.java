package androidarchitecturecomponent.example.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import androidarchitecturecomponent.example.R;
import androidarchitecturecomponent.example.ui.user.ListFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            if (savedInstanceState == null)
                switchFragment(ListFragment.newInstance(), true, false, false);
    }

    public void switchFragment(Fragment fragment, boolean anim, boolean stack, boolean add){

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(add)
            transaction.add(R.id.container, fragment);
        else
            transaction.replace(R.id.container, fragment);

        if(stack)
            transaction.addToBackStack(fragment.getClass().getName().toString());

        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
