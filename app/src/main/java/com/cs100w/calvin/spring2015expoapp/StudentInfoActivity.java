package com.cs100w.calvin.spring2015expoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoActivity extends Activity {
    PageAdapter pageAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent = getIntent();
        String projectName = intent.getStringExtra("project_name");
        Log.e("Name of Project", projectName);
        List<Fragment> fragments = getFragments(projectName);
        pageAdapter = new PageAdapter(getFragmentManager(), fragments);
        ViewPager pager =
                (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);
        Log.e("Yes","" +  fragments.size());
    }

    /**
     * Load the json file into a readable and parsable string.
     * @return a json formatted string
     */
    public String loadJSONFromAsset() {
        String json;
        try {

            InputStream is = getAssets().open("students.json");

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

    /**
     *
     * @param json The formatted json string
     * @param projectName Name of the project which you are trying to retrieve students
     * @return A list of students in the project.
     */
    public List<Student> getStudents(String json, String projectName){
        try {
            List<Student> students = new ArrayList<>();
            String id;
            String studentName; String studentSummary; String skillA; String skillB; String pathToImg; String linkedInURL;
            JSONObject jObject = new JSONObject(json);
            JSONArray jArray = jObject.getJSONArray("students");
            JSONObject obj;
            for(int i = 0; i < jArray.length(); i++){
                obj = jArray.getJSONObject(i);

                if(obj.getString("project").equals(projectName)){
                    id = obj.getString("id");
                    studentName = obj.getString("name");
                    studentSummary = obj.getString("summary");
                    skillA = obj.getString("skillA");
                    skillB = obj.getString("skillB");
                    pathToImg = obj.getString("pathToImg");
                    linkedInURL = obj.getString("linkedInURL");
                    students.add(new Student(id,studentName, studentSummary, skillA, skillB, pathToImg,linkedInURL,projectName));
                }
            }
            return students;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    private List<Fragment> getFragments(String projectName) {
        List<Fragment> fList = new ArrayList<>();
        List<Student> students = getStudents(loadJSONFromAsset(), projectName);
        for(Student s : students) {
            fList.add(StudentInfoFragment.newInstance(s.getId(),s.getName(),s.getSummary(),s.getSkillA(),s.getSkillB(),s.getPathToImg(),s.getLinkedInURL()));
        }
        return fList;
    }
}