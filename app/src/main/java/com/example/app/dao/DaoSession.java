package com.example.app.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.app.common.bean.Song;
import com.example.app.common.bean.SongComment;

import com.example.app.dao.SongDao;
import com.example.app.dao.SongCommentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig songDaoConfig;
    private final DaoConfig songCommentDaoConfig;

    private final SongDao songDao;
    private final SongCommentDao songCommentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        songDaoConfig = daoConfigMap.get(SongDao.class).clone();
        songDaoConfig.initIdentityScope(type);

        songCommentDaoConfig = daoConfigMap.get(SongCommentDao.class).clone();
        songCommentDaoConfig.initIdentityScope(type);

        songDao = new SongDao(songDaoConfig, this);
        songCommentDao = new SongCommentDao(songCommentDaoConfig, this);

        registerDao(Song.class, songDao);
        registerDao(SongComment.class, songCommentDao);
    }
    
    public void clear() {
        songDaoConfig.clearIdentityScope();
        songCommentDaoConfig.clearIdentityScope();
    }

    public SongDao getSongDao() {
        return songDao;
    }

    public SongCommentDao getSongCommentDao() {
        return songCommentDao;
    }

}