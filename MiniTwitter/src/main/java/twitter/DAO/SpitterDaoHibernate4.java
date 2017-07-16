package twitter.DAO;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import twitter.domain.Spitter;
@Repository
public class SpitterDaoHibernate4 extends BaseDaoHibernate4<Spitter> implements SpitterDao{
	@Transactional
	public Serializable save( Spitter spitter ){
		try{
			return getSessionFactory().getCurrentSession().save(spitter);
		}
		catch( Exception e ){
			System.out.println( e.getMessage() );
			return "error";
		}
	}
}
