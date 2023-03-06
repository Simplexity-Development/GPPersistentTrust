package adhdmc.gppersistenttrust.listener;

import adhdmc.gppersistenttrust.config.Trusts;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.ClaimPermission;
import me.ryanhamshire.GriefPrevention.events.ClaimCreatedEvent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.UUID;

public class ClaimListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onClaimCreation(ClaimCreatedEvent event) {
        if (!(event.getCreator() instanceof Player player)) return;
        Claim claim = event.getClaim();
        if (claim.isAdminClaim()) return;
        ConfigurationSection trusts = Trusts.getInstance().getConfig().getConfigurationSection(player.getUniqueId().toString());
        if (trusts == null) return;
        ArrayList<String> trustedPlayers = new ArrayList<>();
        for (String trustedPlayer : trusts.getKeys(false)) {
            ConfigurationSection section = trusts.getConfigurationSection(trustedPlayer);
            if (section == null) continue;
            String type = section.getString("type", null);
            ClaimPermission permission = null;
            try { permission = ClaimPermission.valueOf(type); }
            catch (IllegalArgumentException e) { /* TODO: Implement Feedback */ }
            if (permission == null) continue;
            claim.setPermission(trustedPlayer, permission);
            trustedPlayers.add(Bukkit.getOfflinePlayer(UUID.fromString(trustedPlayer)).getName());
        }
        if (trustedPlayers.size() == 0) return;
        // TODO: Make configurable.
        StringBuilder feedback = new StringBuilder("Placeholder: Added the following players to your claim...\n");
        trustedPlayers.forEach((playerName) -> feedback.append(playerName).append(" "));
        player.sendMessage(feedback.toString());
    }

}
