package com.salah.taskmaster;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void taskInstancesTest() {
        Task taskOne = new Task("Workout", "10 push ups", "complete");
        assertEquals(taskOne.getTitle(), "Workout");
        assertEquals(taskOne.getBody(), "10 push ups");
        assertEquals(taskOne.getState(), "complete");


    }

}