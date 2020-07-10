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
			case BEGINNER:
				jobs.add(WARRIOR);
				jobs.add(MAGICIAN);
				jobs.add(BOWMAN);
				jobs.add(THIEF);
				jobs.add(PIRATE);
				break;
			case WARRIOR:
				jobs.add(FIGHTER);
				jobs.add(PAGE);
				jobs.add(SPEARMAN);
				break;
			case FIGHTER:
				jobs.add(CRUSADER);
				break;
			case CRUSADER:
				jobs.add(HERO);
				break;
			case PAGE:
				jobs.add(WHITEKNIGHT);
				break;
			case WHITEKNIGHT:
				jobs.add(PALADIN);
				break;
			case SPEARMAN:
				jobs.add(DRAGONKNIGHT);
				break;
			case DRAGONKNIGHT:
				jobs.add(DARKKNIGHT);
				break;
			case MAGICIAN:
				jobs.add(FP_WIZARD);
				jobs.add(IL_WIZARD);
				jobs.add(CLERIC);
				break;
			case FP_WIZARD:
				jobs.add(FP_MAGE);
				break;
			case FP_MAGE:
				jobs.add(FP_ARCHMAGE);
				break;
			case IL_WIZARD:
				jobs.add(IL_MAGE);
				break;
			case IL_MAGE:
				jobs.add(IL_ARCHMAGE);
				break;
			case CLERIC:
				jobs.add(PRIEST);
				break;
			case PRIEST:
				jobs.add(BISHOP);
				break;
			case BOWMAN:
				jobs.add(HUNTER);
				jobs.add(CROSSBOWMAN);
				break;
			case HUNTER:
				jobs.add(RANGER);
				break;
			case RANGER:
				jobs.add(BOWMASTER);
				break;
			case CROSSBOWMAN:
				jobs.add(SNIPER);
				break;
			case SNIPER:
				jobs.add(MARKSMAN);
				break;
			case THIEF:
				jobs.add(ASSASSIN);
				jobs.add(BANDIT);
				break;
			case ASSASSIN:
				jobs.add(HERMIT);
				break;
			case HERMIT:
				jobs.add(NIGHTLORD);
				break;
			case BANDIT:
				jobs.add(CHIEFBANDIT);
				break;
			case CHIEFBANDIT:
				jobs.add(SHADOWER);
				break;
			case PIRATE:
				jobs.add(BRAWLER);
				jobs.add(GUNSLINGER);
				break;
			case BRAWLER:
				jobs.add(MARAUDER);
				break;
			case MARAUDER:
				jobs.add(BUCCANEER);
				break;
			case GUNSLINGER:
				jobs.add(OUTLAW);
				break;
			case OUTLAW:
				jobs.add(CORSAIR);
				break;
			case NOBLESSE:
				jobs.add(DAWNWARRIOR1);
				jobs.add(BLAZEWIZARD1);
				jobs.add(WINDARCHER1);
				jobs.add(NIGHTWALKER1);
				jobs.add(THUNDERBREAKER1);
				break;
			case DAWNWARRIOR1:
				jobs.add(DAWNWARRIOR2);
				break;
			case DAWNWARRIOR2:
				jobs.add(DAWNWARRIOR3);
				break;
			case DAWNWARRIOR3:
				jobs.add(DAWNWARRIOR4);
				break;
			case BLAZEWIZARD1:
				jobs.add(BLAZEWIZARD2);
				break;
			case BLAZEWIZARD2:
				jobs.add(BLAZEWIZARD3);
				break;
			case BLAZEWIZARD3:
				jobs.add(BLAZEWIZARD4);
				break;
			case WINDARCHER1:
				jobs.add(WINDARCHER2);
				break;
			case WINDARCHER2:
				jobs.add(WINDARCHER3);
				break;
			case WINDARCHER3:
				jobs.add(WINDARCHER4);
				break;
			case NIGHTWALKER1:
				jobs.add(NIGHTWALKER2);
				break;
			case NIGHTWALKER2:
				jobs.add(NIGHTWALKER3);
				break;
			case NIGHTWALKER3:
				jobs.add(NIGHTWALKER4);
				break;
			case THUNDERBREAKER1:
				jobs.add(THUNDERBREAKER2);
				break;
			case THUNDERBREAKER2:
				jobs.add(THUNDERBREAKER3);
				break;
			case THUNDERBREAKER3:
				jobs.add(THUNDERBREAKER4);
				break;
			case LEGEND:
				jobs.add(ARAN1);
				break;
			case ARAN1:
				jobs.add(ARAN2);
				break;
			case ARAN2:
				jobs.add(ARAN3);
				break;
			case ARAN3:
				jobs.add(ARAN4);
				break;
			default:
				break;
		}
		return jobs;
	}
	
	public int getAdvLevel(){
		if (isA(EVAN1)){
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
