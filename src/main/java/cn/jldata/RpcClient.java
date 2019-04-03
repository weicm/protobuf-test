package cn.jldata;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;

import java.net.Socket;

/**
 * Hello world!
 *
 */
public class RpcClient {
    public static final String host = "localhost";
    public static final int port = 9999;


    public static void main(String[] args) {
        //创建Socket链接
        try (Socket socket = new Socket(host, port)) {

            //使用socket io创建RpcChannel
            RpcChannel channel = new TestRpcChannel(socket.getInputStream(), socket.getOutputStream());

            //调用Service.SearchService.newStub(channel)创建具体服务
            Service.SearchService.Interface service = Service.SearchService.newStub(channel);

            //调用具体服务的方法
            service.search(
                    //创建控制器
                    new TestRpcControler(),
                    //创建请求参数
                    Entities.SearchRequest.newBuilder().setNum1(1).setNum2(2).build(),
                    //创建应答回调函数, 在RpcChannel得到应答后执行该方法
                    //RpcCallback作用: 实现客户端获取应答后的操作
                    new RpcCallback<Entities.SearchResponse>() {
                        @Override
                        public void run(Entities.SearchResponse response) {
                            System.out.println("结果: " + response.getId());
                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
