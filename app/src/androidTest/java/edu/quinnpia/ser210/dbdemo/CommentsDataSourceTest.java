package edu.quinnpia.ser210.dbdemo;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by relkharboutly on 4/25/2017.
 */
@RunWith(AndroidJUnit4.class)
public class CommentsDataSourceTest {

    private CommentsDataSource commentsDataSource;
    @Before
    public void setUp() throws Exception {

        getTargetContext().deleteDatabase(MySQLiteHelper.DATABASE_NAME);
        commentsDataSource = new CommentsDataSource(getTargetContext());
        commentsDataSource.open();
    }

    @After
    public void tearDown() throws Exception {
        commentsDataSource.close();
    }

    @Test
    public void createComment() throws Exception {

        commentsDataSource.createComment("cool");
        List<Comment> comments = commentsDataSource.getAllComments();
        assertEquals(comments.size(),1);
        assertTrue(comments.get(0).getComment().equals("cool"));
    }

    @Test
    public void deleteComment() throws Exception {
        Comment addedComment = commentsDataSource.createComment("hate");
        commentsDataSource.deleteComment(addedComment);
        List<Comment> comments = commentsDataSource.getAllComments();
        assertEquals(comments.size(),0);
      //  assertTrue(comments.get(0).getComment().equals("hate"));

    }

}