package twitter.DAO;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import twitter.domain.Authorities;
import twitter.domain.Spitter;
@Repository
public class SpitterDaoHibernate4 extends BaseDaoHibernate4<Spitter> implements SpitterDao{
	@Transactional
	public Serializable save( Spitter spitter ){
		try{
			Authorities authorities = new Authorities();
			authorities.setAuthority("true");
			authorities.setUsername(spitter.getUsername() );
			//getHibernateTemplate().save( authority );
			//return "";
			//getSessionFactory().getCurrentSession().save(authorities);
			return getSessionFactory().getCurrentSession().save(spitter);
		}
		catch( Exception e ){
			System.out.println( e.getMessage() );
			return "error";
		}
	}
}
