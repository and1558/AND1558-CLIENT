package owo.aydendevy.DiscordRP;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRichPresence;
import net.arikia.dev.drpc.DiscordUser;
import net.arikia.dev.drpc.callbacks.ReadyCallback;
import net.arikia.dev.drpc.DiscordRPC;
import owo.aydendevy.DevyClient;
// Discord RPC Implementation for this client
public class DiscordRPCEvent {
    private boolean running = true;
    private long created = 0;
    // Initialize the DiscordRPC Service
    public void start(){
        this.created = System.currentTimeMillis();
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler(new ReadyCallback() {
            @Override
            public void apply(DiscordUser user) {
                DevyClient.getInstance().logger.info("Welcome " + user.username + "#" + user.discriminator);
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
    // Update the Rich Presence with a text when hovering the icon
    public void update(String first, String Second, String Hover){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(Second);
        b.setBigImage("large", Hover);
        b.setDetails(first);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
    // Update the Rich Presence without a text when hovering the icon
    public void update(String first, String Second){
        DiscordRichPresence.Builder b = new DiscordRichPresence.Builder(Second);
        b.setBigImage("large", "");
        b.setDetails(first);
        b.setStartTimestamps(created);
        DiscordRPC.discordUpdatePresence(b.build());
    }
}
