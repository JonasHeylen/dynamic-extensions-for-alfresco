package nl.runnable.alfresco.webscripts;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ExceptionHandlerTest extends AbstractWebScriptAnnotationsTest {

	@Inject
	private ExceptionHandlerExample handler;

	@Test
	public void testHandleExceptionOfOneType() {
		handleGet("/throwIllegalArgumentException");
		assertNotNull(handler.illegalArgumentException);
		assertNotNull(handler.throwable);
		assertNull(handler.illegalStateException);
	}

	@Test
	public void testHandleExceptionOfAnotherType() {
		handleGet("/throwIllegalStateException");
		assertNull(handler.illegalArgumentException);
		assertNotNull(handler.throwable);
		assertNotNull(handler.illegalStateException);
	}
}