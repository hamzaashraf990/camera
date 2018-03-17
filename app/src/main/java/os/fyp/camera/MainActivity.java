package os.fyp.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imgview;
    Button btnselect, btnshow;
    Integer REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap btmap;
    Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgview = (ImageView) findViewById(R.id.imView);
        btnselect = (Button) findViewById(R.id.btSelect);
        btnshow = (Button) findViewById(R.id.btShow);
        btnshow.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("Image",btmap);
                intent.putExtra("imagePath",image);
                startActivity(intent);

            }

        });

        btnselect.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                final CharSequence[] items = {"Camera", "Gallery","Cancel"};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("ADD IMAGE");

                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (items[i].equals("Camera")) {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            startActivityForResult(intent, REQUEST_CAMERA);
                        }
                        else if (items[i].equals("Gallery")) {

                             Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                            intent.setType("image/*");

                            startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                        }
                    }
                });

                builder.show();

            }

        });
    }

    @Override

    public void onActivityResult(int requestcode, int resultcode, Intent data) {

        super.onActivityResult(requestcode, resultcode, data);

        if (resultcode == Activity.RESULT_OK) {


            if (requestcode == REQUEST_CAMERA) {


                Bundle bundle = data.getExtras();

                if (bundle != null) {

                    btmap = (Bitmap) bundle.get("data");

                    imgview.setImageBitmap(btmap);
                }
            }
            else {

                if (requestcode == SELECT_FILE) {

                    image = data.getData();


                    try {

                        btmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    imgview.setImageBitmap(btmap);


                }
            }
        }
    }



}

