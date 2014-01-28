package com.dao.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

import com.dao.CourseDao;
import com.util.DbUtil;

public class CourseDaoTest {

    @Test
    public void shouldAddCourseCorrectly() throws SQLException{
        CourseDao  courseDao = new CourseDao();
        DbUtil dbUtil = Mockito.mock(DbUtil.class);
        Whitebox.setInternalState(courseDao, "dbUtil", dbUtil);
        
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement ps = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbUtil.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(ps);

        courseDao.addCourse("Maths");
        
        Mockito.verify(ps,Mockito.times(1)).setString(Mockito.eq(1),Mockito.eq("Maths"));
        Mockito.verify(ps,Mockito.times(1)).executeUpdate();
        Mockito.verify(connection,Mockito.times(1)).prepareStatement(Mockito.eq("insert into course (coursename) values (?)"));
    }


}