package twitter.service;

import org.springframework.stereotype.Service;

import twitter.domain.Spitter;

public interface SpitterFeedService {
	void save(Spitter spitter);
}
