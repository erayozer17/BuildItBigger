package com.erayo.myandroidlibrary;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AsyncCallForJokeInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        AsyncCallForJoke asyncCallForJoke = new AsyncCallForJoke(getContext());
        asyncCallForJoke.notTest = false;
        asyncCallForJoke.execute();
        Thread.sleep(5000);
        System.out.print(asyncCallForJoke.get());
        assertTrue(asyncCallForJoke.get() != null);
    }
}
