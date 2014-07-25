package com.bionic.socialNetwork.logic;

import com.bionic.socialNetwork.dao.GroupDao;
import com.bionic.socialNetwork.dao.GroupPostDao;
import com.bionic.socialNetwork.dao.UserDao;
import com.bionic.socialNetwork.dao.impl.GroupDaoImpl;
import com.bionic.socialNetwork.dao.impl.GroupPostDaoImpl;
import com.bionic.socialNetwork.dao.impl.UserDaoImpl;
import com.bionic.socialNetwork.models.Group;
import com.bionic.socialNetwork.models.GroupPost;
import com.bionic.socialNetwork.models.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Troshchuk
 * @version 1.00  23.07.14.
 */
public class GroupLogic {
    private GroupDao groupDao;
    private GroupPostDao groupPostDao;

    public GroupLogic() {
        groupDao = new GroupDaoImpl();
        groupPostDao = new GroupPostDaoImpl();
    }

    public Group getGroup(long id) {
        try {
            return groupDao.selectById(id);
        } catch (Exception e) {
            return null;

        }
    }

    public boolean createPost(long id, long ts, String msg) {
        try {
            Group group = getGroup(id);
            if (group == null) {
                return false;
            }

            User user = new UserDaoImpl().selectById(ts);

            groupPostDao.insert(new GroupPost(group, user, msg, new Timestamp(
                    new java.util.Date().getTime())));

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deletePost(long ts, long post) {
        try {
            GroupPost groupPost = groupPostDao.selectById(post);
            if (groupPost == null) {
                return false;
            }

            if (groupPost.getUser().getId() != ts) {
                return false;
            }

            groupPostDao.delete(groupPost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<User> followers() {
        return null;
    }
}