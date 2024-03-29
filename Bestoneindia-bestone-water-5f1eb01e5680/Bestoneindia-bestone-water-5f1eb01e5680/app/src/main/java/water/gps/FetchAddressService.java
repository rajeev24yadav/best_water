package water.gps;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class FetchAddressService  extends IntentService {
    private ResultReceiver resultReceiver;
    private static final String TAG = "FetchAddressService";
    public FetchAddressService() {
        super("FetchAddressService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent!=null)
        {
            String errorMessage = "";
            resultReceiver = intent.getParcelableExtra(Constants.RECEIVER);
            Location location = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            if (location == null)
            {
                return;
            }
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try{
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            }catch (Exception e)
            {
                errorMessage = e.getMessage();
                e.printStackTrace();
            }
            if (addresses == null || addresses.isEmpty()){
                deliverResultToReceiver(Constants.FAILURE_RESULT,errorMessage);
            }
            else
            {
                Address address = addresses.get(0);
                //Log.e(TAG, "address: "+address.getAddressLine(0));
                ArrayList<String> addressFragments = new ArrayList<>();
                for (int i = 0; i <address.getMaxAddressLineIndex() ; i++) {
                    Log.e(TAG, "address: "+address.getAddressLine(i));
                    addressFragments.add(address.getAddressLine(i));

                }
                deliverResultToReceiver(Constants.SUCCESS_RESULT,
                        TextUtils.join(Objects.requireNonNull(System.getProperty("line.separator")),
                                addressFragments
                        ));
            }
        }
    }

    private void deliverResultToReceiver(int resultCode, String addressMessage)
    {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_DATA_KEY, addressMessage);
        resultReceiver.send(resultCode, bundle);
    }
}
