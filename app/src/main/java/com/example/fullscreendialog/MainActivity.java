package com.example.fullscreendialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import static com.example.fullscreendialog.R.*;
import static com.example.fullscreendialog.R.layout.info_mate_dialog;

public class MainActivity extends AppCompatActivity implements GetInfoDialog_normal.ExampleDialogListener {
    //Get info of dialog
  private   StringBuffer infoDialog;
    private TextView showContent;
    // Implement Fragment
    private TextView textViewUsername;
    private TextView textViewPassword;
    private Button button;
   private  Button btnCusMateDialog;
    // For customize dialog button
    private MaterialAlertDialogBuilder materialAlertDialogBuilder;
    private View  customAlertDialogView;
    private TextInputLayout nameTextField, phoneNumberTextField,addressTextField;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        //New dialog
        materialAlertDialogBuilder=new  MaterialAlertDialogBuilder(this);
        infoDialog= new StringBuffer();
        showContent= findViewById(id.tvGetInfo);
        //Set Dynamic Event
        btnCusMateDialog= findViewById(id.btnInfoMaterDialog);
        btnCusMateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert file .xml to code java
                final LayoutInflater inflater =LayoutInflater.from(MainActivity.this);
                customAlertDialogView=inflater.inflate(info_mate_dialog, null,false);
                //Launch Customize
                launchCustomerAlertDialog();
            }
        });
    }

    /**
     * Show Fullscreen Dialog
     * @param view
     */
    public void showCustomiseDialog(View view) {
        DialogFragment dialog= new FullscreenDialog();
        ((FullscreenDialog) dialog).setCallback(new FullscreenDialog.Callback() {
            @Override
            public void onActionClick(String account, String evenName, String address, String startDay, String endDay, String startTime, String endTime, boolean isFullDay) {

            }


        });
        dialog.show(getSupportFragmentManager(),"tag");
    }

    /**
     * Event Show raw customise dialog (without Material design)
     */
    public void sendInfoDialog(View view) {
            openDialog();
    }
    public  void openDialog(){
        GetInfoDialog_normal info= new GetInfoDialog_normal();
        info.show(getSupportFragmentManager(),"example dialog");
    }


    @Override
    public void applyTexts(String username, String phone, String address) {
        //  Add
        infoDialog= new StringBuffer();
        infoDialog.append("User Name: ").append(username)
                .append(" Phone: ").append(phone)
                  .append(", Address: ").append(address);

        showContent.setText(infoDialog);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //Event Click to show customize dialog use Material
    public void customizeMateDialog(View view) {
     //   customAlertDialogView=this.getLayoutInflater().inflate(R.layout.info_mate_dialog,null);
//            customAlertDialogView= LayoutInflater.from(this)
//                    .inflate(R.layout.info_mate_dialog,null);

        final LayoutInflater inflater =this.getLayoutInflater();
        customAlertDialogView=inflater.inflate(info_mate_dialog, null);
        launchCustomerAlertDialog();

    }

    private void launchCustomerAlertDialog() {
        //Get info of view in dialog
        nameTextField= customAlertDialogView.findViewById(id.name_text_field);
        phoneNumberTextField= customAlertDialogView.findViewById(id.phone_number_text_field);
        addressTextField= customAlertDialogView.findViewById(id.address_text_field);
        //Set View for Material Customise Dialog
        materialAlertDialogBuilder.setView(customAlertDialogView)
                .setTitle("Details")

                .setMessage("Enter your basic details")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameTextField.getEditText().getText().toString();
                        String phoneNumber = phoneNumberTextField.getEditText().getText().toString();
                        String address = addressTextField.getEditText().getText().toString();

                      //  Add
                        infoDialog= new StringBuffer();
                        infoDialog.append("Name: ").append(name)
                                .append(", phone: ").append(phoneNumber)
                                .append(", Address: ").append(address);
                        showContent.setText(infoDialog);
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Operation cancelled!", Toast.LENGTH_LONG);
                        dialog.dismiss();
                    }
                })
                .show();

    }
    }
