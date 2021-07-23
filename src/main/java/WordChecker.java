import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WordChecker extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        for (String word: ConfigParser.getWords()) {
            if (event.getMessage().getContentDisplay().toLowerCase().contains(word)){
                ScoreManager.addUser(event.getAuthor().toString());
                event.getChannel().sendMessage("```diff\n+ You got a point " + event.getAuthor().getName() + "! Your score is: " + ScoreManager.getScore(event.getAuthor().toString()) + "```").queue();
            }
        }
    }
}
