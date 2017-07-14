package twitter.DAO;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import twitter.domain.Spittle;

@Repository
public class SpittleDaoHibernate4 extends BaseDaoHibernate4<Spittle> implements SpittleDao{
	//private AtomicLong nextId = new AtomicLong(1L);
	@Transactional
	public Serializable save(Spittle spittle)
	{
		try{
			//Field idField = Spittle.class.getField("id");
			//idField.setAccessible(true);
			//Long id = nextId.incrementAndGet();
			//idField.set(spittle, id);
			//System.out.println( "log show: "+spittle.getId() +" " +spittle.getMessage());
			return getSessionFactory().getCurrentSession().save(spittle);
		}
		catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();			
			return "error";
		}
	}
}
