import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WordChecker extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");
        for (String word: ConfigParser.getWords()) {
            if (event.getMessage().getContentDisplay().toLowerCase().contains(word)){
                if (CooldownManager.hasUser(event.getAuthor().toString())){
                    event.getChannel().sendMessage("```diff\n- You are on a cooldown! Please wait...\n```").queue();
                } else {
                    ScoreManager.addUser(event.getAuthor().toString());
                    event.getChannel().sendMessage("```diff\n+ You got a point " + event.getAuthor().getName() + "! Your score is: " + ScoreManager.getScore(event.getAuthor().toString()) + "```").queue();
                    Thread thread = new Thread(){
                        public void run(){
                            try {
                                CooldownManager.addCooldown(event.getAuthor().toString());
                                Thread.sleep(5000);
                                CooldownManager.removeCooldown(event.getAuthor().toString());
                            } catch (Exception e){
                                event.getChannel().sendMessage("```diff\n- A fatal error has occurred!\n```").queue();
                            }
                        }
                    };
                    thread.start();

                }
            }
        }
    }
}
