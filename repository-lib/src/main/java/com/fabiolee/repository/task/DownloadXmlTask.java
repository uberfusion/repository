package com.fabiolee.repository.task;

import android.os.AsyncTask;

import com.fabiolee.repository.Request;
import com.fabiolee.repository.Response;
import com.fabiolee.repository.adapter.network.NetworkAdapter;
import com.fabiolee.repository.object.xml.Callback;

public class DownloadXmlTask extends AsyncTask<Request, Void, Response[]> {
    private Callback<Response[]> mRemoteCallback;
    private NetworkAdapter mNetwork;

    public DownloadXmlTask(Callback<Response[]> mRemoteCallback, NetworkAdapter mNetwork) {
        super();
        this.mRemoteCallback = mRemoteCallback;
        this.mNetwork = mNetwork;
    }

    @Override
    protected Response[] doInBackground(Request... mRequests) {
        Response[] mResult = new Response[mRequests.length];
        for (int i = 0; i < mRequests.length; i++) {
            mResult[i] = new Response.Builder()
                    .request(mRequests[i])
                    .resXml(mNetwork.httpGetString(mRequests[i].mUrl))
                    .build();
        }
        return mResult;
    }

    @Override
    protected void onPostExecute(Response[] mResult) {
        mRemoteCallback.onResponse(mResult);
    }

    @Override
    protected void onPreExecute() {
    }
}
