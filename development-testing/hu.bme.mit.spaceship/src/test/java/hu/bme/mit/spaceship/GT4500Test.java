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
		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(false);
		when(sts.isEmpty()).thenReturn(true);
		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(true);
		ship.setTorpedoStore(pts,sts);
		boolean result = ship.fireTorpedos(FiringMode.SINGLE);

		assertEquals(true, result);
	}

	@Test

	public void fireTorpedos_SinglePrimary_Success_thenSecondaryFail(){
		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(false).thenReturn(true);
		when(sts.isEmpty()).thenReturn(false);
		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(true);
		ship.setTorpedoStore(pts,sts);
		ship.fireTorpedos(FiringMode.SINGLE);

		//assertEquals(true, result);
		boolean result  = ship.fireTorpedos(FiringMode.SINGLE);
		assertEquals(false, result);
	}
	@Test

	public void fireTorpedos_SinglePrimary_BothEmpty(){
		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);

		when(pts.isEmpty()).thenReturn(true);
		when(sts.isEmpty()).thenReturn(true);

		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(true);

		ship.setTorpedoStore(pts,sts);

		boolean result  = ship.fireTorpedos(FiringMode.SINGLE);
		assertEquals(false, result);
	}

	@Test
	public void	fireTorpedos_ALL_Succes(){

		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(false);
		when(sts.isEmpty()).thenReturn(false);
		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(true);
		ship.setTorpedoStore(pts,sts);

		boolean result = ship.fireTorpedos(FiringMode.ALL);
		assertEquals(true, result);
	}

	@Test
	public void	fireTorpedos_ALL_SecondaryIsEmpty(){

		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(false);
		when(sts.isEmpty()).thenReturn(true);
		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(true);
		ship.setTorpedoStore(pts,sts);

		boolean result = ship.fireTorpedos(FiringMode.ALL);
		assertEquals(true, result);
	}


	@Test
	public void	fireTorpedos_ALL_Secondary_Fail(){

		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(false);
		when(sts.isEmpty()).thenReturn(false);
		when(pts.fire(1)).thenReturn(true);
		when(sts.fire(1)).thenReturn(false);
		ship.setTorpedoStore(pts,sts);

		boolean result = ship.fireTorpedos(FiringMode.ALL);
		assertEquals(true, result);
	}

	@Test
	public void	fireTorpedos_ALL_BothEmpty(){

		TorpedoStore pts = mock(TorpedoStore.class);
		TorpedoStore sts = mock(TorpedoStore.class);
		when(pts.isEmpty()).thenReturn(true);
		when(sts.isEmpty()).thenReturn(true);
		when(pts.fire(1)).thenReturn(false);
		when(sts.fire(1)).thenReturn(false);
		ship.setTorpedoStore(pts,sts);

		boolean result = ship.fireTorpedos(FiringMode.ALL);
		assertEquals(false, result);
	}
	public class TestShip extends GT4500{
		public void setTorpedoStore(TorpedoStore primaryTorpedoStore,TorpedoStore secondaryTorpedoStore){
			this.primaryTorpedoStore = primaryTorpedoStore;
			this.secondaryTorpedoStore = secondaryTorpedoStore;
		}
	}
}
