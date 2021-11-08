package com.luismolina.acromine;

import org.junit.Test;

import static org.junit.Assert.*;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.luismolina.acromine.model.Dictionary;
import com.luismolina.acromine.model.DictionaryViewModel;

import java.util.ArrayList;

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
    public void testViewModel() throws InterruptedException {
        ArrayList data = new ArrayList<Dictionary>();
        Dictionary dictionary = new Dictionary();
        dictionary.sf = "TEST";
        Dictionary.Word word = new Dictionary.Word();
        word.lf = "TESTING";
        data.add(dictionary);

        DictionaryViewModel dictionaryViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(DictionaryViewModel.class);
        dictionaryViewModel.loadDictionary(data);

        assertEquals(LiveDataTestUtil.getOrAwaitValue(dictionaryViewModel.getDictionaryLiveData()), data); // Passes
    }
}