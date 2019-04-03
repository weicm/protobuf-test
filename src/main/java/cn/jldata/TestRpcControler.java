package cn.jldata;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * Created by weicm on 2019/4/2.
 */
public class TestRpcControler implements RpcController {
    @Override
    public void reset() {

    }

    @Override
    public boolean failed() {
        return false;
    }

    @Override
    public String errorText() {
        return null;
    }

    @Override
    public void startCancel() {

    }

    @Override
    public void setFailed(String s) {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void notifyOnCancel(RpcCallback<Object> rpcCallback) {

    }
}
