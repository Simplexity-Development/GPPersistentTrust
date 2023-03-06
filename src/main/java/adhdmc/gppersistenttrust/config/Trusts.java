package adhdmc.gppersistenttrust.config;

import adhdmc.gppersistenttrust.GPPersistentTrust;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Trusts {

    private static Trusts instance;

    private final String fileName = "trusts.yml";
    private final File dataFile = new File(GPPersistentTrust.getInstance().getDataFolder(), fileName);
    private final FileConfiguration config = new YamlConfiguration();

    private Trusts() {
        try { dataFile.createNewFile(); }
        catch (IOException e) { e.printStackTrace(); }
        reload();
    }

    public static Trusts getInstance() {
        if (instance == null) instance = new Trusts();
        return instance;
    }

    public FileConfiguration getConfig() { return config; }

    public void reload() {
        try { config.load(dataFile); }
        catch (IOException | InvalidConfigurationException e) { e.printStackTrace(); }
    }

}
