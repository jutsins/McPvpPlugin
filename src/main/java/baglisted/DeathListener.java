package baglisted;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

//TODO implement a penalty for dying by fall damage\
public class DeathListener implements Listener {
    @EventHandler
    public void playerDeath(PlayerDeathEvent playerDeathEvent) {
        Player deadplayer = playerDeathEvent.getEntity();
        if (playerDeathEvent.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FALL) {
            Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
            Set<Team> teams = scoreboard.getTeams();
            for (Team team : teams) {
                if (team.hasPlayer(deadplayer)) {
                    for (String entry : scoreboard.getEntries()
                    ) {
                        if (entry.equalsIgnoreCase("kills")) {
                            //subtract
                        }
                    }
                    System.out.println(" do stuff ");
                }
            }
            playerDeathEvent.getEntity().sendMessage("FALLDAMAGEDEATHSTOM");
        }

    }

}
