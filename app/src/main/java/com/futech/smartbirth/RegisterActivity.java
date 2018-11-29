package com.futech.smartbirth;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    //private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText editTextNik, editTextNama, editTextTtl, editTextAlamat, editTextRtRw, editTextDusun, editTextTelp, editTextPassword;
    private View mProgressView;
    private View mLoginFormView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        editTextNik = (EditText) findViewById(R.id.edittext_nik);
        editTextNama = (EditText) findViewById(R.id.edittext_nama);
        editTextTtl = (EditText) findViewById(R.id.edittext_ttl);
        editTextAlamat = (EditText) findViewById(R.id.edittext_alamat);
        editTextRtRw = (EditText) findViewById(R.id.edittext_rtrw);
        editTextDusun = (EditText) findViewById(R.id.edittext_dusun);
        editTextTelp = (EditText) findViewById(R.id.edittext_telp);
        editTextPassword = (EditText) findViewById(R.id.edittext_rtrw);

        Button mSignupButton = (Button) findViewById(R.id.signup_button);
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();
            }
        });

        Button mLoginButton = (Button) findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                getApplicationContext().startActivity(i);
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }


    private void signup() {
        //if (mAuthTask != null) {
        //    return;
        //}

        // Reset errors.
        //mPassView.setError(null);

        // Store values at the time of the login attempt.
        String nik = editTextTelp.getText().toString();
      //  String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
/*
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
           // mPasswordView.setError(getString(R.string.error_invalid_password));
            //focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
      //  if (TextUtils.isEmpty(phone)) {
      //      mPhoneView.setError(getString(R.string.error_field_required));
      //      focusView = mPhoneView;
      //      cancel = true;
       // } else if (!isPhoneValid(phone)) {
       //     mPhoneView.setError(getString(R.string.error_invalid_phone));
        //    focusView = mPhoneView;
        //    cancel = true;
        }

        //if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
        //    focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            //mAuthTask = new UserLoginTask(phone, password);
            //mAuthTask.execute((Void) null);
            userLogin();
        }*/
    }

    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() > 9;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }






    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */

    /*
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPhone;
        private final String mPassword;

        UserLoginTask(String phone, String password) {
            mPhone = phone;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.


            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }




            // TODO: register the new account here.
            //return false;
        }



        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }

        */

    private void userLogin(){

        final String mPhone = editTextTelp.getText().toString();
       // final String mPassword = mPasswordView.getText().toString();

        String url = "https://www.tokosms.com/api/smartbirth/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        Log.d("echo",response.toString());
                        showProgress(false);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("phone", mPhone);
           //     params.put("pass", mPassword);

                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,1,1));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}