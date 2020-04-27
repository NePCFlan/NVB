package nepcflan.nvb;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Slf4j
public class MyListener extends ListenerAdapter {
    private final String prefix;
    private final long admin;

    public MyListener(String prefix, long admin) {
        this.prefix = prefix;
        this.admin = admin;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor() == event.getJDA().getSelfUser() ||
                event.getAuthor().isBot() ||
                event.isWebhookMessage() ||
                !event.getTextChannel().canTalk())
            return;

        String raw = event.getMessage().getContentRaw();
        String input;
        if (raw.startsWith(prefix)) {
            input = raw.substring(prefix.length()).trim();
        } else if (!event.getMessage().getMentions().isEmpty()) {
            if (!event.getMessage().isMentioned(event.getJDA().getSelfUser()))
                return;
            input = raw.substring(event.getJDA().getSelfUser().getAsMention().length() + 1).trim();
        } else {
            return;
        }

        if (input.isEmpty())
            return;

        String[] args = input.split("\\p{javaSpaceChar}+");
        if (args.length == 0)
            return;
        String commandTrigger = args[0];

        switch (commandTrigger) {
            case "clean":
                event.getChannel().getHistory().retrievePast(100).queue(messages -> messages.forEach(message -> {
                    List<Member> members = event.getMessage().getMentionedMembers();
                    if (members.contains(message.getMember()))
                        message.delete().submit();
                }));
                break;

            case "neko":
                event.getChannel().sendMessage("猫ではありません！").queue();
                break;

            case "shutdown":
                if (event.getAuthor().getIdLong() == admin) {
                    event.getChannel().sendMessage("プログラムを終了します。").queue();
                    log.info("プログラムを終了します。");
                    Runtime.getRuntime().exit(0);
                } else {
                    event.getChannel().sendMessage("Adminではありません！").queue();
                }
                break;

            default:
                event.getChannel().sendMessage("このコマンドは登録されていません！").queue();
                break;
        }
    }
}