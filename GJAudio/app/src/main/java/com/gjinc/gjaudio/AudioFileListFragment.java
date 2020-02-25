package com.gjinc.gjaudio;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.gjinc.gjaudio.databinding.AudioFileListFragmentBinding;
import java.util.ArrayList;
import java.util.List;

public class AudioFileListFragment extends Fragment {

    AudioFileListAdapter audioFileListAdapter;
    MyListener listener;
    List<AudioFile> audioFileList = new ArrayList<AudioFile>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        AudioFileListFragmentBinding  binding  =  DataBindingUtil.inflate(inflater, R.layout.audio_file_list_fragment,container,false);
        binding.audioFileList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));

        Uri uri  =  MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;  //  La  carte  SD
        String[]  projection  =     {
                                    MediaStore.Audio.Media.DATA,
                                    MediaStore.Audio.Media.TITLE,
                                    MediaStore.Audio.Media.ARTIST,
                                    MediaStore.Audio.Media.ALBUM,
                                    MediaStore.Audio.Media.BOOKMARK,
                                    MediaStore.Audio.Media.YEAR,
                                    MediaStore.Audio.Media.DURATION
                                    };  //chemin  du  fichier,  titre,  artist

        Cursor cursor  =  getContext().getContentResolver().query(uri,projection,null,null,null);
        while(cursor.moveToNext()){
            String path = cursor.getString(0);
            String title = cursor.getString(1);
            String artist = cursor.getString(2);
            String album = cursor.getString(3);
            String genre = cursor.getString(4);
            int year = cursor.getInt(5);
            int duration = cursor.getInt(6);

            audioFileList.add(new AudioFile(title,path,artist,album,genre,year,duration));
        }

        //List<AudioFile> fakeList  =  new ArrayList<>();
        audioFileListAdapter = new AudioFileListAdapter(audioFileList);
        binding.audioFileList.setAdapter(audioFileListAdapter);
        audioFileListAdapter.setListener(this.listener);

        return  binding.getRoot();
    }

    public void setListener(MyListener listener){
        this.listener = listener;
    }
}
