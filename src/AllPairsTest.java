import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class AllPairsTest {
	@Test
	// verifying gear decision system when lastDecision = "Reverse" and random
	// variable is 0/1/2
	public void testGetDecisionReverse() {
		System.out.println("\n***Test 1***");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream stdout = System.out;
		try {
			// setup
			System.setOut(new PrintStream(outContent));
			Controller controller = new Controller();
			Status status = controller.getDecision("Reverse");
			String expected1 = "Random int is 0 and gear is Stop";
			String expected2 = "Random int is 1 and gear is Reverse";
			String expected3 = "Random int is 2 and gear is";
			assertTrue(outContent.toString().trim().equalsIgnoreCase(expected1)
					|| outContent.toString().trim().equalsIgnoreCase(expected2)
					|| outContent.toString().trim().equalsIgnoreCase(expected3));
		} finally {
			System.setOut(stdout);
		}
	}

	@Test
	// verifying gear decision system when lastDecision = "Forward" and random
	// variable is 0/1/2
	public void testGetDecisionForward() {
		System.out.println("\n***Test 2***");
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		PrintStream stdout = System.out;
		try {
			// setup
			System.setOut(new PrintStream(outContent));
			Controller controller = new Controller();
			Status status = controller.getDecision("Forward");
			String expected1 = "Random int is 0 and gear is Forward";
			String expected2 = "Random int is 1 and gear is Stop";
			String expected3 = "Random int is 2 and gear is";
			assertTrue(outContent.toString().trim().equalsIgnoreCase(expected1)
					|| outContent.toString().trim().equalsIgnoreCase(expected2)
					|| outContent.toString().trim().equalsIgnoreCase(expected3));
		} finally {
			System.setOut(stdout);
		}
	}
	
	@Test
	// verifying evaluateSlot method call when expectedCarState = "Parked" and
	// slotEvaluated = false
	public void testDriveParkedAndSlotEvaluated() {
		System.out.println("\n***Test 3***");
		Controller controller = new Controller();
		Controller controllerSpy = spy(controller);
		Location finalLocation = new Location(26.87, 98.71);
		controllerSpy.drive("Parked", finalLocation);
		verify(controllerSpy, times(1)).evaluateSlot(finalLocation);
	}

	@Test
	// verifying evaluateSlot method call when expectedCarState = "Un-parked" and slotEvaluated is dont care
	public void testDriveUnParkedAndSlotEvaluated() {
		System.out.println("\n***Test 4***");
		Controller controller = new Controller();
		Controller controllerSpy = spy(controller);
		Location finalLocation = new Location(26.87, 98.71);
		controllerSpy.drive("Un-parked", finalLocation);
		verify(controllerSpy, times(0)).evaluateSlot(finalLocation);
	}

}
