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
			ArrayList<MapleJob> newJobs = job.getNextJob();
			MapleJob newJob = MapleJob.BEGINNER;
			int level = player.getLevel();
			boolean changeJob = false;
			if (newJobs.size() == 1){
				newJob = newJobs.get(0);
				if (level >= newJob.getAdvLevel()){
					changeJob = true;
				}
				else{
					player.message("You must reach level " + newJob.getAdvLevel() + " before advancing.");
				}
			}
			else if (newJobs.size() > 1){
				ArrayList<String> newJobNames = MapleJob.getNamesFromIds(newJobs);
				if (params.length > 0){
					if (newJobNames.contains(params[0])){
						newJob = newJobs.get(newJobNames.indexOf(params[0].toLowerCase()));
						if (level >= newJob.getAdvLevel()){
							changeJob = true;
						}
						else{
							player.message("You must reach level " + newJob.getAdvLevel() + " before advancing.");
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
			if (changeJob){
				player.changeJob(newJob);
				if (newJob.getAdvLevel() <= 10 && level > newJob.getAdvLevel()){
					player.updateRemainingSp(player.getRemainingSp() + (level - newJob.getAdvLevel())*3);
				}
			}
		}
		else{
			player.message("You must have at least 1 rebirth to use this command.");
		}
	}
}
