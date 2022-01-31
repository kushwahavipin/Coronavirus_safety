package com.erinfa.fbmusicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    DatabaseReference reference;

    ArrayList<String> imageurl=new ArrayList<>();
    List<SliderItem> sliderItems =new ArrayList<>();

    TextView songName,songArtist;
    ArrayList<String> songnameslist=new ArrayList<>();
    ArrayList<String> songartistslist=new ArrayList<>();

    MediaPlayer mediaPlayer;
    ArrayList<String> songurlList=new ArrayList<>();


    boolean play = true;
    ImageView play_img,pause_img,prev_img,next_img;

    Integer currentSongIndex =0;


    SeekBar seekBars;
    //for time pass and due time
    TextView pass,due;
    //we need handler to handle text on ui
    Handler handler;
    //we need two string to store text
    String out, out2;
    //need integer to store total time
    Integer totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2=findViewById(R.id.imageslider);
        reference= FirebaseDatabase.getInstance().getReference();

        songArtist=findViewById(R.id.songartist);
        songName=findViewById(R.id.songname);

        play_img=findViewById(R.id.play);
        pause_img=findViewById(R.id.pause);
        prev_img=findViewById(R.id.prev);
        next_img=findViewById(R.id.next);

        mediaPlayer=new MediaPlayer();

        ////////////seekbar   initialize...////////////////////////
        seekBars=findViewById(R.id.seek_bar);
        pass=findViewById(R.id.tv_pass);
        due=findViewById(R.id.tv_due);
        handler=new Handler();



        seekBars.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        seekBars.setProgress(mediaPlayer.getCurrentPosition());
                    }
                },0,1000000) ;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                initializeSeekBar();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //now iterate over snapshot child..
                for (DataSnapshot ds: snapshot.getChildren()){
                    //add all image urls in list
                    imageurl.add(ds.child("imageurl").getValue(String.class));
                    songnameslist.add(ds.child("songname").getValue(String.class));
                    songartistslist.add(ds.child("songarlist").getValue(String.class));
                    songurlList.add(ds.child("songurl").getValue(String.class));
                }
                for (int i=0; i<imageurl.size();i++){
                    //now add urls in slideritems
                    sliderItems.add(new SliderItem(imageurl.get(i)));
                }

                viewPager2.setAdapter(new SliderAdapter(sliderItems));
                viewPager2.setClipToPadding(false);
                viewPager2.setClipChildren(false);
                viewPager2.setOffscreenPageLimit(3);
                viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

                //now make composite page transformer to set page margin and page y scale..
                CompositePageTransformer compositePageTransformer=new CompositePageTransformer();
                compositePageTransformer.addTransformer(new MarginPageTransformer(40));
                compositePageTransformer.addTransformer((page, position) -> {
                    page.setScaleY(1);
                    // here 1 mean all page
                });
                viewPager2.setPageTransformer(compositePageTransformer);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                //make new function
                init(viewPager2.getCurrentItem());
                currentSongIndex=viewPager2.getCurrentItem();

            }
        });

        next_img.setOnClickListener(view -> {
            currentSongIndex=currentSongIndex+1;

            viewPager2.setCurrentItem(currentSongIndex);

            init(currentSongIndex);
        });
        play_img.setOnClickListener(view -> {
            if (play){
                pause_img.setVisibility(View.VISIBLE);
                play_img.setVisibility(View.INVISIBLE);
                mediaPlayer.start();
            }
        });
        pause_img.setOnClickListener(view -> {
            if (play){
                pause_img.setVisibility(View.INVISIBLE);
                play_img.setVisibility(View.VISIBLE);
                mediaPlayer.pause();

            }
        });
        prev_img.setOnClickListener(view -> {
            //here decrease index by 1
            currentSongIndex=currentSongIndex-1;
            viewPager2.setCurrentItem(currentSongIndex);
            init(currentSongIndex);
        });

    }

    private void init(int currentItem) {

        //we check here is medially will loaded with and other source then we will reset it first.otherwise two song will play...
        try {
            if(mediaPlayer.isPlaying())
                mediaPlayer.reset();
        }catch (Exception ignored){

        }

        pause_img.setVisibility(View.VISIBLE);
        play_img.setVisibility(View.INVISIBLE);
        play=true;

        ////////////////////play pause prev next............/////////////

        //now settext to our textview with the help of arraylist and currentitem value..
        songName.setText(songnameslist.get(currentItem));
        songArtist.setText(songnameslist.get(currentItem));

        ////////////////songgg    media  Player.....//////////////////////////
        try {
//            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDataSource(songurlList.get(currentItem));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mediaPlayer -> {
                mediaPlayer.start();
                ////////////seekbar   initialize...////////////////////////
//                    //call initialize seekbar function here...


                ////////////seekbar   initialize...////////////////////////
              //  Toast.makeText(getApplicationContext(),mediaPlayer.getDuration()+"",Toast.LENGTH_LONG).show();

            });
           // Toast.makeText(getApplicationContext(),mediaPlayer.getDuration(),Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
        ////////////////songgg    media  Player.....//////////////////////////


    }
    private void initializeSeekBar() {
        seekBars.setMax(30000);//set max limit of song
        int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
        seekBars.setProgress(mCurrentPosition);

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer!=null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition()/1000;
                    seekBars.setProgress(mCurrentPosition);

                    out=String.format("%02d:%02d",seekBars.getProgress()/60,seekBars.getProgress()%60);
                    //now set this to pass text view
                    pass.setText(out);
                }
                handler.postDelayed(this,1000);
            }
        });
        totalTime=mediaPlayer.getDuration()/1000;
        out2=String.format("%02d:%02d",totalTime/60,totalTime%60);
        due.setText(out2);
    }

}