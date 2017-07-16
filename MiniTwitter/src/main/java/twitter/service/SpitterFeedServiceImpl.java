package twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import twitter.DAO.SpitterDao;
import twitter.domain.Spitter;


@Service
public class SpitterFeedServiceImpl implements SpitterFeedService{
	
	private SpitterDao spitterDao;
	
	@Autowired
	public SpitterFeedServiceImpl(SpitterDao spitterDao){
		this.spitterDao = spitterDao;
	}
	@Override
	public void save(Spitter spitter) {
		// TODO Auto-generated method stub
		spitterDao.save( spitter );
	}
}
