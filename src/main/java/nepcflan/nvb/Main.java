package nepcflan.nvb;

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

        api = JDABuilder.createDefault(config.getToken()).build();
        api.addEventListener(new MyListener(config.getPrefix(), config.getAdmin()));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            api.shutdownNow();
        }));
    }
}
