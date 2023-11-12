package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Response.ResponseClass;

public interface DialogStatus {
    String getDialogStatus();
    void nextDialogStatus(DialogContext dialogContext);
    void previousDialogStatus(DialogContext dialogContext);

}
