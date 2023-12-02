package org.example.DialogStatus;

import org.example.DialogContext;
import org.example.Response;

public interface DialogStatus {
    String getDialogStatus();
    Response nextDialogStatus(DialogContext dialogContext, String req);
    void previousDialogStatus(DialogContext dialogContext);

}
