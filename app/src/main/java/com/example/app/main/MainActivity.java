package com.example.app.main;

import static com.example.app.common.bean.Song.MODE_ALL;
import static com.example.app.common.bean.Song.MODE_FANCY;
import static com.example.app.common.bean.Song.MODE_RACE;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.app.R;
import com.example.app.base.activity.BaseToolActivity;
import com.example.app.base.adapter.SortedItem;
import com.example.app.common.adapter.MyDiffAdapter;
import com.example.app.common.adapter.MyItemDecor;
import com.example.app.common.bean.Song;
import com.example.app.common.item.ImageCardItem;
import com.example.app.common.listener.OnItemEventListener;
import com.example.app.dao.SongDaoHelper;
import com.example.app.databinding.ActivityMainBinding;
import com.example.app.databinding.NavigationBarBinding;
import com.example.app.databinding.NavigationBottomBarBinding;
import com.example.app.databinding.SelectorBarBinding;
import com.example.app.global.GlobalApp;
import com.example.app.util.Utility;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>created by wyh in 2021/11/15</p>
 */
public class MainActivity extends BaseToolActivity<ActivityMainBinding> {

    private static final String TAG = "<MainActivity>";

    NavigationBarBinding navigationBarTop;
    SelectorBarBinding selectorBar;
    NavigationBottomBarBinding navigationBarBottom;
    MyDiffAdapter sortedAdapter;
    List<SortedItem> mData;
    int navigatorId;
    String mode = MODE_ALL;
    String modeDetail;

    @Override
    protected void onCreateViewModule() {
        initBinding();
        initNavigatorBottom();
        initData();
        matchBottomTv();
        matchMode();
    }

    private void initBinding() {
        navigationBarTop = NavigationBarBinding.bind(rootBinding.getRoot());
        selectorBar = SelectorBarBinding.bind(rootBinding.getRoot());
        navigationBarBottom = NavigationBottomBarBinding.bind(rootBinding.getRoot());
    }

    private void initNavigatorBottom() {
        navigationBarBottom.musicImage.setImageResource(R.drawable.navigation_star_songs);
        navigationBarBottom.musicTv.setText(R.string.navigation_bottom_songs);
        navigationBarBottom.squareImage.setImageResource(R.drawable.navigation_star_square);
        navigationBarBottom.squareTv.setText(R.string.navigation_bottom_square);
        navigationBarBottom.teachImage.setImageResource(R.drawable.navigation_star_teach);
        navigationBarBottom.teachTv.setText(R.string.navigation_bottom_teach);
        navigationBarBottom.collectImage.setImageResource(R.drawable.navigation_star_collect);
        navigationBarBottom.collectTv.setText(R.string.navigation_bottom_collect);
        navigationBarBottom.midButton.setImageResource(R.drawable.navigation_star_ufo);
        navigationBarBottom.midButton.setOnClickListener(v -> {

        });
    }

    private void initData() {
        mData = new ArrayList<>();
        SongDaoHelper songDaoHelper = SongDaoHelper.getInstance(GlobalApp.getInstance());
        List<Song> songs = songDaoHelper.searchAll();
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            Log.d(TAG, "song[" + i + "].id = " + song.getId());
            mData.add(new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId()));
        }
        sortedAdapter = new MyDiffAdapter(mData);
        rootBinding.rvDataList.setAdapter(sortedAdapter);
        rootBinding.rvDataList.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setData(List<Song> data) {
        mData.clear();
        for (int i = 0; i < data.size(); i++) {
            Song song = data.get(i);
            Log.d(TAG, "add song[" + i + "]: id = " + song.getId());
            mData.add(new ImageCardItem().setImageSource(song.getImageSrc())
                    .setSongName(song.getSongName())
                    .setDifficulty(song.getDifficulty())
                    .setDescription(song.getDescription())
                    .setSortedIndex(song.getId()));
        }
        sortedAdapter.notifyDiff();
    }

    @Override
    protected void newExternalRelations() {
        new MainRelations(this);
    }

    public void setOnItemEventListener(OnItemEventListener listener) {
        sortedAdapter.setOnItemListener(listener);
    }

    public void setMusicButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.musicButton.setOnClickListener(v -> {
            navigatorId = 0;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setSquareButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.squareButton.setOnClickListener(v -> {
            navigatorId = 1;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setTeachButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.teachButton.setOnClickListener(v -> {
            navigatorId = 2;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void setCollectButtonClickListener(View.OnClickListener listener) {
        navigationBarBottom.collectButton.setOnClickListener(v -> {
            navigatorId = 3;
            matchBottomTv();
            listener.onClick(v);
        });
    }

    public void matchBottomTv() {
        navigationBarBottom.musicTv.setVisibility(View.GONE);
        navigationBarBottom.teachTv.setVisibility(View.GONE);
        navigationBarBottom.collectTv.setVisibility(View.GONE);
        navigationBarBottom.squareTv.setVisibility(View.GONE);
        switch (navigatorId) {
            case 0:
                navigationBarBottom.musicTv.setVisibility(View.VISIBLE);
                break;
            case 1:
                navigationBarBottom.squareTv.setVisibility(View.VISIBLE);
                break;
            case 2:
                navigationBarBottom.teachTv.setVisibility(View.VISIBLE);
                break;
            case 3:
                navigationBarBottom.collectTv.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void matchMode() {
        selectorBar.allModeBtn.setTextColor(getColor(R.color.color_text_black));
        selectorBar.fancyModeBtn.setTextColor(getColor(R.color.color_text_black));
        selectorBar.raceModeBtn.setTextColor(getColor(R.color.color_text_black));
        switch (mode) {
            case MODE_ALL:
                selectorBar.allModeBtn.setTextColor(getColor(R.color.pantone_flesh_1));
                break;
            case MODE_FANCY:
                selectorBar.fancyModeBtn.setTextColor(getColor(R.color.pantone_flesh_1));
                break;
            case MODE_RACE:
                selectorBar.raceModeBtn.setTextColor(getColor(R.color.pantone_flesh_1));
                break;
        }
    }
}
