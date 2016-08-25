package com.hrl.action;

import java.util.Date;

import com.hrl.dao.IReplyDao;
import com.hrl.model.Reply;
import com.hrl.model.User;
import com.hrl.util.JSONKit;

public class ReplyAction extends DefaultAction {
	private IReplyDao replyDao = null;
	private Reply reply = null;
    /**
     * 娣诲姞鍥炲锛岀粰瀹㈡埛绔繑鍥瀓son鏍煎紡鐨勪俊鎭�
     * @return
     */
	public String addReply() {
		User currUser = this.getCurrUser();
		if (currUser == null) {
			JSONKit.outJSONInfo("{success:false,msg:'浣犺繕娌℃湁鐧婚檰,涓嶈兘鍥炲'}");
			return NONE;
		}
		this.reply.setReplyTime(new Date());
		this.replyDao.addReply(reply);
		JSONKit.outJSONInfo("{success:true,'userName':'"
				+ currUser.getUserName() + "','replyTime':'"
				+ this.getNowTime() + "','content':'"
				+ this.getReply().getContent() + "'}");
		return NONE;
	}

	public IReplyDao getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(IReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

}
