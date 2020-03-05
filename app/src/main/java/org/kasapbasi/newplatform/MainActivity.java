package org.kasapbasi.newplatform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1461: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"İZİN VERİLDİ " ,Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"İZİN REDDEDİLDİ " ,Toast.LENGTH_LONG).show();

                    // permission denied
                    // Disable the functionality that depends on this permission.
                }
                return;
            }

            // other 'case' statements for other permssions
        }

    }

    public void myClick(View v){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR);
       //-1 gelirse iznim yok anlamındadır.

        Toast.makeText(this,"İZİN DURUMU " + permissionCheck,Toast.LENGTH_LONG).show();
        if (!(permissionCheck == PackageManager.PERMISSION_GRANTED)) {
            // User may have declined earlier, ask Android if we should show him a reason
            if (shouldShowRequestPermissionRationale (Manifest.permission.WRITE_CALENDAR)) {
                // show an explanation to the user
                // Good practise: don't block thread after the user sees the explanation, try again to request the permission.
                Toast.makeText(this,"TAKVİME YAZMAK İÇİN İZİN GEREKLİDİR " + permissionCheck,Toast.LENGTH_LONG).show();

                requestPermissions( new String[]{Manifest.permission.WRITE_CALENDAR}, 1461);

            } else {
                // request the permission.
                // CALLBACK_NUMBER is a integer constants
                requestPermissions( new String[]{Manifest.permission.WRITE_CALENDAR}, 1461);
                // The callback method gets the result of the request.
            }
        } else {
            Toast.makeText(this,"TAKVİME YAZMAK İÇİN İZİN VAR" + permissionCheck,Toast.LENGTH_LONG).show();

        }
    }
}
