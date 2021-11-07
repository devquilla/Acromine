package com.luismolina.acromine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.luismolina.acromine.custom.LinearSpacesItemDecoration;
import com.luismolina.acromine.databinding.ActivityMainBinding;
import com.luismolina.acromine.internet.SimpleRequest;
import com.luismolina.acromine.internet.SimpleResponse;
import com.luismolina.acromine.model.Dictionary;
import com.luismolina.acromine.model.DictionaryViewModel;
import com.luismolina.acromine.model.WordAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private DictionaryViewModel dictionaryViewModel;
    private String mCurrentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);

        binding.mSwipeRefreshLayout.setOnRefreshListener(this);

        final WordAdapter adapter = new WordAdapter(new WordAdapter.DictionaryDiff());
        binding.mWordsRecyclerView.setAdapter(adapter);
        binding.mWordsRecyclerView.setLayoutManager(new LinearLayoutManager(App.MainContext));
        binding.mWordsRecyclerView.addItemDecoration(new LinearSpacesItemDecoration(36));
        binding.mSwipeRefreshLayout.setOnRefreshListener(this);

        dictionaryViewModel.getDictionaryLiveData().observe(this, words -> {
            adapter.initList((ArrayList<Dictionary.Word>)words);
            binding.mSwipeRefreshLayout.setRefreshing(false);
            binding.mInfo.setText("Words found: " + words.size());
        });

        binding.mBtnSearch.setOnClickListener(view -> getDictionary());

        getSupportActionBar().setElevation(0f);
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDictionary();
    }

    private void getDictionary(){
        mCurrentText = binding.mTxtSearch.getText().toString().trim();
        if(mCurrentText.length() <2){
            binding.mTxtSearch.setText("");
            binding.mTxtSearch.setError("Two letters minimum");
            return;
        }

        binding.mSwipeRefreshLayout.setRefreshing(true);
        SimpleRequest simpleRequest = new SimpleRequest(new SimpleResponse() {

            @Override
            public void onResponse(String response) {
                try{
                    Type type = new TypeToken<List<Dictionary>>() {}.getType();
                    List<Dictionary> dictionaryList = App.getGsonObject().fromJson(response, type);

                    if(dictionaryList.isEmpty()){
                        dictionaryViewModel.cleanDictionary();
                    } else {
                        dictionaryViewModel.loadDictionary(dictionaryList);
                    }
                } catch (Exception ex){
                    dictionaryViewModel.cleanDictionary();
                    binding.mSwipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, ex.getMessage());
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                dictionaryViewModel.cleanDictionary();
                binding.mSwipeRefreshLayout.setRefreshing(false);
                Log.e(TAG, error);
            }
        });

        String GET_URI = String.format("http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=%1$s", mCurrentText);
        simpleRequest.newGetRequest(GET_URI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onRefresh() {
        getDictionary();
    }
}