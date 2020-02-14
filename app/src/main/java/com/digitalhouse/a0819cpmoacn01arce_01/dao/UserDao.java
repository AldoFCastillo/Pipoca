package com.digitalhouse.a0819cpmoacn01arce_01.dao;

import androidx.annotation.NonNull;

import com.digitalhouse.a0819cpmoacn01arce_01.model.User;
import com.digitalhouse.a0819cpmoacn01arce_01.utils.ResultListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDao {

    public void addProfile (final ResultListener<Boolean> resultListener, String userId, User user) {
        FirebaseFirestore db;
        db = FirebaseFirestore.getInstance();
        db.collection("usuarios").document(userId).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                resultListener.onFinish(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                resultListener.onFinish(false);
            }
        });
    }
}
