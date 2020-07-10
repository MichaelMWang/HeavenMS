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
			if (newJob.size() == 1){
				if (level >= newJob.get(0).getAdvLevel()){
					player.changeJob(newJob.get(0));
				}
				else{
					player.message("You must reach level " + newJob.get(0).getAdvLevel() + " before advancing.");
				}
			}
			else if (newJob.size() == 0){
				ArrayList<String> newJobNames = MapleJob.getNamesFromIds(newJob);
				if (params.length > 0){
					if (newJobNames.contains(params[0])){
						//checklevel!
						if (level >= newJob.get(newJobNames.indexOf(params[0].toLowerCase())).getAdvLevel()){
							player.changeJob(newJob.get(newJobNames.indexOf(params[0].toLowerCase())));
						}
						else{
							player.message("You must reach level " + newJob.get(newJobNames.indexOf(params[0].toLowerCase())).getAdvLevel() + " before advancing.");
						}
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
