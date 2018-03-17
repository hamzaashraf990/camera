package os.fyp.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
  ImageView v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        v1 = (ImageView) findViewById(R.id.iview);
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Image");
        v1.setImageBitmap(bitmap);
    }
}


