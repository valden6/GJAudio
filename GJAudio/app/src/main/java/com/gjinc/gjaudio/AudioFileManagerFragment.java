package com.gjinc.gjaudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.gjinc.gjaudio.databinding.AudioFileManagerFragmentBinding;


public class AudioFileManagerFragment extends Fragment {

    AudioFileManagerFragmentBinding binding;
    AudioFile audioFile;
    MyPlayButtonListener playButtonListener;
    MyPreviousButtonListener previousButtonListener;
    MyNextButtonListener nextButtonListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        binding  =  DataBindingUtil.inflate(inflater, R.layout.audio_file_manager_fragment,container,false);

        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButtonListener.onDoneSomething(audioFile);
            }
        });

        binding.buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playButtonListener.onDoneSomething(audioFile);
            }
        });

        binding.buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousButtonListener.onDoneSomething(audioFile);
            }
        });

        binding.textViewTitleMusic.setText(audioFile.getTitle());
        binding.textViewArtistMusic.setText(audioFile.getArtist());

        return binding.getRoot();
    }

    public void setPlayButtonListener(MyPlayButtonListener playButtonListener){
        this.playButtonListener = playButtonListener;
    }

    public void setPreviousButtonListener(MyPreviousButtonListener previousButtonListener){
        this.previousButtonListener = previousButtonListener;
    }

    public void setNextButtonListener(MyNextButtonListener nextButtonListener){
        this.nextButtonListener = nextButtonListener;
    }

    public void setAudioFile(AudioFile audioFile){
        this.audioFile = audioFile;
    }



}
