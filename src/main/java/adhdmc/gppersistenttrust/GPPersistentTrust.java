package adhdmc.gppersistenttrust;

import org.bukkit.plugin.java.JavaPlugin;

public final class GPPersistentTrust extends JavaPlugin {

    private static GPPersistentTrust instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static GPPersistentTrust getInstance() { return instance; }
}
