package twitter.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import sun.rmi.runtime.Log;
import twitter.DAO.SpittleDaoHibernate4;
import twitter.domain.BroadcastCommentForm;
import twitter.domain.Comment;
import twitter.domain.CommentOnForm;
import twitter.domain.Notification;
import twitter.domain.Spittle;
import twitter.domain.SpittleForm;
import twitter.service.CommentFeedService;
import twitter.service.SpittleFeedService;

@Controller
public class SpittrMessageController {

  private SpittleDaoHibernate4 spittleRepo;
  private SpittleFeedService feedService;
  private CommentFeedService feedComment;
  @Autowired
  public SpittrMessageController(SpittleFeedService feedService, CommentFeedService feedComment) {
	//this.spittleRepo = spittleRepo;
	this.feedService = feedService;
	this.feedComment = feedComment;
  }  

  @MessageMapping("/spittle")
  @SendToUser("/queue/notifications")
  public Notification handleSpittle(Principal principal, SpittleForm form) {
	  System.out.println("incoming spittle!");
	  Spittle spittle = new Spittle( 1, form.getText(), new Date());
	  
	  //Spittle spittle = new Spittle( principal, form.getText(), new Date());
	  feedService.saveSpittle(spittle);
	  //feedService.saveSpittle( new Spittle(form.getText(), new Date() ) );
	  feedService.broadcastSpittle(spittle);
	  return new Notification("Saved Spittle for user: " + principal.getName());
  }
  
  @MessageMapping("/commentBroadcast")
  @SendToUser("/queue/notifications")
  public Notification broadcastComment(Principal principal, BroadcastCommentForm form) {
	  System.out.println("incoming broadcasting message: "+ form.getText());

	  Comment comment = new Comment(principal.getName(), form.getText(), 0 );
	  feedComment.save( comment );
	  feedComment.broadcastComment(comment);
	  return new Notification("successfully broadcast comment: " + principal.getName());
  }  
  
  @MessageMapping("/commentOn")
  @SendToUser("/queue/notifications")
  public Notification commentOn( Principal principal, CommentOnForm form ){
	  System.out.println("incomming comment!");
	  //System.out.println( principal.getName() +" :" + form.getText() +" "+form.getCommentToId() +" "+form.getCommentToUser() );
	  Comment comment = new Comment( principal.getName(), form.getText(), form.getCommentToId() );
	  feedComment.save(comment);
	  feedComment.commentOn( comment, form.getCommentToId(), form.getCommentToUser() );
	  return new Notification( "successfully comment from: "+ principal.getName() );
  }
}
