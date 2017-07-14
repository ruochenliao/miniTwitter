package twitter.service;

import java.util.List;

import twitter.domain.Spittle;

public interface SpittleFeedService {

	void broadcastSpittle(Spittle spittle);
	
	void saveSpittle(Spittle spittle);
	
	Spittle getSpittleById(long spittle_id);
	
}