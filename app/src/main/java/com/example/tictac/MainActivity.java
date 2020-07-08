package com.example.tictac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    ImageView imageView1,imageView2,imageView3,imageView4,
    imageView5,imageView6,imageView7,imageView8,imageView9;
    private TextView status;
    private Button reset;
    int activePlayer =0;
   private  AlertDialog alertDialog;
    int []gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningState={{0,1,2}, {3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
status=findViewById(R.id.status);
imageView1=findViewById(R.id.imageView0);
        imageView2=findViewById(R.id.imageView1);
        imageView3=findViewById(R.id.imageView2);
        imageView4=findViewById(R.id.imageView3);
        imageView5=findViewById(R.id.imageView4);
        imageView6=findViewById(R.id.imageView5);
        imageView7=findViewById(R.id.imageView6);
        imageView8=findViewById(R.id.imageView7);
        imageView9=findViewById(R.id.imageView8);
reset=findViewById(R.id.reset);
         alertDialog=new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert !!");

reset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        gameReset();
    }
});
    }

    public void playerTap(View view)
    {
        ImageView image=(ImageView)view;
        int tappedImage =Integer.parseInt(image.getTag().toString());
//        if(!gameActive)
//        {
//            gameReset();
//        }

        if (gameState[tappedImage] == 2) {
                    gameState[tappedImage] = activePlayer;
                    image.setTranslationY(-1000f);
                    if (activePlayer == 0) {
                        image.setImageResource(R.drawable.x);
                        activePlayer = 1;
                        status.setText("O`s turn, Tap to Play");
                    } else {
                        image.setImageResource(R.drawable.o);
                        activePlayer = 0;
                        status.setText("X`s turn, Tap to Play");
                    }
                    image.animate().translationYBy(1000f).setDuration(300);
                } else {
            int count=0;
for(int i=0;i<gameState.length;i++)
{
    if(gameState[i]!=2)
    {
        count++;
        if(count==gameState.length)
        {
            alertDialog.setMessage("No one Wins play again");
            alertDialog.show();
            status.setText("Game is finished,");
            gameActive=false;
        }
        else
        {

            alertDialog.setMessage("Tapping Twice in same block is not allowed");

            alertDialog.show();
        }
    }

}

                }
                for (int[] winningPosition : winningState) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                            && gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]] != 2) {
                        if (gameState[winningPosition[0]] == 0) {
                            alertDialog.setMessage("'X' has win the Game");

                        } else {
                            alertDialog.setMessage("'O' has won the Game");
                        }
                        status.setText("Game is finished,");
                     alertDialog.show();
                        gameActive = false;
                        gameReset();

                    }


                }


    }

    private void gameReset() {
        gameActive=true;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
imageView1.setImageResource(0);
        imageView2.setImageResource(0);
        imageView9.setImageResource(0);
        imageView8.setImageResource(0);
        imageView7.setImageResource(0);
        imageView6.setImageResource(0);
        imageView5.setImageResource(0);
        imageView4.setImageResource(0);
        imageView3.setImageResource(0);
        status.setText("X`s turn, Tap to Play");
    }
}
