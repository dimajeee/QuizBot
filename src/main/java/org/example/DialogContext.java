package org.example;

import org.example.DialogStatus.DialogStatus;

public class DialogContext {
    private DialogStatus dialogStatus;

    public DialogStatus getDialogStatus() {
        return dialogStatus;
    }

    public Response nextDialogContext(String[] req) {
        return dialogStatus.nextDialogStatus(this, req);
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

