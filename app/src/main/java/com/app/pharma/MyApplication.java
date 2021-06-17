package com.app.pharma;


import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.app.pharma.Activities.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nizar on 07/12/16.
 */
public class MyApplication extends Application {



    @Override
    public void onCreate() {

        super.onCreate();

        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);

    }







    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    public void setFragment(FragmentActivity fragmentActivity, Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        // Replace fragmentCotainer with your container id

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();

    }


    public void setDownFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();
    }
    public void setUpFragmentWithoutBackStack(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment)/*.addToBackStack(((MainActivity) fragmentActivity).bodyFragment)*/.commitAllowingStateLoss();
    }
    public void setUpFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();
    }
    public void setAddFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                .add(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commitAllowingStateLoss();

    }

    public void setFragmentWithBackAnimation(FragmentActivity fragmentActivity, Fragment fm) {
        ((MainActivity) fragmentActivity).bodyFragment = fm.getClass().getSimpleName();
        Fragment currentFragment = fragmentActivity.getSupportFragmentManager().findFragmentById(R.id.fragment_container);

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commit();

    }

    public void setFragmentWithBackAnimationFromUp(FragmentActivity fragmentActivity, Fragment fm) {
        ((MainActivity) fragmentActivity).bodyFragment = fm.getClass().getSimpleName();

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_top)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commit();

    }
    public void setFragmentWithBackAnimationFromDown(FragmentActivity fragmentActivity, Fragment fm) {

        ((MainActivity) fragmentActivity).bodyFragment = fm.getClass().getSimpleName();

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                    .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment).addToBackStack(((MainActivity) fragmentActivity).bodyFragment).commit();

    }
    public void setFragmentWithCustomAnimation(FragmentActivity fragmentActivity, Fragment fm, int[] anims){
        String TAG = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(anims[0], anims[1], anims[2], anims[3])
                .replace(R.id.fragment_container, fm, TAG).addToBackStack(TAG).commit();
    }
    public void addFragment(FragmentActivity paramFragmentActivity, Fragment paramFragment, int paramInt, String paramString)
    {
        paramFragmentActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom).add(paramInt, paramFragment, paramString).addToBackStack(paramString).commit();
    }
    public void setFragmentWithoutAnimation(FragmentActivity fragmentActivity, Fragment fm) {
        ((MainActivity) fragmentActivity).bodyFragment = fm.getClass().getSimpleName();
        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fm, ((MainActivity) fragmentActivity).bodyFragment)
                /*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();

    }

    public void refreshFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction().detach(fm).attach(fm)/*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();
    }
    public void removeFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction().remove(fm).commit();
    }
    public void setDefaultFragmentSharedElements(FragmentActivity fragmentActivity, Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )

                    .commit();


    }

    public void setFragmentSharedElements(FragmentActivity fragmentActivity, Fragment fm, View SharedElement, String nameShared){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id

            fragmentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )
                    .addSharedElement(SharedElement, nameShared)
                    .commit();


    }
    public void setFragmentMultiSharedElements(FragmentActivity fragmentActivity, Fragment fm, View SharedElement, String nameShared, View SharedElement1, String nameShared1){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();
        // Replace fragmentCotainer with your container id

            fragmentActivity
                    .getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fm,  ((MainActivity) fragmentActivity).bodyFragment )
                    .addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )
                    .addSharedElement(SharedElement, nameShared)
                    .addSharedElement(SharedElement1, nameShared1)
                    .commit();
    }
}