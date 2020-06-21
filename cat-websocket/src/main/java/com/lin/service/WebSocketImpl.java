package com.lin.service;

import com.alibaba.fastjson.JSON;
import com.lin.model.SocketInfo;
import com.lin.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author lzr
 * @date 2020-04-20 20:13:10
 */
@ServerEndpoint(value = "/websocket/{username}")
@Component
public class WebSocketImpl {
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketImpl> webSocketSet = new CopyOnWriteArraySet<>();
    //用session作为key，保存用户信息
    private static Map<Session, SocketInfo> connectmap = new HashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    //  这里使用静态，让 service 属于类
    private static AuthUserService authUserService;

    // 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(AuthUserService authUserService) {
        WebSocketImpl.authUserService = authUserService;
    }


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        this.session = session;
        SocketInfo socketInfo = new SocketInfo(session.getId(),username);
        connectmap.put(session,socketInfo);
        //加入set中
        webSocketSet.add(this);
        System.out.println(username+" 上线了！当前在线人数为" + webSocketSet.size());
        MessageVo messageVo = new MessageVo();
        String message = username+" 上线了！当前在线人数为" + webSocketSet.size();
        messageVo.setMessage(message);
        //次要消息
        messageVo.setType(0);
        String messageObj = JSON.toJSONString(messageVo);
        System.out.println(messageObj);
        //群发消息，告诉每一位
        broadcast(messageObj);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        String username=connectmap.get(session).getUsername();
        connectmap.remove(session);
        //从set中删除
        webSocketSet.remove(this);
        MessageVo messageVo = new MessageVo();
        String message = username+" 下线了！当前在线人数为" + webSocketSet.size();
        messageVo.setMessage(message);
        //次要消息
        messageVo.setType(0);
        String messageObj = JSON.toJSONString(messageVo);
        System.out.println(username+" 下线了！当前在线人数为" + webSocketSet.size());

        //群发消息，告诉每一位
        broadcast(messageObj);
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        String username=connectmap.get(session).getUsername();

        //获取用户信息
        MessageVo messageVo = authUserService.messageInfo(username);
        messageVo.setMessage(message);
        //主要消息
        messageVo.setType(1);
        String messageObj = JSON.toJSONString(messageVo);

        //异步发送信息
        broadcast(messageObj);
    }
    /**
     * 发生错误时调用
     *
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
    /**
     * 群发自定义消息
     * */
    public  void broadcast(String message){
        for (WebSocketImpl item : webSocketSet) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            //this.session.getBasicRemote().sendText(message);
            item.session.getAsyncRemote().sendText(message);//异步发送消息.
        }
    }
}