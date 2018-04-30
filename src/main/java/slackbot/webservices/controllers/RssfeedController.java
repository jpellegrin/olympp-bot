package slackbot.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.repositories.RssfeedRepository;

@RestController
@RequestMapping(path = "/slackbot/rssfeed")
public class RssfeedController {

	@Autowired
	private RssfeedRepository rssfeedRepository;

	@GetMapping("/add")
	public @ResponseBody String create(@RequestParam String name) {

		Rssfeed rssfeed = new Rssfeed();
		rssfeed.setTitle("title test");
		rssfeed.setRssUrl("urlTest");

		rssfeedRepository.save(rssfeed);

		return "OK";
	}

	@GetMapping("/{id}")
	public @ResponseBody Rssfeed show(@PathVariable("id") long id) {
		return null;
	}

	@GetMapping("/list")
	public @ResponseBody Iterable<Rssfeed> list() {
		return rssfeedRepository.findAll();
	}

}
