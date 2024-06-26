package com.lus.dawm.services;

import com.lus.dawm.model.StatusMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class MessageService {
    private Map<String, String> messageMap;

    public MessageService() {
        messageMap = new HashMap<>();
        messageMap.put("ICE_EXIST", "Cette ICE déja exist");
        messageMap.put("EMPTY_DATA", "Veuillez vérifier vos data");
        messageMap.put("NOT_EXIST", "Veuillez vérifier vos data");
        messageMap.put("EXIST_DEJA", "Ctte element exist déja");

        messageMap.put("OK", "Success");
    }

    public StatusMessage message(String codeStatut) {
        String message = messageMap.getOrDefault(codeStatut, "--");
        return new StatusMessage(codeStatut, message);
    }
}
