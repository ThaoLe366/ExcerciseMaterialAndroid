package com.example.fullscreendialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GetInfoDialog_normal extends AppCompatDialogFragment {

    private EditText edtUserName;
    private EditText edtPhone;
    private EditText edtAddress;
    private ExampleDialogListener info;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.send_info_dialog,null);

        builder.setView(view)
                .setTitle("Details")
                .setMessage("Enter your basic details")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //GetValue
                        String userName = edtUserName.getText().toString();
                        String phone= edtPhone.getText().toString();
                        String address= edtAddress.getText().toString();
                        //Call method in Interface, return value to previous
                        info.applyTexts(userName, phone,address);

                       dialog.dismiss();
                    }
                });
        edtPhone= view.findViewById(R.id.sendDiaName);
        edtUserName= view.findViewById(R.id.sendDiaPhone);
        edtAddress= view.findViewById(R.id.sendDiaAddress);


        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            info= (ExampleDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must ...");
        }
    }
    public interface ExampleDialogListener {
        void applyTexts(String username, String phone, String address);
    }
}
