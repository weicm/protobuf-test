# Protobuf Rpc 实现类关系
- 生成类
    - Entities 请求应答对象
    - Service 服务

- Rpc实现步骤
    - 客户端和服务端RpcCallback区别
        - 客户端目的: 实现客户端接受到应答请求后的操作需求
        - 服务端目的: 将应答通过socket io发送给客户端
        
    - 客户端和服务端网络通信实现原理
        - 客户端: 通过Stub程序绑定具体的RpcChannel实现
        - 服务端: 有服务端自己实现
            
    - 客户端实现
        1. 实现RpcChannel, 并实现对应方法RpcChannel.callMethod
            ```
            //该方法实现功能: 
            //      1. 将请求方法和请求对象编码, 并通过网络socket io发送服务端
            //      2. socket io读取应答信息, 并解码成应答对象
            //      3. 调用客户端回调函数, 并将应答对象传递给客户端应答回调函数, 以执行客户端获取结果后的操作
            void callMethod(
                Descriptors.MethodDescriptor method, //被调用方法描述符
                RpcController controller, //rpc调用控制器
                Message request, //被调用方法请求参数对象
                Message responsePrototype, //被调用方法应答参数对象
                RpcCallback<Message> done //客户端应答回调函数
                );
            ```
        2. 自定义Rpc客户端类
            ```
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
            ```
    - 服务端实现
        1. 实现具体服务Service.SearchService.Interface, 并实现服务中具体的方法
            ```
            //主要功能:
            //      1. 执行具体计算逻辑
            //      2. 把计算逻辑的结果封装成应答对象
            //      3. 调用服务端回调函数, 并把应答对象传递给服务端回调函数, 以执行服务端自己将应答通过socket io发送给客户端的操作
            //例如
            @Override
                public void search(RpcController controller, Entities.SearchRequest request, RpcCallback<Entities.SearchResponse> done) {
                    //服务端具体逻辑实现
                    int result = request.getNum1() + request.getNum2();
                    System.out.println("计算结果: " + result);
                    //将逻辑计算结果封装成应答对象, 并传递给服务端应答回调函数
                    Entities.SearchResponse response = Entities.SearchResponse.newBuilder().setId(result).build();
                    done.run(response);
                }            
            ```
        2. 自定义Rpc服务端
            ```
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
            ```
    