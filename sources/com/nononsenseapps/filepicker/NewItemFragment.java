package com.nononsenseapps.filepicker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public abstract class NewItemFragment extends DialogFragment {
    /* access modifiers changed from: private */
    public OnNewFolderListener listener = null;

    public interface OnNewFolderListener {
        void onNewFolder(String str);
    }

    /* access modifiers changed from: protected */
    public abstract boolean validateName(String str);

    public void setListener(OnNewFolderListener onNewFolderListener) {
        this.listener = onNewFolderListener;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.nnf_dialog_folder_name).setTitle(R.string.nnf_new_folder).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton(17039370, (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                final AlertDialog alertDialog = (AlertDialog) dialogInterface;
                final EditText editText = (EditText) alertDialog.findViewById(R.id.edit_text);
                if (editText != null) {
                    alertDialog.getButton(-2).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            alertDialog.cancel();
                        }
                    });
                    final Button button = alertDialog.getButton(-1);
                    button.setEnabled(false);
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            String obj = editText.getText().toString();
                            if (NewItemFragment.this.validateName(obj)) {
                                if (NewItemFragment.this.listener != null) {
                                    NewItemFragment.this.listener.onNewFolder(obj);
                                }
                                alertDialog.dismiss();
                            }
                        }
                    });
                    editText.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void afterTextChanged(Editable editable) {
                            button.setEnabled(NewItemFragment.this.validateName(editable.toString()));
                        }
                    });
                    return;
                }
                throw new NullPointerException("Could not find an edit text in the dialog");
            }
        });
        return create;
    }
}
