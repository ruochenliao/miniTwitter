package twitter.service;

import twitter.domain.Spittle;

public interface SpittleFeedService {

	void broadcastSpittle(Spittle spittle);
	
	void saveSpittle(Spittle spittle);
}