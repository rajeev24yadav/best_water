package water.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.ViewModel;

import water.view.activity.LoginActivity;

public class ActivityWelcomeViewModel extends ViewModel {
    private Context context;

    public ActivityWelcomeViewModel(Context context) {
        this.context = context;

    }

    public void onWelcomeScreen() {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


}
