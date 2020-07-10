/*
	This file is part of the OdinMS Maple Story Server
    Copyright (C) 2008 Patrick Huy <patrick.huy@frz.cc>
		       Matthias Butz <matze@odinms.de>
		       Jan Christian Meyer <vimes@odinms.de>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation version 3 as published by
    the Free Software Foundation. You may not use, modify or distribute
    this program under any other version of the GNU Affero General Public
    License.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package client;
import java.util.ArrayList;

public enum MapleJob {
    BEGINNER(0),

    WARRIOR(100),
    FIGHTER(110), CRUSADER(111), HERO(112),
    PAGE(120), WHITEKNIGHT(121), PALADIN(122),
    SPEARMAN(130), DRAGONKNIGHT(131), DARKKNIGHT(132),

    MAGICIAN(200),
    FP_WIZARD(210), FP_MAGE(211), FP_ARCHMAGE(212),
    IL_WIZARD(220), IL_MAGE(221), IL_ARCHMAGE(222),
    CLERIC(230), PRIEST(231), BISHOP(232),

    BOWMAN(300),
    HUNTER(310), RANGER(311), BOWMASTER(312),
    CROSSBOWMAN(320), SNIPER(321), MARKSMAN(322),

    THIEF(400),
    ASSASSIN(410), HERMIT(411), NIGHTLORD(412),
    BANDIT(420), CHIEFBANDIT(421), SHADOWER(422),

    PIRATE(500),
    BRAWLER(510), MARAUDER(511), BUCCANEER(512),
    GUNSLINGER(520), OUTLAW(521), CORSAIR(522),

    MAPLELEAF_BRIGADIER(800),
    GM(900), SUPERGM(910),

    NOBLESSE(1000),
    DAWNWARRIOR1(1100), DAWNWARRIOR2(1110), DAWNWARRIOR3(1111), DAWNWARRIOR4(1112),
    BLAZEWIZARD1(1200), BLAZEWIZARD2(1210), BLAZEWIZARD3(1211), BLAZEWIZARD4(1212),
    WINDARCHER1(1300), WINDARCHER2(1310), WINDARCHER3(1311), WINDARCHER4(1312),
    NIGHTWALKER1(1400), NIGHTWALKER2(1410), NIGHTWALKER3(1411), NIGHTWALKER4(1412),
    THUNDERBREAKER1(1500), THUNDERBREAKER2(1510), THUNDERBREAKER3(1511), THUNDERBREAKER4(1512),

    LEGEND(2000), EVAN(2001),
    ARAN1(2100), ARAN2(2110), ARAN3(2111), ARAN4(2112),
	
    EVAN1(2200), EVAN2(2210), EVAN3(2211), EVAN4(2212), EVAN5(2213), EVAN6(2214),
    EVAN7(2215), EVAN8(2216), EVAN9(2217), EVAN10(2218);

    final int jobid;
    final static int maxId = 22;    // maxId = (EVAN / 100);
    
    private MapleJob(int id) {
        jobid = id;
    }
    
    public static int getMax() {
        return maxId;
    }

    public int getId() {
        return jobid;
    }

    public static MapleJob getById(int id) {
        for (MapleJob l : MapleJob.values()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    public static MapleJob getBy5ByteEncoding(int encoded) {
        switch (encoded) {
            case 2:
                return WARRIOR;
            case 4:
                return MAGICIAN;
            case 8:
                return BOWMAN;
            case 16:
                return THIEF;
            case 32:
                return PIRATE;
            case 1024:
                return NOBLESSE;
            case 2048:
                return DAWNWARRIOR1;
            case 4096:
                return BLAZEWIZARD1;
            case 8192:
                return WINDARCHER1;
            case 16384:
                return NIGHTWALKER1;
            case 32768:
                return THUNDERBREAKER1;
            default:
                return BEGINNER;
        }
    }
    
    public boolean isA(MapleJob basejob) {  // thanks Steve (kaito1410) for pointing out an improvement here
        int basebranch = basejob.getId() / 10;
        return (getId() / 10 == basebranch && getId() >= basejob.getId()) || (basebranch % 10 == 0 && getId() / 100 == basejob.getId() / 100);
    }
    
    public int getJobNiche() {
        return (jobid / 100) % 10;
        
        /*
        case 0: BEGINNER;
        case 1: WARRIOR;
        case 2: MAGICIAN;
        case 3: BOWMAN;  
        case 4: THIEF;
        case 5: PIRATE;
        */
    }
	
	public ArrayList<MapleJob> getNextJob(){
		ArrayList<MapleJob> jobs = new ArrayList<MapleJob>();
		switch (this){
			case MapleJob.BEGINNER:
				jobs.add(MapleJob.WARRIOR);
				jobs.add(MapleJob.MAGICIAN);
				jobs.add(MapleJob.BOWMAN);
				jobs.add(MapleJob.THIEF);
				jobs.add(MapleJob.PIRATE);
				break;
			case MapleJob.WARRIOR:
				jobs.add(MapleJob.FIGHTER);
				jobs.add(MapleJob.PAGE);
				jobs.add(MapleJob.SPEARMAN);
				break;
			case MapleJob.FIGHTER:
				jobs.add(MapleJob.CRUSADER);
				break;
			case MapleJob.CRUSADER:
				jobs.add(MapleJob.HERO);
				break;
			case MapleJob.PAGE:
				jobs.add(MapleJob.WHITEKNIGHT);
				break;
			case MapleJob.WHITEKNIGHT:
				jobs.add(MapleJob.PALADIN);
				break;
			case MapleJob.SPEARMAN:
				jobs.add(MapleJob.DRAGONKNIGHT);
				break;
			case MapleJob.DRAGONKNIGHT:
				jobs.add(MapleJob.DARKKNIGHT);
				break;
			case MapleJob.MAGICIAN:
				jobs.add(MapleJob.FP_WIZARD);
				jobs.add(MapleJob.IL_WIZARD);
				jobs.add(MapleJob.CLERIC);
				break;
			case MapleJob.FP_WIZARD:
				jobs.add(MapleJob.FP_MAGE);
				break;
			case MapleJob.FP_MAGE:
				jobs.add(MapleJob.FP_ARCHMAGE);
				break;
			case MapleJob.IL_WIZARD:
				jobs.add(MapleJob.IL_MAGE);
				break;
			case MapleJob.IL_MAGE:
				jobs.add(MapleJob.IL_ARCHMAGE);
				break;
			case MapleJob.CLERIC:
				jobs.add(MapleJob.PRIEST);
				break;
			case MapleJob.PRIEST:
				jobs.add(MapleJob.BISHOP);
				break;
			case MapleJob.BOWMAN:
				jobs.add(MapleJob.HUNTER);
				jobs.add(MapleJob.CROSSBOWMAN);
				break;
			case MapleJob.HUNTER:
				jobs.add(MapleJob.RANGER);
				break;
			case MapleJob.RANGER:
				jobs.add(MapleJob.BOWMASTER);
				break;
			case MapleJob.CROSSBOWMAN:
				jobs.add(MapleJob.SNIPER);
				break;
			case MapleJob.SNIPER:
				jobs.add(MapleJob.MARKSMAN);
				break;
			case MapleJob.THIEF:
				jobs.add(MapleJob.ASSASSIN);
				jobs.add(MapleJob.BANDIT);
				break;
			case MapleJob.ASSASSIN:
				jobs.add(MapleJob.HERMIT);
				break;
			case MapleJob.HERMIT:
				jobs.add(MapleJob.NIGHTLORD);
				break;
			case MapleJob.BANDIT:
				jobs.add(MapleJob.CHIEFBANDIT);
				break;
			case MapleJob.CHIEFBANDIT:
				jobs.add(MapleJob.SHADOWER);
				break;
			case MapleJob.PIRATE:
				jobs.add(MapleJob.BRAWLER);
				jobs.add(MapleJob.GUNSLINGER);
				break;
			case MapleJob.BRAWLER:
				jobs.add(MapleJob.MARAUDER);
				break;
			case MapleJob.MARAUDER:
				jobs.add(MapleJob.BUCCANEER);
				break;
			case MapleJob.GUNSLINGER:
				jobs.add(MapleJob.OUTLAW);
				break;
			case MapleJob.OUTLAW:
				jobs.add(MapleJob.CORSAIR);
				break;
			case MapleJob.NOBLESSE:
				jobs.add(MapleJob.DAWNWARRIOR1);
				jobs.add(MapleJob.BLAZEWIZARD1);
				jobs.add(MapleJob.WINDARCHER1);
				jobs.add(MapleJob.NIGHTWALKER1);
				jobs.add(MapleJob.THUNDERBREAKER1);
				break;
			case MapleJob.DAWNWARRIOR1:
				jobs.add(MapleJob.DAWNWARRIOR2);
				break;
			case MapleJob.DAWNWARRIOR2:
				jobs.add(MapleJob.DAWNWARRIOR3);
				break;
			case MapleJob.DAWNWARRIOR3:
				jobs.add(MapleJob.DAWNWARRIOR4);
				break;
			case MapleJob.BLAZEWIZARD1:
				jobs.add(MapleJob.BLAZEWIZARD2);
				break;
			case MapleJob.BLAZEWIZARD2:
				jobs.add(MapleJob.BLAZEWIZARD3);
				break;
			case MapleJob.BLAZEWIZARD3:
				jobs.add(MapleJob.BLAZEWIZARD4);
				break;
			case MapleJob.WINDARCHER1:
				jobs.add(MapleJob.WINDARCHER2);
				break;
			case MapleJob.WINDARCHER2:
				jobs.add(MapleJob.WINDARCHER3);
				break;
			case MapleJob.WINDARCHER3:
				jobs.add(MapleJob.WINDARCHER4);
				break;
			case MapleJob.NIGHTWALKER1:
				jobs.add(MapleJob.NIGHTWALKER2);
				break;
			case MapleJob.NIGHTWALKER2:
				jobs.add(MapleJob.NIGHTWALKER3);
				break;
			case MapleJob.NIGHTWALKER3:
				jobs.add(MapleJob.NIGHTWALKER4);
				break;
			case MapleJob.THUNDERBREAKER1:
				jobs.add(MapleJob.THUNDERBREAKER2);
				break;
			case MapleJob.THUNDERBREAKER2:
				jobs.add(MapleJob.THUNDERBREAKER3);
				break;
			case MapleJob.THUNDERBREAKER3:
				jobs.add(MapleJob.THUNDERBREAKER4);
				break;
			case MapleJob.LEGEND:
				jobs.add(MapleJob.ARAN1);
				break;
			case MapleJob.ARAN1:
				jobs.add(MapleJob.ARAN2);
				break;
			case MapleJob.ARAN2:
				jobs.add(MapleJob.ARAN3);
				break;
			case MapleJob.ARAN3:
				jobs.add(MapleJob.ARAN4);
				break;
			default:
				break;
		}
		return jobs;
	}
	
	public int getAdvLevel(){
		if (isA(MapleJob.EVAN1)){
			return 0;
		}
		int level = 0;
		if (getId() % 10 == 1){ // 3rd job
			level = 120;
		}
		else if (getId() % 100 > 0 && getId() % 10 == 0){ // 2nd job
			level = 70;
		}
		else if (getId() % 1000 > 0 && getId() % 100 == 0){ // 1st job
			level = 30;
		}
		else if (getId() % 1000 == 0){ // beginner
			level = 10;
		}
		return level;
	}
	
	public static ArrayList<String> getNamesFromIds(ArrayList<MapleJob> jobs){
		ArrayList<String> names = new ArrayList<String>();
		for (MapleJob job: jobs){
			names.add(job.name().toLowerCase());
		}
		return names;
	}
}
