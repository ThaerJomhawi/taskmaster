package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskTodo;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Button addTaskButton = findViewById(R.id.buttonAddTask);
        EditText title = findViewById(R.id.editTextTaskTitle);
        EditText body = findViewById(R.id.editTextDescription);
        EditText state = findViewById(R.id.editTextTaskState);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                TaskTodo todo = TaskTodo.builder()
                        .title(title.getText().toString())
                        .body(body.getText().toString())
                        .state(state.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_SHORT).show();
                Intent goToHome = new Intent(AddTask.this, MainActivity.class);
                startActivity(goToHome);


            }
        });



    }
}