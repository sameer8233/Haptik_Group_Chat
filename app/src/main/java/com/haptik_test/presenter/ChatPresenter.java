package com.haptik_test.presenter;

import android.app.ProgressDialog;
import android.widget.Toast;
import com.haptik_test.model.ChatMessages;
import com.haptik_test.model.Message;
import com.haptik_test.model.RestClient;
import com.haptik_test.utils.ConnectionDetector;
import com.haptik_test.utils.Constants;
import com.haptik_test.utils.Dialogs;
import com.haptik_test.views.fragment.ChatFragment;
import java.util.ArrayList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Sameer on 7/2/2016.
 */
public class ChatPresenter {
    private ChatFragment view;
    private ArrayList<Message> items;
    private ConnectionDetector connectionDetector;

    // API calling
    private void getData() {

        if (connectionDetector.isConnectedToInternet()) {
            final ProgressDialog d = Dialogs.showLoading(view.getActivity());
            d.setCanceledOnTouchOutside(false);
            RestClient.get().getChat(new Callback<ChatMessages>() {
                @Override
                public void success(ChatMessages basePojo, Response response) {
                    if (basePojo != null) {
                        // setting response to list
                        items = (ArrayList<Message>) basePojo.getMessages();
                        publish(view);
                    } else {
                        Toast.makeText(view.getActivity(), "No Chat Messages", Toast.LENGTH_LONG).show();
                    }
                    d.dismiss();
                }
                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(view.getActivity(), Constants.SOMETHING_WRONG, Toast.LENGTH_LONG).show();
                    d.dismiss();
                }

            });
        } else {
            Toast.makeText(view.getActivity(), Constants.CHECK_CONNECTION_FALSE, Toast.LENGTH_LONG).show();

        }
    }

    public void onTakeView(ChatFragment view) {
        this.view = view;
        connectionDetector = new ConnectionDetector(view.getActivity());
        // API Call
        getData();
    }

    // sending data to chat fragment
    public void publish(ChatFragment view) {
        if (view != null) {
            if (items != null) {
                view.onItemNext(items);
            }
        }
    }


}
