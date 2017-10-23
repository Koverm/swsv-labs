package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

	private TestShip ship;
	
	@Before
	public void init(){	
		this.ship = new TestShip();
	}
		
	@Test
	/**
	 * Test that the first single firing fires the primary torpedo if it is not empty
	 * 
	 * TODO: not a unit test, does not test the module in isolation!
	 */
	public void fireTorpedos_SinglePrimary_Success(){
		boolean result = ship.fireTorpedos(FiringMode.SINGLE);
		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		assertEquals(true, result);
	}
	public class TestShip extends GT4500{
		public void setTorpedoStore(TorpedoStore primaryTorpedoStore,TorpedoStore secondaryTorpedoStore){
			this.primaryTorpedoStore = primaryTorpedoStore;
			this.secondaryTorpedoStore = secondaryTorpedoStore;
		}
	}
}
