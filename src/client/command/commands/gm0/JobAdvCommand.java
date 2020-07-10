package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.MapleJob;
import java.util.ArrayList;

public class JobAdvCommand extends Command {
	{
		setDescription("");
	}

	@Override
	public void execute(MapleClient c, String[] params) {
		MapleCharacter player = c.getPlayer();
		if (player.getReborns() > 0){
			MapleJob job = player.getJob();
			ArrayList<MapleJob> newJob = job.getNextJob();
			int level = player.getLevel();
			if (newJob.length == 1){
				if (level >= newJob[0].getAdvLevel()){
					player.changeJob(newJob[0]);
				}
				else{
					player.message("You must reach level " + newJob[0].getAdvLevel() + " before advancing.");
				}
			}
			else if (newJob.length == 0){
				ArrayList<String> newJobNames = MapleJob.getNamesFromIds(newJob);
				if (params.length > 0){
					if (newJobNames.contains(param[0])){
						player.changeJob(newJob[newJobNames.indexOf(param[0].toLowerCase())]);
					}
					else{
						player.message("Syntax: @rebirth [" + String.join("|",newJobNames) + "]");
					}
				}
				else{
					player.message("Syntax: @rebirth [" + String.join("|",newJobNames) + "]");
				}
			}
			else{
				player.message("There are no available job advancements at this time.");
			}
		}
		else{
			player.message("You must have at least 1 rebirth to use this command.");
		}
	}
}
