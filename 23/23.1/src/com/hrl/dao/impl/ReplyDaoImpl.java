package com.hrl.dao.impl;

import java.util.List;

import com.hrl.dao.IReplyDao;
import com.hrl.dao.IUserDao;
import com.hrl.model.Article;
import com.hrl.model.Reply;
import com.hrl.model.User;

public class ReplyDaoImpl extends DefaultDaoImpl implements IReplyDao {
/**
 * 娣诲姞鍥炲
 */
	public void addReply(Reply reply) {
        this.save(reply);
	}
}
