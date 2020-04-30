package nepcflan.nvb;

import nepcflan.nvb.Handler.EventHandler;
import nepcflan.nvb.Handler.MessageHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Main {

    private static Config config;
    private static JDA api;
    public static void main(String[] arguments) throws Exception {
        ConfigReader reader = new ConfigReader();
        if (reader.existsConfig(true)) {
            reader.reloadConfig();
            config = reader.getConfig();
        } else {
            return;
        }

        //commandHandler.registerCommands(new CommandRegistry().getCommands());

        api = JDABuilder.createDefault(config.getToken()).build();
        api.addEventListener(new MessageHandler(config.getPrefix(), config.getAdmin()), new EventHandler());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            api.shutdownNow();
        }));
    }
}
