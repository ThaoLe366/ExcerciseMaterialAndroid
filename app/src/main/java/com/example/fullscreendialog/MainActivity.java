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
    //region Initial Value and Component
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
    //endregion

    protected void onCreate(Bundle savedInstanceState) {
        //region Inheritance
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        //endregion
        //region Initial global value
        //Dialog using Material
        materialAlertDialogBuilder=new  MaterialAlertDialogBuilder(this);
        //Get value from Dialog
        infoDialog= new StringBuffer();
        //View show information from dialog
        showContent= findViewById(id.tvGetInfo);
       //Button show Material Customise Dialog
        btnCusMateDialog= findViewById(id.btnInfoMaterDialog);
        //endregion
        //region Event Material Customise Dialog
        //Create event onClick for Material Customise Dialog
        btnCusMateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert file .xml to code java
                final LayoutInflater inflater =LayoutInflater.from(MainActivity.this);
                customAlertDialogView=inflater.inflate(info_mate_dialog, null,false);
                //Setup for Material Customise (using directly)
                launchCustomerAlertDialog();
            }
        });
        //endregion
    }

    //region Call Fullscreen Dialog
    /**
     * Show Fullscreen Dialog
     * @param view
     */
    public void showCustomiseDialog(View view) {
        DialogFragment dialog= new FullscreenDialog();
        ((FullscreenDialog) dialog).setCallback(new FullscreenDialog.Callback() {
            @Override
            public void onActionClick(String account, String evenName, String address, String startDay, String endDay, String startTime, String endTime, boolean isFullDay) {
                //Handle method
            }
        });
        dialog.show(getSupportFragmentManager(),"tag");
    }
    //endregion

    //region Call Customise Dialog
    /**
     * Event Show raw customise dialog (without Material design)
     */
    public void sendInfoDialog(View view) {
            openDialog();
    }

    /**
     * Main function Call customise Dialog
     */
    public  void openDialog(){
        GetInfoDialog_normal info= new GetInfoDialog_normal();
        info.show(getSupportFragmentManager(),"example dialog");
    }

    /**
     * Implement method to transfer data from Fragment Dialog
     * @param username
     * @param phone
     * @param address
     */
    @Override
    public void applyTexts(String username, String phone, String address) {
        //  Add
        infoDialog= new StringBuffer();
        infoDialog.append("User Name: ").append(username)
                .append(" Phone: ").append(phone)
                  .append(", Address: ").append(address);

        showContent.setText(infoDialog);
    }
    //endregion


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //region Call Material Customise Dialog
    //Event Click to show customize dialog use Material
    public void customizeMateDialog(View view) {
     //   customAlertDialogView=this.getLayoutInflater().inflate(R.layout.info_mate_dialog,null);
//            customAlertDialogView= LayoutInflater.from(this)
//                    .inflate(R.layout.info_mate_dialog,null);
        final LayoutInflater inflater =this.getLayoutInflater();
        customAlertDialogView=inflater.inflate(info_mate_dialog, null);
        launchCustomerAlertDialog();
    }

    /**
     * Setup for Material Customise Dialog (using Directly)
     */
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
                      //  Get Information
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
    //endregion
    }
