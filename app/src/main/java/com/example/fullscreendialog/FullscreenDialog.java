package com.example.fullscreendialog;

import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class FullscreenDialog extends DialogFragment implements View.OnClickListener {

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
    String account , evenName, address, startDay, endDay, startTime, endTime;
    boolean isFullDay;
//   //Get view in layout
//    AutoCompleteTextView edtDropDown;
    private Callback callback;

    static FullscreenDialog newInstance(){
        return new FullscreenDialog();
    }
    public  interface  Callback{
        void onActionClick(String account, String evenName, String address,
                           String startDay, String endDay,
                           String startTime, String endTime,boolean isFullDay);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.Theme_FullscreenDialog);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Initial view in dynamic
        View view = inflater.inflate(R.layout.fulyscreen_dialog, container,false);
        ImageButton close = view.findViewById(R.id.fullscreen_dialog_close);
        TextView action = view.findViewById(R.id.fullscreen_dialog_action);
        //Initial value for information of event

//        ArrayAdapter<CharSequence>  emailAdapter= ArrayAdapter.createFromResource(getActivity(),
//                R.array.listAccount,R.layout.single_spinner_item );
////        edtDropDown= view.findViewById(R.id.accDropDown);
//        edtDropDown.setAdapter(emailAdapter);
       // TextView autoCompleteAccount= view.findViewById(R.id
        //Initial value for information of event
        account = new String();
        evenName = new String();
        address= new String();
        startDay= new String();
        endDay= new String();
        startTime= new String();
        endTime=new String();
        isFullDay= false;

        //Set On Click Event
        close.setOnClickListener(this);
        action.setOnClickListener(this);


    return  view;

    }    private  void getInfo()
    {

    }


    @Override
    public void onClick(View v) {
            int id= v.getId();
        MaterialAlertDialogBuilder builder= new MaterialAlertDialogBuilder(v.getContext());

            switch (id){
                case R.id.fullscreen_dialog_close:
                    this.dismiss();
                    break;
                case R.id.fullscreen_dialog_action:
                    getInfo();
                    callback.onActionClick(account , evenName, address, startDay, endDay, startTime, endTime, isFullDay);
                    this.dismiss();
                    break;
            }
    }
}
