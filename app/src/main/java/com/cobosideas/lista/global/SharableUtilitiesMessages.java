package com.cobosideas.lista.global;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.snackbar.Snackbar;

public class SharableUtilitiesMessages {
    private  Context context;
    private  boolean isDebuggable = false;
    /**
     * Utilities allow to use some functions globally
     * @param contextToUse setting up context
     */
    public SharableUtilitiesMessages(Context contextToUse) {
        this.context = contextToUse;
        isDebuggable = isDebuggable(context);
    }

    /**
     * Show short message on Toast
     *
     * @param message to show
     */
    public void message(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show long message on Toast
     * @param longMessage if long time to display or short
     * @param message to show
     */
    public void message(boolean longMessage, String message) {
        if (longMessage) Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        else Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show SnackBar Message
     * @param viewToUse to show
     * @param messageToShow to show
     */
    public void message(View viewToUse, String messageToShow) {
        Snackbar.make(viewToUse, messageToShow, Snackbar.LENGTH_LONG)
                .setAction("Action change", null).show();
    }

    /**
     * Show dialog message
     *
     * @param titleAlertDialog to show
     * @param messageAlertDialog to show
     * @param buttonAlertDialog to show on the button
     */
    public void alertDialogMessage(String titleAlertDialog,
                                   String messageAlertDialog,
                                   String buttonAlertDialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleAlertDialog);
        builder.setMessage(messageAlertDialog);
        builder.setNeutralButton(buttonAlertDialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    private boolean isDebuggable(Context ctx)
    {
        boolean debuggable = false;

        PackageManager pm = ctx.getPackageManager();
        try
        {
            ApplicationInfo appInformation = pm.getApplicationInfo(ctx.getPackageName(), 0);
            debuggable = (0 != (appInformation.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        }
        catch(PackageManager.NameNotFoundException e)
        {
            /*debuggable variable will remain false*/
        }

        return debuggable;
    }
}
