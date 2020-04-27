package nepcflan.nvb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty("discordToken")
    private String token;
    @JsonProperty("prefix")
    private String prefix;
    @JsonProperty("admin")
    private long admin;

    public String getToken() {
        return token;
    }

    public String getPrefix() {
        return prefix;
    }

    public long getAdmin() {
        return admin;
    }
}
