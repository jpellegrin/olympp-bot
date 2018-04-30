package slackbot.data.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import slackbot.data.jpa.model.Rssfeed;

public interface RssfeedRepository extends JpaRepository<Rssfeed, Long> {

}
