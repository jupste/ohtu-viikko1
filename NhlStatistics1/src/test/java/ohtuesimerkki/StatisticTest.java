/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jussiste
 */
public class StatisticTest {
    

        Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    @Test
    public void searchWorks(){
        assertEquals(stats.search("Kurri").getName(), "Kurri");
        assertNull(stats.search("Muumipeikko"));
    }
    @Test
    public void topScoresGiveAList(){
        //metodi antaa howMany+1 parasta pelaajaa
        List<Player> topdogs= stats.topScorers(3);
        assertEquals(4, topdogs.size());
        assertEquals(topdogs.get(0).getName(), "Gretzky");
    }
    @Test
        public void teamMethodWorks(){
            List<Player> team=stats.team("EDM");
            assertEquals(3, team.size());
        }
}
