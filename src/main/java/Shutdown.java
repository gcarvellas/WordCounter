import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Shutdown extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(ConfigParser.getPrefix() + "shutdown") && event.getAuthor().getId().equals(ConfigParser.getAuthorId())) {
            event.getChannel().sendMessage("```diff\n-Shutting down bot\n```").queue();
            event.getJDA().shutdown();
        }
    }
}