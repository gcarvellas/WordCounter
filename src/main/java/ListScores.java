import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bson.Document;

import java.util.*;

public class ListScores extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "listScores")) {
            String prefix = ConfigParser.getPrefix();
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle("Scores:");
            info.setDescription(scoresAsString());
            info.setColor(0xa88785);
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }

    private String scoresAsString(){
        String result="";

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();

        ScoreManager.getScores().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> entry: sortedMap.entrySet()){
            result+= entry.getKey() + ": " + entry.getValue() + "\n";
        }

        return result;
    }


}
