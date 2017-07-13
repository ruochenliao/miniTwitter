package twitter.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import sun.rmi.runtime.Log;
import twitter.DAO.SpittleDaoHibernate4;
import twitter.domain.Notification;
import twitter.domain.Spittle;
import twitter.domain.SpittleForm;
import twitter.service.SpittleFeedService;

@Controller
public class SpittrMessageController {

  private SpittleDaoHibernate4 spittleRepo;
  private SpittleFeedService feedService;
  @Autowired
  public SpittrMessageController(SpittleFeedService feedService) {
	//this.spittleRepo = spittleRepo;
	this.feedService = feedService;
  }  
/*
  @Autowired
  public SpittrMessageController(SpittleDaoHibernate4 spittleRepo, SpittleFeedService feedService) {
	this.spittleRepo = spittleRepo;
	this.feedService = feedService;
  }
  */
  @MessageMapping("/spittle")
  @SendToUser("/queue/notifications")
  public Notification handleSpittle(Principal principal, SpittleForm form) {
	  Spittle spittle = new Spittle( principal.getName(), form.getText(), new Date());
	  System.out.println( principal.getName()+" "+ form.getText() +" " );
	  feedService.saveSpittle(spittle);
	  feedService.broadcastSpittle(spittle);
	  return new Notification("Saved Spittle for user: " + principal.getName());
  }
  
}
