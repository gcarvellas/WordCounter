import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
    public static void main(String[] args) throws LoginException{
        ConfigParser.loadProperties();

        JDABuilder jda = JDABuilder.createDefault(ConfigParser.getBotToken());
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.watching(ConfigParser.getPrefix() + "listScores"));

        Mongo.start();

        jda.addEventListeners(new WordChecker());
        jda.addEventListeners(new Shutdown());
        jda.addEventListeners(new ListScores());
        jda.addEventListeners(new Mongo());

        jda.setEnableShutdownHook(true);
        jda.build();
    }
}
