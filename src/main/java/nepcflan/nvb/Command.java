package nepcflan.nvb;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Command extends ListenerAdapter {
    private final String prefix;

    public Command(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void GuildMemberJoinEvent​(join, long responseNumber, Member ) {
        if (GuildMemberJoinEvent)
    }
}
