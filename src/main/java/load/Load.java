package load;

import Events.Event;
import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import io.github.cdimascio.dotenv.Dotenv;

public class Load {
    private static Dotenv dotenv = Dotenv.load();

    public static final TS3Config config = new TS3Config();
    public static final TS3Query query = new TS3Query(config);
    public static final TS3Api api = query.getApi();

    public static void main(String[] args) {
        config.setHost(dotenv.get("TS_IP"));
        config.setFloodRate(TS3Query.FloodRate.UNLIMITED);
        query.connect();
        api.login(dotenv.get("BOT_USER"), dotenv.get("BOT_PASS"));
        api.selectVirtualServerById(1);
        api.setNickname(dotenv.get("NICKNAME"));
        Event.loadEvents();
        System.out.println("Bot is ready!");
    }
}
