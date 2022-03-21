package com.sungkyul.moji.subway;

import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sungkyul.moji.R;

//import uk.co.senab.photoview.PhotoViewAttacher;

public class Subwaymap extends AppCompatActivity implements View.OnClickListener{

    private SubsamplingScaleImageView imageView;
    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
   // private ImageView mImageView;

    private FloatingActionButton fabsubway, fabsudo, fabkwangju, fabdaejeon, fabdaegu, fabbusan;
    private TextView textsudo, textkwangju, textdaejeon, textdaegu, textbusan;
    private Boolean isMenuOpen = false;
    Float translationY = 100f;

    OvershootInterpolator interpolator = new OvershootInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subwaymap);

        initFabMenu();

        // xml에 정의한 이미지뷰 찾고
        //mImageView=(ImageView)findViewById(R.id.subwaymap);

        imageView = (SubsamplingScaleImageView)findViewById(R.id.subwaymap);
        imageView.setImage(ImageSource.resource(R.drawable.sudomap));



    }

    private void initFabMenu() {
        fabsubway = (FloatingActionButton)findViewById(R.id.fabsubway);

        fabsudo = (FloatingActionButton)findViewById(R.id.fabsudo);
        textsudo = (TextView)findViewById(R.id.textsudo);

        fabkwangju = (FloatingActionButton)findViewById(R.id.fabkwangju);
        textkwangju = (TextView)findViewById(R.id.textkwangju);

        fabdaejeon = (FloatingActionButton)findViewById(R.id.fabdaejeon);
        textdaejeon = (TextView)findViewById(R.id.textdaejeon);

        fabdaegu = (FloatingActionButton)findViewById(R.id.fabdaegu);
        textdaegu = (TextView)findViewById(R.id.textdaegu);

        fabbusan = (FloatingActionButton)findViewById(R.id.fabbusan);
        textbusan = (TextView)findViewById(R.id.textbusan);

        fabsudo.setAlpha(0f);
        fabkwangju.setAlpha(0f);
        fabdaejeon.setAlpha(0f);
        fabdaegu.setAlpha(0f);
        fabbusan.setAlpha(0f);

        textsudo.setAlpha(0f);
        textkwangju.setAlpha(0f);
        textdaejeon.setAlpha(0f);
        textdaegu.setAlpha(0f);
        textbusan.setAlpha(0f);

        fabsudo.setTranslationY(translationY);
        fabkwangju.setTranslationY(translationY);
        fabdaejeon.setTranslationY(translationY);
        fabdaegu.setTranslationY(translationY);
        fabbusan.setTranslationY(translationY);

        fabsubway.setOnClickListener(this);
        fabsudo.setOnClickListener(this);
        fabkwangju.setOnClickListener(this);
        fabdaejeon.setOnClickListener(this);
        fabdaegu.setOnClickListener(this);
        fabbusan.setOnClickListener(this);
    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        imageView.setImage(ImageSource.resource(0));
        fabsubway.animate().setInterpolator(interpolator).rotationBy(180f).setDuration(300).start();

        fabsudo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabkwangju.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabdaejeon.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabdaegu.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabbusan.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();


        textsudo.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        textkwangju.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        textdaejeon.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        textdaegu.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        textbusan.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();



    }
    private void closeMenu() {
        isMenuOpen = !isMenuOpen;

        fabsubway.animate().setInterpolator(interpolator).rotationBy(180f).setDuration(300).start();

        fabsudo.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabkwangju.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabdaejeon.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabdaegu.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabbusan.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

        textsudo.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        textkwangju.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        textdaejeon.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        textdaegu.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        textbusan.animate().translationY(translationY).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fabsubway:
                if(isMenuOpen){
                    closeMenu();
                }
                else {
                    openMenu();
                }
                break;
            case R.id.fabsudo:
                imageView.setImage(ImageSource.resource(R.drawable.sudomap));
                Toast.makeText(this, "수도권 지하철 노선도", Toast.LENGTH_SHORT).show();
                closeMenu();
                break;
            case R.id.fabkwangju:
                imageView.setImage(ImageSource.resource(R.drawable.kwangjumap));
                Toast.makeText(this, "광주 지하철 노선도", Toast.LENGTH_SHORT).show();
                closeMenu();
                break;
            case R.id.fabdaejeon:
                imageView.setImage(ImageSource.resource(R.drawable.daejeonmap));
                Toast.makeText(this, "대전 지하철 노선도", Toast.LENGTH_SHORT).show();
                closeMenu();
                break;
            case R.id.fabdaegu:
                imageView.setImage(ImageSource.resource(R.drawable.daegumap));
                Toast.makeText(this, "대구 지하철 노선도", Toast.LENGTH_SHORT).show();
                closeMenu();
                break;
            case R.id.fabbusan:
                imageView.setImage(ImageSource.resource(R.drawable.busanmap));
                Toast.makeText(this, "부산 지하철 노선도", Toast.LENGTH_SHORT).show();
                closeMenu();
                break;

        }
    }


}
