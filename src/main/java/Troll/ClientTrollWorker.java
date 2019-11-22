package Troll;

import Load.Load;
import com.github.theholywaffle.teamspeak3.TS3ApiAsync;

import java.util.Random;

public class ClientTrollWorker implements Runnable {
    private static final Random RANDOM = new Random();
    private static final String[] MESSAGES = {
            "Get off my server, damn kids!",
            "Tell me a joke!",
            "Hey Apple! Hey Apple! Apple! Hey Apple!",
            "A virus has been detected",
            "Move out of this channel or you will be banned in 15 seconds.",
            "Say something :3",
            "Boo!"
    };
    private final int clientId;
    private boolean cancelled;

    private ClientTrollWorker(int clientId) {
        this.clientId = clientId;
        cancelled = false;
    }
//https://github.com/TheHolyWaffle/TeamSpeak-3-Java-API/blob/master/example/TrollExample.java
    @Override
    public void run() {
        if (cancelled) return;

        final TS3ApiAsync asyncApi = Load.query.getAsyncApi();
        int choice = RANDOM.nextInt(4);
        switch (choice) {
            case 0:
                String pokeMessage = MESSAGES[RANDOM.nextInt(MESSAGES.length)];
                asyncApi.pokeClient(clientId, pokeMessage);
                break;
            case 1:
                String privateMessage = MESSAGES[RANDOM.nextInt(MESSAGES.length)];
                asyncApi.sendPrivateMessage(clientId, privateMessage);
                break;
            case 2:
                asyncApi.kickClientFromChannel(clientId);
                break;
        }
    }
}
