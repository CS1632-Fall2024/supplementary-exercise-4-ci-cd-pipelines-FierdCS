package edu.pitt.cs;

import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public interface Cat {
	public static Cat createInstance(InstanceType type, int id, String name) {
		switch (type) {
			case IMPL:
				return new CatImpl(id, name);
			case BUGGY:
				return new CatBuggy(id, name);
			case SOLUTION:
				return new CatSolution(id, name);
			case MOCK:
				// Create a mock object for the Cat interface
				Cat mockCat = Mockito.mock(Cat.class);

				// Stub the getId() method to return the given id
				when(mockCat.getId()).thenReturn(id);

				// Stub the getName() method to return the given name
				when(mockCat.getName()).thenReturn(name);

				// Stub the getRented() method to return false initially
				when(mockCat.getRented()).thenReturn(false);

				// Mock behavior for rentCat: After renting, getRented() should return true
				doAnswer(invocation -> {
					when(mockCat.getRented()).thenReturn(true);
					return null;
				}).when(mockCat).rentCat();

				// Mock behavior for returnCat: After returning, getRented() should return false
				doAnswer(invocation -> {
					when(mockCat.getRented()).thenReturn(false);
					return null;
				}).when(mockCat).returnCat();

				// You can also mock the renameCat method behavior
				doAnswer(invocation -> {
					String newName = invocation.getArgument(0);
					when(mockCat.getName()).thenReturn(newName);
					return null;
				}).when(mockCat).renameCat(anyString());

				// Stub toString() to return a string representation of the cat
				when(mockCat.toString()).thenReturn("ID " + id + ". " + name);

				return mockCat;

			default:
				assert (false);
				return null;
		}
	}

	// WARNING: You are not allowed to change any part of the interface.
	// That means you cannot add any method nor modify any of these methods.

	public void rentCat();

	public void returnCat();

	public void renameCat(String name);

	public String getName();

	public int getId();

	public boolean getRented();

	public String toString();
}
