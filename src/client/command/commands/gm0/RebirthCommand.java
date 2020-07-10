package client.command.commands.gm0;

import client.command.Command;
import client.MapleClient;
import client.MapleCharacter;
import client.MapleJob;

public class RebirthCommand extends Command {
	{
		setDescription("");
	}

	@Override
	public void execute(MapleClient c, String[] params) {
		MapleCharacter player = c.getPlayer();
		int rebirthLevel = 200;
		if (player.getJob().isA(MapleJob.NOBLESSE) || player.getJob().isA(MapleJob.DAWNWARRIOR1) || player.getJob().isA(MapleJob.BLAZEWIZARD1) || player.getJob().isA(MapleJob.WINDARCHER1) || player.getJob().isA(MapleJob.NIGHTWALKER1) || player.getJob().isA(MapleJob.THUNDERBREAKER1)) rebirthLevel = 120;
		
		MapleJob job = MapleJob.BEGINNER;
		boolean error = false;
		if (params.length > 0){
			if (params[0] == "0" || params[0].equalsIgnoreCase("beginner") || params[0].equalsIgnoreCase("explorer")){
				job = MapleJob.BEGINNER;
			}
			else if (params[0] == "1000" || params[0].equalsIgnoreCase("koc") || params[0].equalsIgnoreCase("cygnus")){
				job = MapleJob.NOBLESSE;
			}
			else if(params[0] == "2000" || params[0].equalsIgnoreCase("aran") || params[0].equalsIgnoreCase("legend")){
				job = MapleJob.LEGEND;
			}
			else{
				error = true;
			}
		}
		
		if (error){
			player.message("Syntax: @rebirth [Explorer|Cygnus|Aran]");
			return;
		}
		
		if (player.getLevel() == rebirthLevel){
			player.executeReborn(job);
		}
		else{
			player.message("You must reach level " + rebirthLevel + " to rebirth.");
		}
	}
}
