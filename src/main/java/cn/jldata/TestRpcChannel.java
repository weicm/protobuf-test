package cn.jldata;

import com.google.protobuf.*;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by weicm on 2019/4/2.
 */
public class TestRpcChannel implements RpcChannel {
    private InputStream in;
    private OutputStream out;

    public TestRpcChannel(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    //该方法实现功能:
    //      1. 将请求方法和请求对象编码, 并通过网络socket io发送服务端
    //      2. socket io读取应答信息, 并解码成应答对象
    //      3. 调用客户端回调函数, 并将应答对象传递给客户端应答回调函数, 以便客户端执行获取结果后的操作需求
    @Override
    public void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message request, Message response, RpcCallback<Message> rpcCallback) {
        try {
            Entities.SearchRequest.newBuilder().mergeFrom(request).build().writeDelimitedTo(out);
            Entities.SearchResponse searchResponse = Entities.SearchResponse.parseDelimitedFrom(in);
            rpcCallback.run(searchResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
