package com.cs100w.calvin.spring2015expoapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class TeamInfoFragment extends Fragment {

    private String projectID;
    private TeamProject project;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_team_info, container, false);
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                projectID = bundle.getString("project_id");
                project = getTeam(loadJSONFromAsset(), projectID);
                int imageResource = getResources().getIdentifier(project.getPathToImg(), "drawable", getActivity().getPackageName());
                Drawable d = getResources().getDrawable(imageResource);
                ((ImageView)view.findViewById(R.id.projectImage)).setImageDrawable(d);
                ((TextView)view.findViewById(R.id.projectNameText)).setText(project.getProjectName());
                ((TextView)view.findViewById(R.id.projectDescriptionText)).setText(project.getProjectSummary());
            }
            else{

            }

               return view;
            }


    public String loadJSONFromAsset() {
        String json;
        try {

            InputStream is = getActivity().getAssets().open("projects.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public TeamProject getTeam(String json, String id){
        try {
            JSONObject jObject = new JSONObject(json);
            JSONArray jArray = jObject.getJSONArray("projects");
            int parsedID = Integer.parseInt(id);
            JSONObject oneObject = jArray.getJSONObject(parsedID - 1);
            String teamID = (parsedID + 1) + "";
            String projectName = oneObject.getString("projectName");
            String projectSummary = oneObject.getString("projectSummary");
            String pathToImg = oneObject.getString("pathToImg");
            return new TeamProject(pathToImg, teamID, projectName, projectSummary);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    }

