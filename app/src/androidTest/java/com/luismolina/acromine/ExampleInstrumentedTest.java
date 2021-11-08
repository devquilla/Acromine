package com.luismolina.acromine;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.luismolina.acromine.model.Dictionary;
import com.luismolina.acromine.model.DictionaryViewModel;

import java.util.ArrayList;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule mMainActivityRule = new ActivityScenarioRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        ArrayList data = new ArrayList<Dictionary>();
        Dictionary dictionary = new Dictionary();
        dictionary.sf = "TEST";
        Dictionary.Word word = new Dictionary.Word();
        word.lf = "TESTING";
        data.add(dictionary);

        DictionaryViewModel dictionaryViewModel = new ViewModelProvider((ViewModelStoreOwner) appContext).get(DictionaryViewModel.class);
        dictionaryViewModel.loadDictionary(data);
        // Context of the app under test.
        assertEquals("com.luismolina.acromine", appContext.getPackageName());
    }
}