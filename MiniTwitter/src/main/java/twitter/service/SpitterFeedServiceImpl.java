package twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import twitter.DAO.AuthoritiesDao;
import twitter.DAO.SpitterDao;
import twitter.domain.Authorities;
import twitter.domain.Spitter;


@Service
public class SpitterFeedServiceImpl implements SpitterFeedService{
	
	private SpitterDao spitterDao;
	private AuthoritiesDao authoritiesDao;
	@Autowired
	public SpitterFeedServiceImpl(SpitterDao spitterDao, AuthoritiesDao authoritiesDao){
		this.spitterDao = spitterDao;
		this.authoritiesDao = authoritiesDao;
	}
	@Override
	public void save(Spitter spitter) {
		// TODO Auto-generated method stub
		spitterDao.save( spitter );
		Authorities authorities = new Authorities();
		authorities.setAuthority("true");
		authorities.setUsername(spitter.getUsername());
		authoritiesDao.save( authorities );
	}
}
