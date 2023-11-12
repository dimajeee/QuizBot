package org.example;

import org.example.DialogStatus.DialogStatus;

public class DialogContext {
    private DialogStatus dialogStatus;

    public DialogStatus getDialogStatus() {
        return dialogStatus;
    }

    public void nextDialogContext() {
        dialogStatus.nextDialogStatus(this);
    }

    public void previousDialogStatus() {
        dialogStatus.previousDialogStatus(this);
    }

    public void setDialogStatus(DialogStatus dialogStatus) {
        this.dialogStatus = dialogStatus;
    }

    public String getStatusName() {
        return dialogStatus.getDialogStatus();
    }
}

