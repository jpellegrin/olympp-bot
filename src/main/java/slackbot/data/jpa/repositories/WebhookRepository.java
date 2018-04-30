package slackbot.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import slackbot.data.jpa.model.Webhook;

public interface WebhookRepository extends JpaRepository<Webhook, Long> {

}
