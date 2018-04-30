package slackbot.webservices.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.repositories.RssfeedRepository;
import slackbot.webservices.controllers.RssfeedController;

public class RssfeedControllersTests {

	@InjectMocks
	private RssfeedController rssfeedController;

	@Mock
	private RssfeedRepository rssfeedRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		Optional<Rssfeed> rssfeed = Optional.of(new Rssfeed());
		when(rssfeedRepository.findById(1L)).thenReturn(rssfeed);

		String result = rssfeedController.create("toto");

		// verify(rssfeedRepository).findById(1L);

		assertEquals(result, "OK");
	}

}
