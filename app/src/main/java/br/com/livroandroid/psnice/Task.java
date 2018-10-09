package br.com.livroandroid.psnice;

/**
 * Created by livetouch on 14/09/18.
 */

public interface Task {
    void preExecute() throws Exception;

    void execute() throws Exception;

    void updateView();

    boolean onError(Throwable var1);

    boolean onErrorNetworkUnavailable();
}
