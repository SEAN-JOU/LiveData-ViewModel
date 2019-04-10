package com.example.mainactivity.ui.jetpack;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainactivity.R;


public class JetpackFragment extends Fragment {

    private JetpackViewModel mViewModel;
    private Button changeButton,resumeButton;
    private TextView messageView;
    StockLiveData stockLiveData;

    public static JetpackFragment newInstance() {
        return new JetpackFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jetpack_fragment, container, false);
        messageView = view.findViewById(R.id.jack_message);
        changeButton = view.findViewById(R.id.jack_button);
        resumeButton =view.findViewById(R.id.rbtn);
        mViewModel = ViewModelProviders.of(this).get(JetpackViewModel.class);



        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.updateDataValue("456!");
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute(3);

        mViewModel = ViewModelProviders.of(this).get(JetpackViewModel.class);

        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //upate UI
                String string = messageView.getText().toString() + "\n" + s;
                messageView.setText(string);
            }
        };
        mViewModel.getData().observe(this, nameObserver);



    }

    @Override
    public void onStart() {
        super.onStart();




    }
    @Override
    public void onResume(){
        super.onResume();


        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"aaa",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>
    {
        Fragment context;
        MyAsyncTask(Fragment context){
            this.context=context;
        }
        @Override
        protected String doInBackground(Integer... integers) {
            int n = integers[0];
            int i;
            for(i=n;i>=0;i--)
            {
                try {
                    Thread.sleep(1000);
                    Log.d("Use-ViewModel",""+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }}
            return "OK";
        }
        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);


                    try{
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                messageView.setText("No-Use-ViewModel");
                            }
                        });}
                    catch (Exception e){}


            stockLiveData = new StockLiveData("Use-ViewModel",getContext());

            stockLiveData.observe(context, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    mViewModel.updateDataValue(s);
                }
            });
        }
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }}

}
