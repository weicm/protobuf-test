package cn.jldata;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

/**
 * Created by weicm on 2019/4/3.
 */
//主要功能:
//      1. 执行具体计算逻辑
//      2. 把计算逻辑的结果封装成应答对象
//      3. 调用服务端回调函数, 并把应答对象传递给服务端回调函数, 以便服务端自己将应答通过socket io发送给客户端
public class SearchServiceImpl implements Service.SearchService.Interface {
    @Override
    public void search(RpcController controller, Entities.SearchRequest request, RpcCallback<Entities.SearchResponse> done) {
        //服务端具体逻辑实现
        int result = request.getNum1() + request.getNum2();
        System.out.println("计算结果: " + result);
        //将逻辑计算结果封装成应答对象, 并传递给服务端应答回调函数
        Entities.SearchResponse response = Entities.SearchResponse.newBuilder().setId(result).build();
        done.run(response);
    }
}
