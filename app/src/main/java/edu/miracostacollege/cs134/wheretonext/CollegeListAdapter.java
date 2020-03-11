package edu.miracostacollege.cs134.wheretonext;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.wheretonext.model.College;

/**
 * Helper class to provide custom adapter for the <code>College</code> list.
 */
public class CollegeListAdapter extends ArrayAdapter<College> {

    private Context mContext;
    private List<College> mCollegesList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>CollegeListAdapter</code> given a mContext, resource id and list of colleges.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param colleges The list of colleges to display
     */
    public CollegeListAdapter(Context c, int rId, List<College> colleges) {
        super(c, rId, colleges);
        mContext = c;
        mResourceId = rId;
        mCollegesList = colleges;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the College selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);


        LinearLayout customLayout = view.findViewById(R.id.listHorizontal) ;
        College selectedCollege = mCollegesList.get(pos);

        customLayout.setTag(selectedCollege);
        TextView collegeNameTV = view.findViewById(R.id.collegeNameTV) ;
        collegeNameTV.setText(selectedCollege.getName());
        RatingBar ratingBar = view.findViewById(R.id.ratingBar) ;
        ratingBar.setRating((float) selectedCollege.getRating());

        ImageView imageDetails = view.findViewById(R.id.imageDetails) ;

        try {
            AssetManager am = mContext.getAssets() ;
            InputStream stream = am.open(selectedCollege.getImageName()) ;
            Drawable image = Drawable.createFromStream(stream, selectedCollege.getImageName()) ;
            imageDetails.setImageDrawable(image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO:  Write the code to correctly inflate the view (college_list_item) with
        // TODO:  all widgets filled with the appropriate College information.



        return view;
    }
}
