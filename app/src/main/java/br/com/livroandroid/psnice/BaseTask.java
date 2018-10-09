package br.com.livroandroid.psnice;


import java.io.InterruptedIOException;

/**
 * Created by livetouch on 14/09/18.
 */

public abstract class BaseTask implements Task {
    public BaseTask() {
    }

    public void preExecute() throws Exception {
    }

    public abstract void execute() throws Exception;

    public abstract void updateView();

    public boolean onError(Throwable e) {
        return e instanceof InterruptedIOException;
    }

    public boolean onErrorNetworkUnavailable() {
        return false;
    }
}
