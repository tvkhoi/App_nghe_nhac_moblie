package com.example.app_nghe_nhac;

import static com.example.app_nghe_nhac.R.drawable.baseline_pause_24;
import static com.example.app_nghe_nhac.R.drawable.baseline_play_arrow_24;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView_ten_bai_hat;
    TextView textView_thoi_gian_bat_dau;
    TextView textView_thoi_gian_ket_thuc;
    ImageButton imageButton_chuyen_tiep_trai;
    ImageButton imageButton_chuyen_tiep_phai;
    ImageButton imageButton_play;
    ImageButton imageButton_stop;
    ImageView imageView_cd_music;
    SeekBar seekBar_thoi_gian;
    ArrayList<Bai_hat> arrayList = new ArrayList<>();
    MediaPlayer mediaPlayer = new MediaPlayer();
    int position=0;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList.add(new Bai_hat("Phía sau một cô gái",R.raw.phia_sau_mot_co_gai));
        arrayList.add(new Bai_hat("Xuyên màn đêm",R.raw.xuyen_man_dem));
        arrayList.add(new Bai_hat("Lá xa lìa cành",R.raw.la_xa_lia_canh));
        arrayList.add(new Bai_hat("Đâu phải cho anh",R.raw.dau_phai_cho_anh_lofi));
        arrayList.add(new Bai_hat("Ngày mai em đi mất",R.raw.ngay_mai_em_di_mat));
        arrayList.add(new Bai_hat("Tổng hợp các bài hát tiktok hay 2023",R.raw.music));
        arrayList.add(new Bai_hat("Tổng hợp bài hát hay",R.raw.videoplayback));


        Anhxa();
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_player_music);
        mediaPlayer = MediaPlayer.create(MainActivity.this,
                arrayList.get(position).getLink_baihat());
        // cick vào play
        imageButton_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        imageView_cd_music.clearAnimation();
                        imageButton_play.setImageResource(baseline_play_arrow_24);
                    }
                    else {
                        textView_ten_bai_hat.setText(arrayList.get(position).getTen_bai_hat());
                        mediaPlayer.start();
                        imageView_cd_music.startAnimation(animation);

                        imageButton_play.setImageResource(baseline_pause_24);

                    }
                SeeBar_set();
                thoi_gian();

            }
        }); // kết thúc sự kiện click play
        // click stop
        imageButton_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                imageView_cd_music.clearAnimation();
                imageButton_play.setImageResource(baseline_play_arrow_24);
            }
        });// kết thúc sự kiện click stop
        // click
        imageButton_chuyen_tiep_trai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                imageView_cd_music.startAnimation(animation);
                imageButton_play.setImageResource(baseline_pause_24);
                if(position == 0) {
                    position = arrayList.size()-1;
                }
                else {
                    position--;
                }
                Choi_nhac();

                SeeBar_set();
                textView_ten_bai_hat.setText(arrayList.get(position).getTen_bai_hat());

            }
        });
        imageButton_chuyen_tiep_phai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                imageView_cd_music.startAnimation(animation);
                imageButton_play.setImageResource(baseline_pause_24);
                if(position == arrayList.size()-1) {
                    position = 0;
                }
                else {
                    position++;
                }
                Choi_nhac();

                SeeBar_set();
                textView_ten_bai_hat.setText(arrayList.get(position).getTen_bai_hat());

            }
        });
        seekBar_thoi_gian.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar_thoi_gian.getProgress());
            }
        });
    }
    private void Choi_nhac() {
        mediaPlayer = MediaPlayer.create(MainActivity.this,
                arrayList.get(position).getLink_baihat());
        mediaPlayer.start();
    }
    private void SeeBar_set() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textView_thoi_gian_ket_thuc.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar_thoi_gian.setMax(mediaPlayer.getDuration());
    }
    private  void thoi_gian() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                textView_thoi_gian_bat_dau.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                seekBar_thoi_gian.setProgress(mediaPlayer.getCurrentPosition());
                if(seekBar_thoi_gian.getProgress()==mediaPlayer.getDuration()) {
                    imageButton_play.setImageResource(baseline_play_arrow_24);
                    imageView_cd_music.clearAnimation();
                }
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void Anhxa() {
        textView_ten_bai_hat = (TextView) findViewById(R.id.textView_ten_bai_hat);
        textView_thoi_gian_bat_dau = (TextView) findViewById(R.id.textView_thoi_gian_bat_dau);
        textView_thoi_gian_ket_thuc = (TextView) findViewById(R.id.textView_thoi_gian_ket_thuc);
        imageButton_chuyen_tiep_phai = (ImageButton) findViewById(R.id.ImageButton_chuyen_tiep_phai);
        imageButton_chuyen_tiep_trai = (ImageButton) findViewById(R.id.ImageButton_chuyen_tiep_trai);
        imageButton_play = (ImageButton) findViewById(R.id.ImageButton_play);
        imageButton_stop = (ImageButton) findViewById(R.id.ImageButton_stop);
        seekBar_thoi_gian = (SeekBar) findViewById(R.id.seeBar_thoi_gian);
        imageView_cd_music = (ImageView) findViewById(R.id.imageView_cd_music);
    }
}