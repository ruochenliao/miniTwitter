package twitter.DAO;

import twitter.domain.Spittle;

public interface SpittleRepository {

	Spittle save(Spittle spittle);
	
	Spittle findOne(Long id);
	
}
