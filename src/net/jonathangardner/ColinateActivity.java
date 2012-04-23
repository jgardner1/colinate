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
    	Nation nation = db.fetchNation();
    	TextView population = (TextView) findViewById(R.id.population);
    	population.setText(String.valueOf(nation.population));
    }
    
    public void endTurn(){
    	Nation nation = db.fetchNation();
    	nation.population += 100;

    	db.update(nation);
    	fillValues();
    }
}