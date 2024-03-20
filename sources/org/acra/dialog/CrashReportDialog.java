package org.acra.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import org.acra.ACRA;
import org.acra.config.ConfigUtils;
import org.acra.config.DialogConfiguration;
import org.acra.prefs.SharedPreferencesFactory;

public class CrashReportDialog extends BaseCrashReportDialog implements DialogInterface.OnClickListener {
    private static final int PADDING = 10;
    private static final String STATE_COMMENT = "comment";
    private static final String STATE_EMAIL = "email";
    private DialogConfiguration dialogConfiguration;
    private AlertDialog mDialog;
    private LinearLayout scrollable;
    private SharedPreferencesFactory sharedPreferencesFactory;
    private EditText userCommentView;
    private EditText userEmailView;

    /* access modifiers changed from: protected */
    public void init(Bundle bundle) {
        this.scrollable = new LinearLayout(this);
        this.scrollable.setOrientation(1);
        this.sharedPreferencesFactory = new SharedPreferencesFactory(getApplicationContext(), getConfig());
        this.dialogConfiguration = (DialogConfiguration) ConfigUtils.getPluginConfiguration(getConfig(), DialogConfiguration.class);
        int resTheme = this.dialogConfiguration.resTheme();
        if (resTheme != 0) {
            setTheme(resTheme);
        }
        buildAndShowDialog(bundle);
    }

    /* access modifiers changed from: protected */
    public void buildAndShowDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String title = this.dialogConfiguration.title();
        if (title != null) {
            builder.setTitle(title);
        }
        int resIcon = this.dialogConfiguration.resIcon();
        if (resIcon != 0) {
            builder.setIcon(resIcon);
        }
        builder.setView(buildCustomView(bundle)).setPositiveButton(this.dialogConfiguration.positiveButtonText(), this).setNegativeButton(this.dialogConfiguration.negativeButtonText(), this);
        this.mDialog = builder.create();
        this.mDialog.setCanceledOnTouchOutside(false);
        this.mDialog.show();
    }

    /* access modifiers changed from: protected */
    public View buildCustomView(Bundle bundle) {
        ScrollView scrollView = new ScrollView(this);
        scrollView.setPadding(10, 10, 10, 10);
        scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.addView(this.scrollable);
        addViewToDialog(getMainView());
        View commentLabel = getCommentLabel();
        String str = null;
        if (commentLabel != null) {
            commentLabel.setPadding(commentLabel.getPaddingLeft(), 10, commentLabel.getPaddingRight(), commentLabel.getPaddingBottom());
            addViewToDialog(commentLabel);
            this.userCommentView = getCommentPrompt(bundle != null ? bundle.getString(STATE_COMMENT) : null);
            addViewToDialog(this.userCommentView);
        }
        View emailLabel = getEmailLabel();
        if (emailLabel != null) {
            emailLabel.setPadding(emailLabel.getPaddingLeft(), 10, emailLabel.getPaddingRight(), emailLabel.getPaddingBottom());
            addViewToDialog(emailLabel);
            if (bundle != null) {
                str = bundle.getString("email");
            }
            this.userEmailView = getEmailPrompt(str);
            addViewToDialog(this.userEmailView);
        }
        return scrollView;
    }

    /* access modifiers changed from: protected */
    public final void addViewToDialog(View view) {
        this.scrollable.addView(view);
    }

    /* access modifiers changed from: protected */
    public View getMainView() {
        TextView textView = new TextView(this);
        String text = this.dialogConfiguration.text();
        if (text != null) {
            textView.setText(text);
        }
        return textView;
    }

    /* access modifiers changed from: protected */
    public View getCommentLabel() {
        String commentPrompt = this.dialogConfiguration.commentPrompt();
        if (commentPrompt == null) {
            return null;
        }
        TextView textView = new TextView(this);
        textView.setText(commentPrompt);
        return textView;
    }

    /* access modifiers changed from: protected */
    public EditText getCommentPrompt(CharSequence charSequence) {
        EditText editText = new EditText(this);
        editText.setLines(2);
        if (charSequence != null) {
            editText.setText(charSequence);
        }
        return editText;
    }

    /* access modifiers changed from: protected */
    public View getEmailLabel() {
        String emailPrompt = this.dialogConfiguration.emailPrompt();
        if (emailPrompt == null) {
            return null;
        }
        TextView textView = new TextView(this);
        textView.setText(emailPrompt);
        return textView;
    }

    /* access modifiers changed from: protected */
    public EditText getEmailPrompt(CharSequence charSequence) {
        EditText editText = new EditText(this);
        editText.setSingleLine();
        editText.setInputType(33);
        if (charSequence != null) {
            editText.setText(charSequence);
        } else {
            editText.setText(this.sharedPreferencesFactory.create().getString(ACRA.PREF_USER_EMAIL_ADDRESS, ""));
        }
        return editText;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str;
        if (i == -1) {
            EditText editText = this.userCommentView;
            String obj = editText != null ? editText.getText().toString() : "";
            SharedPreferences create = this.sharedPreferencesFactory.create();
            EditText editText2 = this.userEmailView;
            if (editText2 != null) {
                str = editText2.getText().toString();
                create.edit().putString(ACRA.PREF_USER_EMAIL_ADDRESS, str).apply();
            } else {
                str = create.getString(ACRA.PREF_USER_EMAIL_ADDRESS, "");
            }
            sendCrash(obj, str);
        } else {
            cancelReports();
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        EditText editText = this.userCommentView;
        if (!(editText == null || editText.getText() == null)) {
            bundle.putString(STATE_COMMENT, this.userCommentView.getText().toString());
        }
        EditText editText2 = this.userEmailView;
        if (editText2 != null && editText2.getText() != null) {
            bundle.putString("email", this.userEmailView.getText().toString());
        }
    }

    /* access modifiers changed from: protected */
    public AlertDialog getDialog() {
        return this.mDialog;
    }
}
