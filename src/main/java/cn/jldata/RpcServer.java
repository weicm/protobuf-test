package cn.jldata;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by weicm on 2019/4/3.
 */
public class RpcServer {
    public static final int port = RpcClient.port;

    private static int serviceCount = 0;
    public static void main(String[] args) {
        //创建监听端口
        try (ServerSocket socket = new ServerSocket(port);) {

            //通过Service.SearchService.newReflectiveService方法包装具体服务实现, 以获取具体服务
            com.google.protobuf.Service service = Service.SearchService.newReflectiveService(new SearchServiceImpl());
            while (serviceCount++ < 5) {
                //监听客户端链接
                Socket accept = socket.accept();

                System.out.println("Service count : " + serviceCount);

                //服务端获取输入流和输出流
                final InputStream in = accept.getInputStream();
                final OutputStream out = accept.getOutputStream();

                //从输入流中读取请求新信息
                Entities.SearchRequest searchRequest = Entities.SearchRequest.parseDelimitedFrom(in);
                System.out.println(String.format("收到请求: %d + %d", searchRequest.getNum1(), searchRequest.getNum1()));

                //通过service.getDescriptorForType().findMethodByName方法, 根据请求获取被调用服务的具体方法描述符
                Descriptors.MethodDescriptor method = service.getDescriptorForType().getMethods().get(0);

                //调用具体服务的具体的方法
                service.callMethod(method, new TestRpcControler(), searchRequest, new RpcCallback<Message>() {
                    @Override
                    public void run(Message response) {
                        try {
                            //将应答信息通过socket io写会给客户端(response参数将由SearchServiceImpl参数传递过来)
                            Entities.SearchResponse.newBuilder().mergeFrom(response).build().writeDelimitedTo(out);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
