package com.example.TaxiGo.websocketConfig;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.TaxiGo.dataAccess.TaxiTypeRepository;
import com.example.TaxiGo.entities.TaxiInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component 
public class TaxiGoWebSocketHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TaxiTypeRepository taxiTypeRepository;

    @Override
    public void afterConnectionEstablished(WebSocketSession session)  throws Exception{
        sessions.add(session);
        getAllTaxiInfo(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    public void getAllTaxiInfo(WebSocketSession session) throws Exception {
        List<TaxiInfo> taxiInfos = taxiTypeRepository.findAll();
        String jsonTaxiInfos = new ObjectMapper().writeValueAsString(taxiInfos);
        session.sendMessage(new TextMessage(jsonTaxiInfos)); 
    }

     public void broadcastTaxiInfoList(List<TaxiInfo> taxiInfos) {
        try {
            String taxiInfoListJson = objectMapper.writeValueAsString(taxiInfos);
            for (WebSocketSession session : sessions) {
                session.sendMessage(new TextMessage(taxiInfoListJson));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
