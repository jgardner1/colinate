package net.jonathangardner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ColinateActivity extends Activity {
	private Db db;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button endTurnButton = (Button) findViewById(R.id.end_turn);
        endTurnButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
            	endTurn();
            }
        });
        db = new Db(this);
        db.open();
        fillValues();
    }
    
    public void fillValues() {
    	TextView population = (TextView) findViewById(R.id.population);
    	population.setText(String.valueOf(db.fetchNation()));
    }
    
    public void endTurn(){
    	db.updateNation(db.fetchNation()+100);
    	fillValues();
    }
}