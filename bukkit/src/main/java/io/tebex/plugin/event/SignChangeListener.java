package io.tebex.plugin.event;

import io.tebex.plugin.TebexPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private final TebexPlugin plugin;

    public SignChangeListener(TebexPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getLine(0) == null &&
            !event.getLine(0).equalsIgnoreCase("[tebex]")) return;

    }
}