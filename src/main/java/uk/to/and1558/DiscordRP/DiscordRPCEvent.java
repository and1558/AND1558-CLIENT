package uk.to.and1558.DiscordRP;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.arikia.dev.drpc.DiscordRPC;
import uk.to.and1558.and1558;

public class DiscordRPCEvent {
    private boolean running = true;
    private long created = 0;
    public void start(){
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                and1558.getInstance().logger.info("Welcome " + user.username + "#" + user.discriminator);
            }
        }).build();
        DiscordRPC.discordInitialize("899878059858083913", handlers, true);
        new Thread("Discord RPC Event Thread"){
            @Override
            public void run() {
                while(running){
                    DiscordRPC.discordRunCallbacks();
                }
            }
        }.start();
    }

    public void shutdown(){
        running = false;
        // Does not shutdown discord, only the Rich Presence
        DiscordRPC.discordShutdown();
    }
    public void update(String first, String Second, String Hover){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(Second);
        b.setBigImage("large", Hover);
        b.setDetails(first);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
    public void update(String first, String Second){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(Second);
        b.setBigImage("large", "");
        b.setDetails(first);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
