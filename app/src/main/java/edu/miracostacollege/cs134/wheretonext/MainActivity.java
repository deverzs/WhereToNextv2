package edu.miracostacollege.cs134.wheretonext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import edu.miracostacollege.cs134.wheretonext.R;
import edu.miracostacollege.cs134.wheretonext.model.College;
import edu.miracostacollege.cs134.wheretonext.model.JSONLoader;

public class MainActivity extends AppCompatActivity {

    //private DBHelper db;
    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;
    private ListView collegesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // DONE:  Fill the collegesList with all Colleges from the database
            collegesList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DONE:  Connect the list adapter with the list
        collegesListView = findViewById(R.id.collegeListView);
        collegesListAdapter = new CollegeListAdapter(this, R.layout.college_list_item, collegesList) ;

        // DONE:  Set the list view to use the list adapter
        collegesListView.setAdapter(collegesListAdapter);



    }

    //opens second view - details activity - BECAUSE of clicks
    public void viewCollegeDetails(View view) {
        College clicked = (College) view.getTag(); //cuz it's been clicked

        // DONE: Implement the view college details using an Intent
        Intent intent = new Intent(this, CollegeDetailsActivity.class );

        //key-value
        intent.putExtra("Name", clicked.getName());
        intent.putExtra("Population", clicked.getPopulation());
        intent.putExtra("Tuition", (float)clicked.getTuition());
        intent.putExtra("FileName", clicked.getImageName());
        intent.putExtra("Rating", (float)clicked.getRating());

        startActivity(intent);

    }

    public void addCollege(View view) {


        EditText nameEditText = findViewById(R.id.nameEditText) ;
        EditText populationEditText = findViewById(R.id.populationEditText) ;
        EditText tuitionEditText = findViewById(R.id.tuitionEditText) ;
        RatingBar ratingBarNew = findViewById(R.id.collegeRatingBar) ;

        String name = nameEditText.getText().toString();
        String population= populationEditText.getText().toString() ;
        String tuition = tuitionEditText.getText().toString() ;
        float rating = ratingBarNew.getRating() ;

        Log.i("Rating", "" + rating) ;

        College addMe = new College() ;
        addMe.setName(name);
        addMe.setRating(rating);

        Log.i("Send star:" , "" + addMe.getRating());
        int temp = Integer.parseInt(population) ;
        addMe.setPopulation(temp);
        double temp2 = Double.parseDouble(tuition) ;
        addMe.setTuition(temp2);


        collegesListAdapter.add(addMe);


        // DONE: Implement the code for when the user clicks on the addCollegeButton
    }

}
