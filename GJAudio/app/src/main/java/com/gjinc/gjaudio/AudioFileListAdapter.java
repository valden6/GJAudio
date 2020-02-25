package com.gjinc.gjaudio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.gjinc.gjaudio.databinding.AudioFileItemBinding;
import java.util.List;

public class AudioFileListAdapter extends RecyclerView.Adapter<AudioFileListAdapter.ViewHolder>{

    List <AudioFile> audioFileList;
    private MyListener listener;

    public  AudioFileListAdapter(List <AudioFile> fileList)  {

        assert  fileList  !=  null;
        audioFileList  =  fileList;

    }

    @NonNull
    @Override
    public  ViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int  viewType)  {
        AudioFileItemBinding binding  =  DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()), R.layout.audio_file_item,  parent,false);
        return  new  ViewHolder(binding);
    }

    @Override
    public  void  onBindViewHolder(@NonNull  ViewHolder  holder,  int  position)  {
        final AudioFile  file  =  audioFileList.get(position);
        holder.viewModel.setAudioFile(file);
        holder.binding.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDoneSomething(file);
            }
        });
    }

    @Override
    public  int  getItemCount()  {
        return  audioFileList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private  AudioFileItemBinding  binding;
        private AudioFileViewModel viewModel = new AudioFileViewModel();

        ViewHolder(AudioFileItemBinding  binding)  {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setAudioFileViewModel(viewModel);
        }
    }

    public void setListener(MyListener listener) {
        this.listener = listener;
    }
}
