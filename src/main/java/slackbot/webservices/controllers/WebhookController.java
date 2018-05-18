package slackbot.webservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import slackbot.data.jpa.model.Rssfeed;
import slackbot.data.jpa.model.Webhook;
import slackbot.data.jpa.repositories.WebhookRepository;

@RestController
@RequestMapping(path = "/slackbot/webhook")
public class WebhookController {

	@Autowired
	private WebhookRepository webhookRepository;

	@GetMapping("/add")
	public @ResponseBody String create(@RequestParam String name) {

		Webhook webhook = new Webhook();
		webhook.setTitle("mentoring_vincent");
		webhook.setUrl("https://hooks.slack.com/services/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");
		// webhookRepository.save(webhook);

		return "OK";
	}

	@GetMapping("/{id}")
	public @ResponseBody Rssfeed show(@PathVariable("id") long id) {
		return null;
	}

	@GetMapping("/list")
	public @ResponseBody Iterable<Webhook> list() {
		return webhookRepository.findAll();
	}

}
