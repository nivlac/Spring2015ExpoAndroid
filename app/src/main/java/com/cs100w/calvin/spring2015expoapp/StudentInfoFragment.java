package com.cs100w.calvin.spring2015expoapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by calvin on 4/5/15.
 */
public class StudentInfoFragment extends Fragment {

    public static final StudentInfoFragment newInstance(String studentName, String studentSummary, String skillA, String skillB, String pathToImg, String linkedInURL)
    {
        StudentInfoFragment f = new StudentInfoFragment();
        Bundle bdl = new Bundle();
        bdl.putString("student_name", studentName);
        bdl.putString("student_summary", studentSummary);
        bdl.putString("skill_a", skillA);
        bdl.putString("skill_b", skillB);
        bdl.putString("path_to_img", pathToImg);
        bdl.putString("linkedin_url", linkedInURL);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_student_info, container, false);
        int imageResource = getResources().getIdentifier(getArguments().getString("path_to_img"), "drawable", getActivity().getPackageName());
        Drawable d = getResources().getDrawable(imageResource);
        ((ImageView)v.findViewById(R.id.studentImage)).setImageDrawable(d);
        ((TextView)v.findViewById(R.id.studentNameText)).setText(getArguments().getString("student_name"));
        ((TextView)v.findViewById(R.id.studentSummaryText)).setText(getArguments().getString("student_summary"));
        ((TextView)v.findViewById(R.id.skillAText)).setText("Skill 1: " + getArguments().getString("skill_a"));
        ((TextView)v.findViewById(R.id.skillBText)).setText("Skill 2: " + getArguments().getString("skill_b"));
        final Activity activity = getActivity();
                ((Button) v.findViewById(R.id.linkedInButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String url = getArguments().getString("linkedin_url");
                if (!url.isEmpty()) {
                    Uri webpage = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(intent);

                }
            }
        });
        return v;
    }

}
