package com.haptik_test.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rats on 7/3/2016.
 */
public class Dialogs {


        public static ProgressDialog showLoading(Activity activity){
            ProgressDialog mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage(Constants.LOADING_MESSAGE);
            mProgressDialog.show();
            return mProgressDialog;
        }





    }
